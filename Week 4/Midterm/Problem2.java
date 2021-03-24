package week4;

import acm.program.*;

/* Problem 2a.
 * Compute the value of each of the following Java expressions.
 * If an error occurs during any of the evaluations,
 * write "Error" on that line and explain briefly why the error occurs.
 * [5.0 / 4 - 4 / 5] 				1.25
 * [7 < 9 -5 && 3 % 0 == 3]			false
 * ["B" + 8 + 4]					"B84"
 * 
 * Problem 2b.
 * The 1st number is: 78
 * The 2nd number is: 73
 */

public class Problem2 extends ConsoleProgram{
	public void run() {
		int num1 = 2;
		int num2 = 13;
		println("The 1st number is: " + Mystery(num1, 6));
		println("The 2nd number is: " + Mystery(num2 % 5, 1 + num1 * 2));
	}
	
	private int Mystery(int num1, int num2) {
		num1 = Unknown(num1, num2);
		num2 = Unknown(num2, num1);
		return(num2);
	}
	
	private int Unknown(int num1, int num2) {
		int num3 = num1 + num2;
		num2 += num3 * 2;
		return(num2);
	}
}
