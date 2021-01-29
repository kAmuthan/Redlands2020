
public class hw4Node {

	// DATA MEMBERS: BINARY TREES HAVE A LEFT AND A RIGHT
	public Integer value;
	public hw4Node left;
	public hw4Node right;

	// EXPLICIT CONSTRUCTOR
	public hw4Node(int n) {
		value = n;
		left = null;
		right = null;
	}

	public String toString() { // when you print the objects
		return "Node value of: " + value + "";
	}
}