
public class BTree {
	
	// Elements for a BTree
	private Node root = null;
	private int size = 0; // number of Nodes in the tree

	// Basic configuration of Tree Nodes
	private int minKeySize; // min elements of a Key
	private int maxKeySize; // maximum number of key elements
	private int minChildrenSize; // min children depending on key total size
	private int maxChildrenSize; // max Children accepted per Key
	
	/* 
	 * Constructor. Create an empty Binary Tree
	 * with the order pass by value.
	 */
	public BTree(int order) {
	    this.minKeySize = order;
	    this.minChildrenSize = minKeySize + 1;
	    this.maxKeySize = 2 * minKeySize;
	    this.maxChildrenSize = maxKeySize + 1;
	    this.root = new Node(null, true, maxKeySize, maxChildrenSize);
	}
	
	/**
	 * Operations
	 * 
	 * Search: Look for an element in the tree.
	 * Insert: Add an element to the tree. We accept duplicates values
	 * Delete: Erase one element from tree
	 */

    /**
     * Does the tree contain the value.
     * 
     * @param value to locate in the tree.
     * @return True if tree contains value.
     */
    public Entry search(Node x, int key) {
    	int i = 1;
    	
    	while ( i <= x.getN() && key > x.getKeyAt( i ) )
			i += 1; // Increase "i" (index) value by one

		if ( i <= x.getN() && key == x.getKeyAt( i ) ) {
			return new Entry(x, i); // Key found!!! Return tuple (Node, index of the found element)
		}
		else if ( x.isLeaf() ) {
			return null; // Key not found :(
		}
		else {
			Node c = x.getChildrenAt( i ); // Get children from "x" at position "i"
			return search( c, key ); // Llamar recursivamente a search con el hijo que está en el índice i
		}
    }

    /**
     * Add value to the tree. Tree can contain multiple equal values.
     * 
     * @param value to add to the tree.
     * @return True if successfully added to tree.
     */
    public void insert(int key) {
    	Node r = this.root; // r == root
    	int t = minChildrenSize;
    			
		if ( r.getN() ==  ((2 * t) - 1) ) {
			
			// Split the tree in 2 trees.
			// s is going to be the parent for the 2 divided treess.
			
			Node s = new Node(null, false, this.maxKeySize, this.maxChildrenSize);
			this.root = s; // s 
			
			s.setLeaf(false); // Root is not a leaf...
			s.setN(0); // parent doesn't have any key. Only childs
			s.setChildrenAt(1, r);
			// TODO: set parent for r (now the parent is s)
			
			s = splitChild( s, 1 );
			s = insertNonFull( s, key );
		}
		else {
			insertNonFull( r, key );
		}
    }
    
    private Node insertNonFull(Node x, int k) {
		
		int i = x.getN();
		int t = this.minKeySize; 
		
		if ( x.isLeaf() ) {
			// External Node. NO children.
			
			while ( i >= 1 && k < x.getKeyAt( i ) ) {
				int value = x.getKeyAt( i );
				x.setKeyAt( i + 1 , value );
				i -= 1;
			}

			x.setKeyAt( i + 1 , k );
			x.setN( x.getN() + 1 );
		}
		else {
			while ( i >= 1 && k < x.getKeyAt( i ) ) {
				i -= 1;
			}
			
			i += 1;
				
			if ( x.getChildrenAt(i).getN() == ((2 * t) - 1) ) {
				x = splitChild( x, i );
				if ( k > x.getKeyAt(i) )
					i += 1; 
			}
			x = insertNonFull( x.getChildrenAt(i), k);
		}
		
		this.size++; // We have insert one node.
		return x;
	}

    public Node splitChild(Node x, int i) {
    	int t, n;
		boolean leaf;
		
		t = this.minChildrenSize; // Set "t" using N variable from y

		Node z = new Node(x, true, this.minKeySize, this. minChildrenSize);
		Node y = x.getChildrenAt( i ); // Getting ith children on node x 
	
		z.leaf = y.leaf;
		z.setN(t - 1);
		
		for (int j = 1; j <=  t - 1; j++) {
			int newKey = y.getKeyAt( j + t ); // Get value from the Y
			z.setKeyAt(j, newKey); // Set value from Y on Z using j position
		}
		
		if ( ! y.isLeaf() ) {
			for (int j = 1; j <= t; j++) {
				Node newChildren = y.getChildrenAt( j + t );
				z.setChildrenAt( j, newChildren );
			}
		}
		
		y.setN( t - 1 );
		
		for (int j = (x.getN() + 1); j >= ( i + 1 ); j--) {
			x.setChildrenAt( j + 1, x.getChildrenAt(j) ); // Swap to the right
		}
		
		x.setChildrenAt( i + 1, z );
		
		for (int j = x.getN(); j >= ( i ); j--) {
			int key =  x.getKeyAt( j );
			x.setKeyAt( key, j + 1 );
		}
		
		int t_key = y.getKeyAt(t);
		x.setKeyAt(i, t_key); // linea 16
		x.setN( 1 + x.getN() ); // linea 17
		
		return x;
    }
    
    /**
     * Remove first occurrence of value in the tree.
     * 
     * @param value to remove from the tree.
     * @return value removed from tree. (-1 if not found)
     */
    public int remove(int key) {
    	return -1;
    }
    
    public Node getRoot() {
    	return this.root;
    }
    
    @Override
    public String toString() {
    	String s = "";

    	s += "size=  " + size + "\n";
    	s += "Initial Node= \n";
    	
    	// System.out.println(root.toString());
    	
    	s += "minKeySize=  " + minKeySize + "\n";
    	s += "maxKeySize=  " + maxKeySize + "\n";
    	s += "minChildrenSize " + minChildrenSize + "\n";
    	s += "maxChildrenSize " + maxChildrenSize + "\n";
    	
    	return s;
    }
    
}
