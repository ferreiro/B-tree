
public class Node {

	public static final int MINIMUM_KEYS = 2; // minimun keys a node must have.
	public static final int MAX_CHILDREN = 20; // maximum children per node. Constant variable

	int n; 	// total number of keys stored in node
	int keys[]; 	// keys stored in nondecreasing order (key1 < key2 < keyn)
	boolean leaf; 	// TRUE= Node is a Leaf | FALSE= Internal node.
	Node children[]; // Pointers to its children ( [!] Leaf nodes have no children. Value undefined)
	
	/*
	 * Node Constructor:
	 * Create a new Node object.
	 * Taking into account parameters passed on constructor calling.
	 */
	public Node(boolean leaf, int totalKeys) {
		this.n = totalKeys;
		this.keys = new int[ totalKeys ]; // Empty array of Integer's
		this.leaf = leaf; // Setting boolean variable with value passed by parmeter.
		
		if ( leaf ) {
			this.children = null; // [!] Leaf nodes have no children. Value undefined)
		}
		else {
			this.children = new Node[ totalKeys + 1 ]; // Children has one more key
		}
	}
	
	/*
	 * Getters method for private attributes. (good programming practise :P)
	 */
	

	public boolean isLeaf() { 
		return this.leaf;
	}
	
	// Get the key value on a given index position
	// passed by parameter.
	public int getKeyAt( int index ) {
		if ( index < 0 || index > n ) {
			return -1; // Error. Index out of the array limits
		}
		return this.keys[index];
	}
	
	public Node getChildrenAt( int index ) {
		return this.children[index];
	}
	
	// Return the total number of keys for 
	// this Node.
	public int getNumKeys() {
		return this.n;
	}

	
}
