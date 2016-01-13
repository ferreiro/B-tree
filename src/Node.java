
public class Node {

	private int t; // Minimum size
	private int n; // keysSize. total number of keys stored in node
	private int[] keys = null;  // keys stored in nondecreasing order (key1 < key2 < keyn)
	private Node[] children = null; // Vector of Nodes - Internal nodes attributes | Pointers to its children ( [!] Leaf nodes have no children. Value undefined)
	private int nChildren;
	boolean leaf = true;				// Leaf= No childs
	
	public Node(boolean leaf, int t) {
		int maxKeySize, maxChildrenSize;
		
		maxKeySize = (2 * t) - 1;
		maxChildrenSize = (2 * t);
		
		this.t = t;
		this.n = 0;
		this.nChildren = 0;
		this.leaf = leaf;
		this.keys = new int[maxKeySize];
		this.children = new Node[maxChildrenSize];
	}
	
	public Node(boolean leaf, int maxKeySize, int maxChildrenSize) {
        this.n = 0; // Creating empty node with 0 keys.
        this.nChildren = 0;
        this.leaf = leaf;
		this.keys = new int[maxKeySize];
		this.children = new Node[maxChildrenSize];
    }

	///////////////////////////////////////////////
	///////////// Auxiliary functions /////////////
	///////////////////////////////////////////////

	public boolean isFullChildren() {
		return (n == (2 * t));
	}
	
	public boolean isFullKeys() {
		return (n == ((2 * t) - 1));
	}
	
	public void increaseN() {
		this.n++;
	}
	
	public int getN() {
		return this.n;
	}

	public boolean isLeaf() {
		return this.leaf;
	}
	
	///////////////////////////////////////////////
	///////////// Getters and Setters /////////////
	///////////////////////////////////////////////

	// Keys. Get and set one key in the array.

	public void setNumberKeys( int n ) {
		this.n = n; 
	}
	
	public void setKeyAt( int index, int value ) {
		this.keys[index-1] = value;
	}

	public int getKeyAt( int index ) {
		return this.keys[index-1];
	}

	// Children. Get and set one children in the array.

	public Node getChildrenAt( int index ) {
		return this.children[index-1];
	}

	public void setChildrenAt( int index, Node children ) {
		this.children[index-1] = children;
		this.nChildren++;
	}
	
	// Leaf

	public void setLeaf( boolean leaf ) {
		this.leaf = leaf;
	}
	
	///////////////////////////////////////////////
	////////////////// Printers ///////////////////
	///////////////////////////////////////////////
	
	public String nodeToString(Node n) {
		String nodeStr = "";
		
		// Print Keys (if there are keys)
		if (n.getN() > 0) {
			for (int i = 0; i < n.getN(); i++) {
				nodeStr += n.getKeyAt(i);
				nodeStr += " ,";
			}
		}
		
		for (int i = 0; i < n.nChildren; i++) {
			if (n.isLeaf()) {
				nodeStr += nodeToString(n.children[i]);
			}
			nodeStr += n.getKeyAt(i);
			nodeStr += " ,";
		}
		
		return nodeStr;
	}
	
    @Override
    public String toString() {
    	String s = nodeToString(this);
    	return s;
    }
	
	
}
