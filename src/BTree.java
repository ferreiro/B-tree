
public class BTree {
	Node root;
	

    // internal nodes: only use key and next
    // external nodes: only use key and value
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
     */
	public Entry search(Node x, int key) {
		int i = 1;
		
		// Iterate until we have traverse all elements 
		// or the key is lower or equal than the key to find
		
		while ( i <= x.getNumKeys() && key > x.getKeyAt( i ) )
			i += 1; // Increase "i" (index) value by one

		if ( i <= x.getNumKeys() && key == x.getKeyAt( i ) ) {
			return new Entry(x, i); // Key found!!! Return tuple (Node, index of the found element)
		}
		else if ( x.isLeaf() ) {
			return null; // Key not found :(
		}
		else {
			Node children = x.getChildrenAt( i ); // Coger el array de todos los hijos de este nodo.
			return search( children, key ); // y llamar recursivamente a search con el hijo que tiene índice i
		}
	}
	
}
