
/**
 * A class for creating Huffman trees, as well as their encoding and decoding
 * processes
 * 
 */
public class Huffman {
	private class TreeNode {
		// Instance variables
		private char data;
		private int weight;
		private TreeNode left;
		private TreeNode right;
		private boolean isLeaf;

		public TreeNode(char theData, int weight) {
			data = theData;
			this.weight = weight;
			left = null;
			right = null;
			isLeaf = true;
		}

		public TreeNode(int weight) {
			data = ' ';
			this.weight = weight;
			left = null;
			right = null;
			this.isLeaf = false;
		}

		@Override
		public String toString() {
			if (!isLeaf)
				return "[(" + weight + "), LL" + left + ", RR" + right + "]";
			return "<\"" + data + "\", " + weight + ">";
		}
	}

	public static void main(String[] args) {
		Huffman x = new Huffman("hello");
	}

	private TreeNode root;
	private TreeNode[] queue;
	private int queueSize;
	private String message; // REMOVE LATER. USED FOR TESTING GUI

	public Huffman(String text) {
		/*-
		 * 0. break into methods?
		 * 1. Huffman tree is created, so next up is to get the encoding of the actual message
		 * 2. make a method to create the encoding of the message. I can think of several ways to do this:
		 * 		a. traverse the tree for each character fully (bad) estimated O(N^2)?
		 * 		b. traverse the tree once and store the traversal to each character, so we can easily map the 
		 * 		   traversal back to that character in the future. This can probably be done doing something
		 * 		   akin to bin sort, since it can work as simple hash table for unique values (decent)
		 *         estimated O(N+k)
		 * 		c. We do the bin sort thing still, but instead of going through the tree at all, we modify the build
		 * 		   stage to start keeping track of the traversal to each character (probably the trickiest, since we 
		 *         may need to add an attribute to keep track of predecessors). This sounded like a better idea in my head,
		 *         but it might estimate out to be O(N^2)
		 * 3. We probably go with 2b. 
		 * 3.5. I was thinking we could store the binary encoding into a long using bit shifting.
		 *      we would just need to create enough longs to fit the whole encoding, which could be done after
		 *      we know the length of each traversal to a character. We can store the traversal for each character
		 *      by creating a struct of sorts for each character that can store the traversal as a boolean or byte
		 *      array
		 * 4. Write a decode method that takes the encoded binary string and produces the original message!
		 * 5. Depending on how serious we want to get, it doesn't actually make sense to pass whole huffman trees back and forth
		 *    when trying to lower the cost of data transmission, so we could consider getting the preorder and inorder traversals
		 *    of the tree, and then passing that instead of the whole tree, in which case the receiver would then have to reconstruct 
		 *    the tree in an easier fashion, and then they can decode the values. 
		 * 
		 */

		boolean[] isCounted = new boolean[text.length()];
		char[] uniqueChars = new char[text.length()];
		int[] frequencies = new int[text.length()];
		int uniqueCount = 0;

		for (int i = 0; i < text.length(); i++) {
			if (!isCounted[i]) {
				char currentChar = text.charAt(i);
				int count = 1;
				for (int j = i + 1; j < text.length(); j++) {
					if (text.charAt(j) == currentChar) {
						count++;
						isCounted[j] = true;
					}
				}
				uniqueChars[uniqueCount] = currentChar;
				frequencies[uniqueCount] = count;
				uniqueCount++;
			}
		}

		queue = new TreeNode[uniqueCount];
		queueSize = 0;
		for (int i = 0; i < queue.length; i++) {
			this.insert(new TreeNode(uniqueChars[i], frequencies[i]));
		}

		while (queueSize > 1) {
			TreeNode node1 = this.removeNext();
			TreeNode node2 = this.removeNext();
			TreeNode innerNode = new TreeNode(node1.weight + node2.weight);
			innerNode.left = node1;
			innerNode.right = node2;
			this.insert(innerNode);
		}
		root = queue[0];

//		System.out.println(root);
		this.message = text; // REMOVE LATER. USED FOR TESTING GUI
	}

	/**
	 * Insert a TreeNode into the correct position in the priority queue
	 * 
	 * @param index
	 * @param newNode
	 */
	private void insert(TreeNode newNode) {
		queue[queueSize] = newNode;
		this.swim(queue, queueSize);
		queueSize++;
	}

	/**
	 * Sink method for heaps. It sinks a value down in order to create/maintain a
	 * heap ordering.
	 * 
	 * @param A - the array/heap
	 * @param i - the index of the node we are looking to sink
	 * @param j - the last index in the array
	 */
	private void sink(TreeNode[] A, int i, int j) {
		TreeNode val = A[i];
		int minChildIndex = -1;
		TreeNode minChild = val;
		int start = i * 2 + 1; // Start index for checking children
		int end = Math.min(i * 2 + 2, j); // End index for checking children

		for (int k = start; k <= end; k++) { // Find min child to swap with
			TreeNode cur = A[k];
			if (cur.weight < minChild.weight) {
				minChild = cur;
				minChildIndex = k;
			}
		}

		if (minChildIndex != -1) {
			A[minChildIndex] = val;
			A[i] = minChild;
			sink(A, minChildIndex, j); // Recursively sink the swapped element until we find the correct position
		}
	}

	/**
	 * Swim method for heaps. It swims a value up in order to maintain a heap
	 * ordering.
	 * 
	 * @param A     - the array/heap
	 * @param start - an index for the node we are looking to swim
	 */
	private void swim(TreeNode[] A, int start) {
		// start is where the last added value is
		if (0 >= start)
			return;

		TreeNode val = A[start];
		int parentIndex = Math.max(Math.floorDiv(start - 1, 2), 0); // End index for checking parent

		if (val.weight < A[parentIndex].weight) { // If the child is less than the parent, swap
			A[start] = A[parentIndex];
			A[parentIndex] = val;
			swim(A, parentIndex); // Recursively swim until a parent with a lesser value is found
		}
	}

	/**
	 * Remove the next node in the queue which has the lowest weight. This is the
	 * node at the front of the queue.
	 * 
	 * @return the TreeNode
	 */
	private TreeNode removeNext() {
		if (queueSize <= 0)
			return null;
		TreeNode node = queue[0];
		queueSize--;
		queue[0] = queue[queueSize];
		queue[queueSize] = null;
		this.sink(queue, 0, queueSize - 1);
		return node;
	}

	private void encode() { // TODO

	}

	public String getEncoding() { // TODO: Change return type later. Needed for GUI
		return message;
	}

	public String decode(String text) { // TODO: Change return type. Needed for GUI
		return text;
	}

	private String queueToString() {
		String s = "[";
		for (TreeNode node : queue) {
			if (node == null)
				s += "null, ";
			else
				s += node.toString() + ", ";
		}
		return s.substring(0, s.length() - 2) + "]";
	}
}
