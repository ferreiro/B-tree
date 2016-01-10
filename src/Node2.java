
public class Node2 {

	int n; 			// total number of keys stored in node
	// int maxKeys;	// Maximum number of keys that this node can have (>= 2).
	int[] keys; 	// keys stored in nondecreasing order (key1 < key2 < keyn)
	boolean leaf; 	// TRUE= Node is a Leaf | FALSE= Internal node.
	
	Node2[] children; // Internal nodes attributes | Pointers to its children ( [!] Leaf nodes have no children. Value undefined)
	
	/*
	 * Constructor.
	 * Creates a new node. If is an internal Node, creates a list of children
	 * if the node is external (no children) initializes the children to null.
	 */

	public Node2(boolean leaf, int t) {
		this.n = 0;
		this.keys = new int[ t ]; 			   // List of keys for this node
		this.leaf = leaf;
		
		if ( leaf ) 
			children = null; // External Node (leaf). NO CHILDREN!!Leaf nodes have no children. So children attribute is null
		else
			// Internal node.
			// At least t children
			// at most 2t children
			children = new Node2[ t ]; // Internal Node. It has childrens! Must be 1 unit greater than the total number of keys.
	}
	
	/*
	public Node(boolean leaf, int maxKeys) {
		this.n = 0;
		//this.maxKeys = ((maxKeys >= 2) ? maxKeys : 2); // When maxKeys is lower than 2, we set the default keys a node must have 2. If the value is correct (>=2) we set the given value
		this.keys = new int[ maxKeys ]; 			   // List of keys for this node
		
		if ( leaf ) children = null; // External Node (leaf). NO CHILDREN!!Leaf nodes have no children. So children attribute is null
		else children = new Node[ maxKeys + 1 ]; // Internal Node. It has childrens! Must be 1 unit greater than the total number of keys.
	}
	*/
	
	/*
	 * Getters method for private attributes. (good programming practise :P)
	 */
	
	public void setN(int n) {
		this.n = n;
	}

	public void setKeyAt(int index, int value) {
		this.keys[index-1] = value;
	}
	
	public void setChildren(Node2[] children) {
		this.children = children;
	}
	
	public void setChildrenAt( Node2 x, int index ) {
		this.children[index-1] = x;
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
/*		if ( index < 0 || index > n ) {
			return -1; // Error. Index out of the array limits
		}*/
		return this.keys[index-1];
	}
	
	public Node2 getChildrenAt( int index ) {
		if ( this.children == null ) {
			return null;
		}
		return this.children[index-1];
	}
	
	// Return the total number of keys for 
	// this Node.
	public int getN() {
		return this.n;
	}
	/*
	public int getMaxKeys() {
		return this.maxKeys;
	}
	*/
	public Node2[] getChildren() {
		return children;
	}
	

	
}
