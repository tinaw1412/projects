public class Vocab {

    private String topic;
    private SinglyLinkedList words;

    /**
     * Parameterized constructor
     * @param topic
     * @return
     */
    public Vocab(String topic) {
        this.topic = topic;
        this.words = new SinglyLinkedList();
    }

    /**
     * Copy constructor
     * @param other
     * @return
     */

    public Vocab(Vocab other) {
        this.topic = other.getTopic();
        this.words = other.getWords();
    }

    /**
     * Accessor method
     * @return topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Accessor method
     * @return words
     */
    public SinglyLinkedList getWords() {
        return words;
    }

    /**
     * Mutator methods
     * @param topic
     * @param words
     */

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setWords(SinglyLinkedList words) {
        this.words = words;
    }

}
