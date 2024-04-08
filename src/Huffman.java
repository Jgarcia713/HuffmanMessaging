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

		public TreeNode(char theData, int weight) {
			data = theData;
			this.weight = weight;
			left = null;
			right = null;
		}
		
		@Override
		public String toString() {
			return data + " " + weight;
		}
	}

	public static void main(String[] args) {
		Huffman x = new Huffman("text text");
	}

	private TreeNode root;
	private TreeNode[] nodes;

	public Huffman(String text) {
//		root = new TreeNode(' '); // temporary
		/*-
		 * 1. get a count of unique chars 
		 * 2. Create array of treeNodes[] iterate through string again and add to array 
		 * 3. sort the array 
		 * 4. huffman alg for creating a
		 * tree.
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

//		for (int i = 0; i < text.length(); i++) {
//			System.out.println(uniqueChars[i] + " " + frequencies[i]);
//		}
//		System.out.println(uniqueCount);

		nodes = new TreeNode[uniqueCount];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new TreeNode(uniqueChars[i], frequencies[i]);
			System.out.println(nodes[i]);

		}
		

	}

	public void addNode(char data) {

	}

	private void encode() {

	}

	public String decode() {
		return " ";
	}

	@Override
	public String toString() {
		return " ";
	}
}
