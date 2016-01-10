import java.util.Scanner;

public class Application {
	public static void main(String args[]) {
		
		int n = -1;
		boolean correct = false;
		BTree myTree;
		Scanner in = new Scanner(System.in);
		
		System.out.println("Hi Teacher! Welcome to my program!");
		
		do {
			
			System.out.println("Introduce the n-size for the B-Tree: ");
			n = in.nextInt();
			
			if ( n >= Constants.MINIMUM_KEYS )
				correct = true;
			else
				System.err.println("Hey! The B-tree size must be greater than 2!");
			
		} while ( ! correct );
		
		myTree = new BTree( n );
		
		// TODO
		// Insert elements here...
		// print
		myTree.insert(3);
		myTree.toString();
		myTree.insert(6);
		myTree.toString();
		myTree.insert(5);
		myTree.toString();
		
	}
}
