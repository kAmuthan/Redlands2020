
import java.util.ArrayList;
import java.util.Collections;

public class hw4Tree {

	//attributes
	public hw4Node root; 
	public int count; 
	public int singles; 
	public ArrayList<Integer> list; 
	public int startIndex; 

	public hw4Tree() {
		root = null;
		list = new ArrayList<Integer>();
	}

	public void addNode(int n) {
		assert !found(n) : "This node with the value of " + n + " already exists"; 
		hw4Node temp = new hw4Node(n);
		System.out.println("The node with value of " + n + " has been ADDED to the Binary Search Tree. ");
		list.add(n);
		Collections.sort(list);
		System.out.println("Binary Search Tree hw4Node Values: " + getList());
		count++;
		if (root == null) { //check if tree empty, run scenario
			root = temp;
		}else{ // if the tree is not empty
			hw4Node travel = root; 
			while (true) {
				if (temp.value < travel.value) { 
					if (travel.left != null) { //if search finds an item, go farther
						travel = travel.left;
					}else{
						travel.left = temp; //final position, work from there
						break;
					}
				}else{
					if (travel.right != null) { //if search finds an item, go farther
						travel = travel.right;
					}else{
						travel.right = temp;
						break;
					}
				}
			}
		}
		//post condition
		assert found(n) && !isEmpty() && isBST(): "This node with value of " + n + " wasn't added to the tree correctly. Error";

	}

	public void deleteNode(int num) {
		assert found(num) && !isEmpty() : "The node with the value of " + num + " doesn't exist"; // pre-condition

		// TASK 1: DECLARE REFERENCES TO A CHILD AND PARENT NODE
		
		System.out.println("The node with value of " + num + " has been REMOVED to the Binary Search Tree. ");
		list.remove(Integer.valueOf(num)); 
		Collections.sort(list);
		System.out.println("Binary Search Tree hw4Node Values: " + getList());

		hw4Node toBeRemoved = root;
		hw4Node parent = null;
		boolean found = false;
		count--;

		// TASK 2: LOCATE THE NODE 

		while (!found && toBeRemoved != null) {
			if (num == toBeRemoved.value) {
				found = true;
			} else {
				parent = toBeRemoved;
				if (num > toBeRemoved.value) {
					toBeRemoved = toBeRemoved.right;
				} else {
					toBeRemoved = toBeRemoved.left;
				}
			}
		}

		// TASK 3: IF THE NODE ISN'T FOUND END

		if (!found) {
			count++;
			System.out.println("hw4Node isn't found !");
			return;
		}

		// SCENARIO 1: ONE OF THE SUBTREES IS EMPTY
		if (toBeRemoved.left == null || toBeRemoved.right == null) {
			// TASK 1: IF ONE OF THE CHILD NODES IS EMPTY. USE THE OTHER
			hw4Node theChild;
			if (toBeRemoved.left == null) {
				theChild = toBeRemoved.right;
			} else {
				theChild = toBeRemoved.left;
			}

			// TASK 2: DEAL WITH THE SITUATION IF THE PARENT IS NULL
			if (parent == null) {
				root = theChild;
			} else if (parent.left == toBeRemoved) {
				parent.left = theChild;
			} else {
				parent.right = theChild;
			}
			return;
		}

		// SCENARIO 2: NEITHER SUBTREE IS EMPTY (2 sub child)

		// TASK 1: FIND THE SMALLEST ELEMENT OF THE RIGHT SUBTREE

		hw4Node smallestParent = toBeRemoved;
		hw4Node smallest = toBeRemoved.right;
		while (smallest.left != null) {
			smallestParent = smallest;
			smallest = smallest.left;
		}

		// TASK 2: NOW SMALLEST CONTAINS SMALLEST CHILD IN RIGHT SUBTREE
		// MOVE CONTENTS

		toBeRemoved.value = smallest.value;
		if (smallestParent == toBeRemoved) {
			smallestParent.right = smallest.right;
		} else {
			smallestParent.left = smallest.right;
		}

		assert !found(num) && isBST(): "This node value of " + num
				+ " is found and still exists. hw4Node wasn't deleted properly. Error";

	}

	// IN ORDER TRAVERSAL

	public void displayInOrder() { // method to display in order
		assert !isEmpty() : "There are no nodes that exist";
		startIndex = 1;
		inOrderRecursive(root); 
	}

	public void inOrderRecursive(hw4Node travel) { //in-order display method

		if (travel != null) {
			inOrderRecursive(travel.left);
			System.out.println(startIndex + ". " + travel.toString());
			startIndex++;
			inOrderRecursive(travel.right);
		}
	}

	// HELPER METHODS

	public boolean isEmpty() {
		return root == null;
	}

	public boolean found(int num) { 

		// TASK 1: DECLARE REFERENCES TO A CHILD AND PARENT NODE
		hw4Node n = root;
		hw4Node parent = null;
		boolean found = false;

		// TASK 2: LOCATE THE NODE
		while (!found && n != null) {
			if (num == n.value) {
				found = true; // found is now equal to true
			} else {
				parent = n;
				if (num > n.value) {
					n = n.right;
				} else {
					n = n.left;
				}
			}
		}
		
		if (!found) {
			return found; //found is false
		}

		else {
			return found; 
		}
	}

	public void Counthw4Nodes() {
		System.out.println("Number of nodes: " + count);
	}

	public void countsingleParent(hw4Node travel) {

		if (travel != null) {

			countsingleParent(travel.left); //goes all the way to the left until is equal to null

			if ((travel.left == null && travel.right != null) || (travel.right == null && travel.left != null)) {
				singles++;
				System.out.println("singles parent node is: " + travel.toString());
			}
			countsingleParent(travel.right); 
		}
	}
	
	public boolean isBST() { //checking if it's actually a tree
		return isBSTTraverse(root, Collections.min(list), Collections.max(list));// smallest and largest
	}
	
	public boolean isBSTTraverse(hw4Node travel, int min, int max) {
		if (travel == null) {
			return true;
		}
		if (travel.value < min || travel.value > max) {
			return false;
		}
		return (isBSTTraverse(travel.left, min, travel.value - 1) && isBSTTraverse(travel.right, travel.value + 1, max));
	}
	public ArrayList<Integer> getList() {
		return list;
	}
	public void setList(ArrayList<Integer> list) { 
		this.list = list;
	}
}