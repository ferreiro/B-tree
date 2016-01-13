public class BTree {
	
	private Node root = null;	
	public static int t = 0; // t = minimum size Children 	| t - 1 = minimum size of Keys.
							 // 2 * t = maximum size of Children | (2 * t) - 1 = maximum size of keys.

    /**
     * Constructor for B-Tree of ordered parameter. 
     * Order means minimum number of keys in a non-root node.
     * 
     * @param order
     *            of the B-Tree.
     */
	public BTree(int order) {
		this.t = order; // order of the tree
	    this.root = new Node(true, t); // Creating an external node (without Children)
	}

    /**
     * Add value to the tree. Tree can contain multiple equal values.
     * 
     * @param value to add to the tree.
     * @return True if successfully added to tree.
     */
    
    public void insert(int key) {
    	if (this.root.isFullKeys()) {
    		Node newChild = this.root; // copy current root before split it
    		this.root = new Node(false, t); // Create internal node with t value. false= no leaf.
    		this.root.setChildrenAt(1, newChild); // Setting previous root as the first child of new root.
    		this.root = splitChild(root, 1, newChild); // Split child (from new root)
    		insertNonFull(this.root, key); // Insert key in a non full B-tree
    	}
    	else {
    		insertNonFull(this.root, key);
    	}
    }
    
    /*
     * En esta función, cogemos el nodo Y. lo 
     * dividiremos en dos partes.
     * y añadiremos a X una clave que será el nodo que quitemos de Y.
     * 
     * La jugada es que en y, tenemos t CLAVES. Y la idea es coger la clave del centro y subirla al nodo padre.
     * Y lo que queda, guardar en z la mitad de sus claves (t-1) y el resto las tendrá en y.
     * Por último añadiremos estos nodos a x (el nuevo nodo padre).
     */
    public Node splitChild(Node x, int i, Node y) {
    	
    	boolean leaf = y.isLeaf();
    	Node z = new Node(leaf, t); // Será un nodo Hijo de x y tendrá la mitad de las claves de y.
    	z.setNumberKeys(t-1); // Tendrá t-1 claves (que serán las que cojamos de y).
    	
    	for (int j = 1; j <= t-1; j++) {
    		int key_value = y.getKeyAt(j+t); // Cogemos las llaves de y a partir de la mitad :).
    		z.setKeyAt(j, key_value); // Guardamos esas claves de y en el nuevo nodo Z.
    	}
    	
    	if ( !y.isLeaf() ) {
    		// Y no es una hoja, guarda también los hijos correspondientes en Z.
    		for (int j = 1; j <= t; j++) {
    			Node children = y.getChildrenAt(j+t); // Coger el hijo de y a partir de j + t
    			z.setChildrenAt(j, children); // Poner ese hijo en z
    		}
    	}
    	
    	y.setNumberKeys( t-1 ); // La mitad de keys están en y
    	
    	//////////////////////////////////////////////////
    	// Ahora vamos a meter el nuevo hijo en el array del padre (xx)
    	
    	for (int j = x.getN() + 1; j >= i+1; j-- ) {
    		Node children = x.getChildrenAt(j);
    		x.setChildrenAt(j+1, children);
    	}
    	
    	x.setChildrenAt(i+1, z); // Guardar z como hijo de x

    	//////////////////////////////////////////////////
    	// Ahora vamos a dejar un espacio en x para meter la clave
    	// que está a la mitad de y (que es el nodo que queremos partir).
    	// Una vez que dejemos espacio, podremos guardar una llave de y en x
    	
    	for (int j = x.getN(); j >= i; j--) {
    		int key = x.getKeyAt(j);
    		x.setKeyAt(j+1, key); // hacer espacio para guardar una nueva llave en
    	}

    	int middleKeyFromY;
    	middleKeyFromY = y.getKeyAt(t); // coger la llave que está a la mitad de y
    	
    	x.setKeyAt(i, middleKeyFromY); // Guardar la llave que está a la mitad del hijo y en X.
    	x.increaseN(); // Add 1 to number of keys that x have.
    	
    	return x;
    }
    
    private void insertNonFull(Node x, int k) {
		
		int i = x.getN();
		
		if ( x.isLeaf() ) {
			// External Node. NO children.
			
			while ( i >= 1 && k < x.getKeyAt( i ) ) {
				int value = x.getKeyAt( i );
				x.setKeyAt( i + 1 , value );
				i -= 1;
			}

			x.setKeyAt( i + 1 , k );
			x.increaseN();
		}
		else {
			while ( i >= 1 && k < x.getKeyAt( i ) ) {
				i -= 1;
			}
			
			i += 1;
				
			if ( x.getChildrenAt(i).getN() == ((2 * t) - 1) ) {
				x = splitChild( x, i, x.getChildrenAt(i) );
				if ( k > x.getKeyAt(i) )
					i += 1; 
			}
			insertNonFull( x.getChildrenAt(i), k);
		}
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
    
    @Override
    public String toString() {
    	String tree = "";
    	
    	tree += "Number of Nodes: \n";
    	tree += this.root.nodeToString(this.root);
    	
		 for (int i = 1; i <= this.root.getN(); i++) {
			 System.out.print("_" + this.root.getKeyAt(i));
		 }
		 
		 System.out.print("-\n");
		 
		 for (int i = 1; i <= this.root.getN(); i++) {
			 Node c =  this.root.getChildrenAt(i);
			 if ( c == null ) {
				 System.out.print("_L_");
			 }
			 else if ( c.isLeaf() ) {
				 System.out.print("_L_");
			 }
			 else {
				 //printTree(c);
			 }
		 }
    	
    	return tree;
    }  
}
