public class LinkedList {
    private Node head;

    public LinkedList() {
        this.head = null;
    }

    public void add(char character, int frequency) {
        Node newNode = new Node(character, frequency);
        newNode.setNext(head);
        this.head = newNode;
    }

    // Returns the head node for access to the full list from outside if needed.
    public Node getHead() {
        return this.head;
    }

    // Assuming tail() and isEmpty() methods remain unchanged, they work as intended without needing knowledge of the node's internal data structure.

    // Method to retrieve the tail of the list (excluding the head)
    public LinkedList tail() {
        if(this.head != null && this.head.getNext() != null) {
            LinkedList tailList = new LinkedList();
            tailList.head = this.head.getNext();
            return tailList;
        }
        return null;
    }

    // Method to check if the list is empty
    public boolean isEmpty() {
        return this.head == null;
    }
}
