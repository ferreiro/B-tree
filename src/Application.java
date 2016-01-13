import java.util.Scanner;

public class Application {
	public static void main(String args[]) {
		
		int n = -1;
		boolean correct = false;
		BTree myTree;
		Scanner in = new Scanner(System.in);
		
		/*
		System.out.println("Hi Teacher! Welcome to my program!");
		
		do {
			
			System.out.println("Introduce the n-size for the B-Tree: ");
			n = in.nextInt();
			
			if ( n >= Constants.MINIMUM_KEYS )
				correct = true;
			else
				System.err.println("Hey! The B-tree size must be greater than 2!");
			
		} while ( ! correct );
		*/
		
		n = 4; // There is an example on page 496 from cormen, where the t=4
		myTree = new BTree( n );
		
		System.out.println(myTree.toString());
		
		myTree.insert(3);
		myTree.insert(23);
		myTree.insert(33);
		myTree.insert(43);
		myTree.insert(53);
		myTree.insert(63);
		myTree.insert(80);
		
		myTree.insert(83);

		System.out.println("3 inserted!");
		System.out.println(myTree.toString());
		
		Entry e = myTree.search(myTree.getRoot(), 3);
		
		if (e == null) {
			System.out.print("NUll");
		}
		else {
			
			System.out.println("Index" + e.getIndex());
		}
		
		
				
		/*
		myTree.insert(3);
		myTree.toString();
		myTree.insert(6);
		myTree.toString();
		myTree.insert(5);
		myTree.toString();
		*/
	}
}
