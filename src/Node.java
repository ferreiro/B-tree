
public class Node {

	public static final int MAX_CHILDREN = 20; // maximum children per node. Constant variable
	
	int n; 			// total number of keys stored in node
	int maxKeys;
	int[] keys; 	// keys stored in nondecreasing order (key1 < key2 < keyn)
	boolean leaf; 	// TRUE= Node is a Leaf | FALSE= Internal node.
	Node[] children; // Pointers to its children ( [!] Leaf nodes have no children. Value undefined)
	
	// Node Constructor:
	// Create a new Node object.
	// Taking into account parameters passed on constructor calling.
	// maximumKeys = total number of keys for this level
	
	public Node(boolean leaf, int maximum_N_Keys ) {
		this.n = 0; // 0 keys stored when creating a new Node.
		this.keys = new int[ maximum_N_Keys ]; // size of the maximum keys for this particular Node.
		this.leaf = leaf;
		this.maxKeys = maximum_N_Keys;
		
		if ( ! leaf ) {
			this.children = new Node[ maximum_N_Keys + 1 ]; // Node has children (and must be 1 more than total number of keys from parent )
		}
		else {
			this.children = null; // [!] Leaf nodes have no children. Value undefined)
		}
	}
	
	/*
	 * Getters method for private attributes. (good programming practise :P)
	 */
	
	public void setN(int n) {
		this.n = n;
	}

	public void setKeyAt(int index, int value) {
		this.keys[index] = value;
	}
	
	public void setChildren(Node[] children) {
		this.children = children;
	}
	
	public void setChildrenAt( Node x, int index ) {
		this.children[index] = x;
	}
	

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}


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
		return this.children[index-1];
	}
	
	// Return the total number of keys for 
	// this Node.
	public int getN() {
		return this.n;
	}

	public Node[] getChildren() {
		return children;
	}
	

	
}
