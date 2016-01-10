import java.lang.reflect.Array;
import java.util.Arrays;

public class Node {
	private Node parent;
	private int n; 						// keysSize. total number of keys stored in node
	private int[] keys = null; 			// keys stored in nondecreasing order (key1 < key2 < keyn)
	private Node[] children = null;		// Internal nodes attributes | Pointers to its children ( [!] Leaf nodes have no children. Value undefined)
	private int childrenSize = 0;		// total number of inserted children
	boolean leaf = true;				// Leaf= No childs
	
	public Node(Node parent, boolean leaf, int maxKeySize, int maxChildrenSize) {
        this.parent = parent;
        this.n = 0; // Creating empty node with 0 keys.
        this.keys = new int[maxKeySize + 1];
        this.leaf = leaf;
        
        if ( !leaf ) {
        	// Internal Node.
        	this.children = new Node[maxChildrenSize + 1];
            this.childrenSize = 0;
        }
        else
        	this.children = null;
    }
	
	public boolean isLeaf() {
		return (this.children == null);
	}

	/**
	 * Getters & Setters
	 */
	
	public int getKeyAt( int index ) {
		return this.keys[index-1];
	}
	
	public void setKeyAt( int index, int value ) {
		this.keys[index-1] = value;
	}
	

	public void setLeaf( boolean leaf ) {
		this.leaf = leaf;
	}
	
	public void setN( int n ) {
		this.n = n;
	}
	

	public Node getChildrenAt( int index ) {
		return this.children[index-1];
	}

	public void setChildrenAt( int index, Node child ) {
		this.children[index-1] = child;
		this.childrenSize++;
	}
	
	public int getN() {
		return this.n;
	}
	
	/*
	public void addKey( int value ) {
		if ( keys.length == n ) {
			System.out.println("The key's array is full. Can not add here");
			return;
		}
		keys[n++] = value;
		Arrays.sort(keys, 0, n); // Sort in nondecreasing order (Arguments: array, fromIndex, toIndex)
	}
	*/
	
    @Override
    public String toString() {
    	String s = "";
    	/*
    	s += "size=  " + parent.toString() + "\n";
    	s += "Initial Node=  " + this.n + "\n";
    	
		s += "Keys=  " + "\n"; 
    	for (int i = 0; i < this.keys.length; i++) {
    		s += keys[i] + " ";
    	} 
    	
		s += "\n";
		s += "Children" + "\n"; 
    	for (int i = 0; i < this.children.length; i++) {
    		s += children[i] + " ";
    	} 
		s += "\n";
		
    	s += "maxKeySize=  " + this.children + "\n";
    	s += "minChildrenSize " + this.childrenSize + "\n";
    	s += "maxChildrenSize " + this.leaf + "\n";
    	*/
    	return s;
    }
	
	
}
