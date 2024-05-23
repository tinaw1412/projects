import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.w3c.dom.Node;

public class DoublyLinkedList {

    /**
     * Doubly linked list
     */
    private DNode head;
    private DNode tail;
    private int size;

    public DoublyLinkedList() {

        head = null;
        tail = null;
        size = 0;

    }

    /**
     * This method adds a new topic to the list. It creates a new node with the
     * given parameter and adds it to the head of the list.
     * 
     * @param vocab
     */

    public void addAtHead(Vocab vocab) {
        DNode temp = head;
        head = new DNode(vocab);

        if (tail == null) {
            tail = head;
        } else {
            head.after = temp;
            temp.before = head;
        }
        size++;
    }

    /**
     * This method adds a new topic to the list. It creates a new node with the
     * given parameter and adds it to the tail of the list.
     * 
     * @param vocab
     * @return
     */
    public void addAtTail(Vocab vocab) {
        DNode temp = tail;
        tail = new DNode(vocab);

        if (head == null) {
            head = tail;
        } else {
            tail.before = temp;
            temp.after = tail;
        }
        size++;
    }

    /**
     * This method adds a new topic after a specific Vocab object.
     * 
     * @param afterTopic
     * @param newTopic
     * @return
     */
    public void addAfter(Vocab afterTopic, Vocab newTopic) {

        DNode position = head;

        // find the position of beforetopic
        while (position != null && !position.vocab.equals(afterTopic)) {
            position = position.after;
        }

        if (position != null) {
            DNode newNode = new DNode(newTopic);
            newNode.before = position;
            newNode.after = position.after;

            if (position.after != null) {
                position.after.before = newNode;
            } else {
                tail = newNode;
            }
            position.after = newNode;
            size++;
        } else {
            System.out.println("afterTopic not found in the list");
        }
    }

    /**
     * This method adds a new topic before a specific Vocab object.
     * 
     * @param beforeTopic
     * @param newTopic
     * @return
     */
    public void addBefore(Vocab beforeTopic, Vocab newTopic) {
        DNode position = head;

        // find the position of beforetopic
        while (position != null && !position.vocab.equals(beforeTopic)) {
            position = position.after;
        }

        if (position != null) {
            DNode newNode = new DNode(newTopic);
            newNode.after = position;
            newNode.before = position.before;

            if (position.before != null) {
                position.before.after = newNode;
            } else {
                head = newNode;
            }
            position.before = newNode;
            size++;
        } else {
            System.out.println("beforeTopic not found in the list");
        }
    }

    /**
     * This method returns the size of the list.
     * 
     * @return
     */
    public int size() {
        int count = 0;
        DNode current = head;
        while (current != null) {
            count++;
            current = current.after;
        }
        return count;
    }

    /**
     * This method removes a topic from the list.
     * 
     * @param remVocab
     * @return
     */

    public void removeTopic(Vocab remVocab) {
        // if list is empty
        if (head == null) {
            System.out.println("List is empty");
            // if list has only one node
        } else if (head.vocab.equals(remVocab)) {
            head = head.after;
            head.before = null;
            size--;
        } else { // if list has more than one node
            DNode prev = head;
            DNode current = head.after;
            // traverse the list to find the target
            while (current != null && !current.vocab.equals(remVocab)) {
                prev = current;
                current = current.after;
            }
            // if target is found and remove
            if (current != null) {
                prev.after = current.after;
                size--;
            } else {
                System.out.println("Value not found");
            }

        }

    }
    
    public void saveOutputFile(){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("A3_output.txt"))) {
            DNode current=head;
            while (current!=null){
                writer.write("# "+current.vocab.getTopic());
                Vocab currentVocab=current.vocab;
                
                /*for (String word: currentVocab.getWords()){
                    writer.write(word+"/n");
                }*/
                
                writer.write("/n");
                current=current.after;
            }
            //flushes the writer for text is written to the file 
            writer. flush() ;
            } catch (IOException e) {
            // Handle I0 exception
            System.err.println("Error: " + e. getMessage ()) ;
            }
    }

    public void display() {

        System.out.println("""
                -----------------------------
                Pick a topic
                -----------------------------""");

        DNode current = head;
        for (int i = 1; current != null; current = current.after, i++) {
            System.out.println(i + " " + current.vocab.getTopic());
        }
        System.out.println("0 Exit");
        System.out.println("-----------------------------" + "\nEnter your choice");

    }

    /**
     * This method returns the Vocab object at the given index.
     * 
     * @param index
     * @return
     */
    public Vocab get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        DNode current = head;
        if (current != null) {
            for (int i = 0; i < index; i++) {
                current = current.after;
            }
        } else {
            return null;
        }

        return current.vocab;

    }

    /**
     * This method returns the Vocab object with the given topic.
     * 
     * @param topic
     * @return
     */
    public Vocab get(String topic) {

        DNode current = head;

        while (current != null) {
            if (current.vocab.getTopic().equals(topic)) {
                return current.vocab;
            }
            current = current.after;
        }

        return null;

    }

    private class DNode {
        // data
        private String topic;

        // links
        private DNode after;
        private DNode before;
        private Vocab vocab;

        public DNode(Vocab vocab) {
            this.vocab = new Vocab(vocab);
            this.after = null;
            this.before = null;
        }

    }

}// closes DoublyLinkedList
