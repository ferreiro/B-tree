import java.util.Scanner;

public class Application {
	public static void main(String args[]) {
		
		BTree myTree;
		int n = -1, toInsert = -1;
		
		n = 4; // There is an example on page 496 from cormen, where the t=4
		toInsert = 100000;
		myTree = new BTree( n );
		
		long time = System.nanoTime(); // Start counting
		
		for (int i = 0; i < toInsert; i++) {
			double newKey = Math.random();
			myTree.insert((int) newKey);
		}
		
		time = System.nanoTime() - (time); // Stop counting time
		System.out.println("Time slaped " + time);
		
		System.out.println(toInsert + " inserted!");
		//System.out.println(myTree.toString());
		

		/*
		boolean correct = false;
		System.out.println("Hi Teacher! Welcome to my program!");
		Scanner in = new Scanner(System.in);
		
		do {
			
			System.out.println("Introduce the n-size for the B-Tree: ");
			n = in.nextInt();
			
			if ( n >= Constants.MINIMUM_KEYS )
				correct = true;
			else
				System.err.println("Hey! The B-tree size must be greater than 2!");
			
		} while ( ! correct );
		*/
		
	}
}
