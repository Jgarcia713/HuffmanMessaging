public class Node {
    private char character; // Character
    private int frequency; // Frequency of the character
    private Node next; // Reference to the next node

    // Constructor to initialize the character and its frequency
    public Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.next = null;
    }

    // Getter for the character
    public char getCharacter() {
        return this.character;
    }
    
    // Getter for the frequency
    public int getFrequency() {
        return this.frequency;
    }

    // Getter for the next node
    public Node getNext() {
        return this.next;
    }

    // Setter for the next node
    public void setNext(Node next) {
        this.next = next;
    }

    // toString method to represent the node as a string
    @Override
    public String toString() {
        return "Character: " + character + ", Frequency: " + frequency;
    }
}
