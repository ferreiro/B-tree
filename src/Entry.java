
public class Entry {
	Node x; // node
	int index; // index of the key in that node.
 
    public Entry(Node x, int index) {
    	this.x = x;
    	this.index = index;
    }
    
    public int getIndex() {
    	return this.index;
    }
    
    public Node getNode() {
    	return this.x;
    }
}
