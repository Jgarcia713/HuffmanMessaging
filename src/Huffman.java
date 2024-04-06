
public class Huffman {
	private class TreeNode {
		// Instance variables
		private char data;
		private TreeNode left;
		private TreeNode right;

		TreeNode(char theData) {
			data = theData;
			left = null;
			right = null;
		}
	}

	private TreeNode root;

	public Huffman() {
		root = new TreeNode(' '); // temporary
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
