
public class BTree2 {

	Node2 root; // Pointer to the root of the B-tree
	int t; // Order. Every node has at most m children
 
	/*
	 *  Constructor.
	 *  Creates an empty Tree.
	 *  
	 *  n_keys se refiere a qué tipo de arbolB queremos hacer
	 *  (si n_keys = 3, es un arbol de 3 keys por el primer nivel)
	 */
	public BTree2(int order) {
		this.t = order;
		this.root = new Node2(true, order);
	}

	
	public Node2 getRoot() {
		return root;
	}

    private static class Entry {
    	Node2 x;
    	int index; // index of the key in the node.
     
        public Entry(Node2 x, int index) {
        	this.x = x;
        	this.index = index;
        }
    }
	
    /*
     * Search a key given a subtree root.
     * x = tree root.
     * key = value to search
     * 
     * Total cost: t logt n
     */
	public Entry searchTree(Node2 x, int key) {
		int i = 1;
		
		// Iterate until we have traverse all elements 
		// or the key is lower or equal than the key to find
		
		while ( i <= x.getN() && key > x.getKeyAt( i ) )
			i += 1; // Increase "i" (index) value by one

		if ( i <= x.getN() && key == x.getKeyAt( i ) ) {
			return new Entry(x, i); // Key found!!! Return tuple (Node, index of the found element)
		}
		else if ( x.isLeaf() ) {
			return null; // Key not found :(
		}
		else {
			Node2 children = x.getChildrenAt( i ); // Coger el array de todos los hijos de este nodo.
			return searchTree( children, key ); // y llamar recursivamente a search con el hijo que tiene índice i
		}
	}
	
	public void insert(int key) {
		Node2 r = this.root; // r == root
		int t = this.t;
		
		if ( r.getN() ==  ((2 * t) - 1) ) {
			
			Node2 s = new Node2(false, this.t );
			this.root = s;
			
			s.setChildrenAt(r, 1);
			s = splitChild(s, 1);
			s = insertNonFull( s, key  );
		}
		else {
			insertNonFull( r, key );
		}
	}
	
	private Node2 insertNonFull(Node2 x, int k) {
		
		int i = x.getN();
		int t = this.t; 
		
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
		
		return x;
	}

	private void setRoot(Node2 s) {
		// TODO Auto-generated method stub
		this.root = s;
	}

	/*
	 * In this function you pass the node you want to split
	 * and the ith position of the child where you want to split
	 * This should be the middle of the keys of Node X.
	 */
	public Node2 splitChild( Node2 x, int i ) {
		int t, n;
		boolean leaf;
		
		Node2 y = x.getChildrenAt( i ); // Getting ith children on node x 
		Node2 z = new Node2( y.isLeaf(), this.t );
		
		t = this.t; // Set "t" using N variable from y
		
		n = ( t - 1 ); // nChildren = (keys of Y) - 1
		z.setN(n);
		
		for (int j = 1; j <=  t - 1; j++) {
			int index_children = ( j + t );
			int newKey = y.getKeyAt( index_children ); // Get value from the Y
			z.setKeyAt(j, newKey); // Set value from Y on Z using j position
		}
		
		if ( ! y.isLeaf() ) {
			for (int j = 1; j <= ( t ); j++) {
				int index_children = ( j + t );
				Node2 newChildren = y.getChildrenAt( index_children );
				z.setChildrenAt( newChildren, j );
			}
		}
		
		y.setN( t - 1 );
		
		for (int j = (x.getN() + 1); j >= ( i + 1 ); j--) {
			Node2 children = x.getChildrenAt( j );
			x.setChildrenAt( children, j + 1 ); // Swap to the right
		}
		
		x.setChildrenAt( z, i + 1 );
		
		for (int j = x.getN(); j >= ( i ); j--) {
			int key =  x.getKeyAt( j );
			x.setKeyAt( key, j + 1 );
		}
		
		int t_key = y.getKeyAt(t);
		x.setKeyAt(i, t_key); // linea 16
		x.setN( 1 + x.getN() ); // linea 17
		
		return x;
	}
	
	// TODO
	// toString function to print the whole tree for testing purposes
	
	public String toString() {
		String tree = "";
		 
		 printTree(root);
		 
		 for (int i = 1; i <= root.getN(); i++) {
			 System.out.print("_" + root.getKeyAt(i));
		 }
		System.out.print("\n-------\n");
		//printTree(this.root);
		
		return tree;
	}
	private void printTree(Node2 node) { 
		 if (node == null) return;
		 
		 for (int i = 1; i <= node.getN(); i++) {
			 System.out.print("_" + node.getKeyAt(i));
		 }
		 
		 System.out.print("-\n");
		 
		 for (int i = 1; i <= node.getN(); i++) {
			 Node2 c = node.getChildrenAt(i);
			 if ( c == null ) {
				 System.out.print("_L_");
			 }
			 else if ( c.isLeaf() ) {
				 System.out.print("_L_");
			 }
			 else {
				 printTree(c);
			 }
		 }
	} 
	
}
