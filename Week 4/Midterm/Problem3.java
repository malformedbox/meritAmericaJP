package week4;

import acm.program.*;

public class Problem3 extends ConsoleProgram {
	private static final int SENTINEL = 0;
	public void run() {
		
		println("This program finds the two largest integers in a list."
				+ " Enter values, one per line, using a " + SENTINEL 
				+ " to signal the end of the list.");
		int largest = -1;
		int second = -1;
		
		while(true) {
			int number = readInt(" ? ");
			if(number == SENTINEL) break;
			
			if(number > largest) {
				second = largest;
				largest = number;
			}else if(number > second) {
				second = number;
			}
		}
		println("The largest value is " + largest);
		println("The second largest is " + second);
	}
}
