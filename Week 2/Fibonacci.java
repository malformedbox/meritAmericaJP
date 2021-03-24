package week2;

import acm.program.*;

public class Fibonacci extends ConsoleProgram {
	//Constant for max value to print.
	private static final int MAX_TERM_VALUE = 10000;
	
	public void run() {	
		println("This program lists the Fibonacci sequence.");
		int n1 = 0, n2 = 1;
		
		while(n1 <= MAX_TERM_VALUE) {
			println(n1);
			int n3 = n1 + n2; 
			n1 = n2; //n1 becomes n2, which is the sum from previous sequence
			n2 = n3; //n2 gets replaced with the sum, n3
		}
	}
}
