
public class BTree {
	
	public Node getRoot() {
		return root;
	}

	Node root;
	
	
	/*
	 *  Constructor.
	 *  Creates an empty Tree.
	 *  
	 *  n_keys se refiere a qué tipo de arbolB queremos hacer
	 *  (si n_keys = 3, es un arbol de 3 keys por el primer nivel)
	 */
	public BTree(int n_keys) {
		boolean leaf = true; // We don't have any children when creating the first root.
		this.root = new Node( leaf, n_keys ); // Creating root. true=  and second parameter is the total number of keys can have (2 at the beginning)
	}

    private static class Entry {
    	Node x;
    	int index; // index of the key in the node.
     
        public Entry(Node x, int index) {
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
	public Entry searchTree(Node x, int key) {
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
			Node children = x.getChildrenAt( i ); // Coger el array de todos los hijos de este nodo.
			return searchTree( children, key ); // y llamar recursivamente a search con el hijo que tiene índice i
		}
	}
	
	public void insert(int key) {
		Node r = this.getRoot(); // r == root
		int t = r.getN();
		
		if ( r.getN() ==  ((2 * t) - 1) ) {
			
			Node s = new Node(false, root.getMaxKeys() );
			this.root = s;
			
			s.setChildrenAt(r, 1);
			s = splitChild(s, 1);
			s = insertNonFull( s, key  );
		}
		else {
			insertNonFull( r, key  );
		}
	}
	
	private Node insertNonFull(Node x, int k) {
		
		int i = x.getN();
		
		if ( x.isLeaf() ) {
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
			
			int t = x.getN(); 
			
			if ( x.getChildrenAt(i).getN() == ((2 * t) - 1) ) {
				x = splitChild( x, i );
				if ( k > x.getKeyAt(i) )
					i += 1; 
			}
			x = insertNonFull( x.getChildrenAt(i), k);
		}
		
		return x;
	}

	private void setRoot(Node s) {
		// TODO Auto-generated method stub
		this.root = s;
	}

	/*
	 * In this function you pass the node you want to split
	 * and the ith position of the child where you want to split
	 * This should be the middle of the keys of Node X.
	 */
	public Node splitChild( Node x, int i ) {
		int t, n;
		boolean leaf;
		
		Node y = x.getChildrenAt( i ); // Getting ith children on node x 
		Node z = new Node( y.isLeaf(), y.getMaxKeys() );
		
		t = y.getMaxKeys(); // Set "t" using N variable from y
		
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
				Node newChildren = y.getChildrenAt( index_children );
				z.setChildrenAt( newChildren, j );
			}
		}
		
		y.setN( t - 1 );
		
		for (int j = (x.getN() + 1); j >= ( i + 1 ); j--) {
			Node children = x.getChildrenAt( j );
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
	private void printTree(Node node) { 
		 if (node == null) return;
		 
		 for (int i = 1; i <= node.getN(); i++) {
			 System.out.print("_" + node.getKeyAt(i));
		 }
		 
		 System.out.print("-\n");
		 
		 for (int i = 1; i <= node.getN(); i++) {
			 Node c = node.getChildrenAt(i);
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
