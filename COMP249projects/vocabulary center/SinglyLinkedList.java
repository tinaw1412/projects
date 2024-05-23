
public class SinglyLinkedList {

    private SNode head;
    private int counter;

    public SinglyLinkedList() {

        head = null;
        counter = 0;

    }

    public SinglyLinkedList(SNode head) {
        this.head = head;
        counter = 0;
    }

    /**
     * This method adds a new word to the list. It creates a new node with the given
     * data and makes it point to the current head of the list.
     * 
     * @param data
     * @return
     */
    public void addAtHead(String data) {
        SNode temp = head;
        head = new SNode(data, null);
        head.next = temp;
        counter++;
    }

    /**
     * This method adds a new word to the list. It creates a new node with the given
     * data and inserts it at the end of the list.
     * 
     * @param word
     * @return
     */
    public void addAtTail(String word) {

        if (head == null) {
            head = new SNode(word, null);
        } else {
            SNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new SNode(word, null);

        }
        counter++;
    }

    /**
     * This method removes a word from the list. It traverses the list to find the
     * node that contains the given word and removes it from the list.
     * 
     * @param value
     * @return
     */
    public void removeWord(String value) {
        SNode temp = head;
        SNode prev = null;
        // search for the word in the list
        while (temp != null && !temp.value.equals(value)) {
            prev = temp;
            temp = temp.next;
        }
        // if the word is found, remove it from the list
        if (temp != null && temp.value.equals(value)) {
            if (prev == null) {
                head = temp.next;
            } else {
                prev.next = temp.next;
            }
            counter--;
        }
    }

    /**
     * This method returns the number of words in the list.
     * @return
     */
    public void display() {
        SNode temp = head;
        for (int i = 1; temp != null; i++) {

            System.out.println(i + ". " + temp.value);
            temp = temp.next;
        }
    }

    private class SNode {
        // data
        private String value;

        // link
        private SNode next;

        public SNode(String value, SNode next) {
            this.value = value;
            this.next = next;
        }
    }



    
}// close class
