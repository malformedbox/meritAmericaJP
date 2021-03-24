package week2;

import acm.program.ConsoleProgram;
/** True/False question in Section Assignment #3
 * a. The value of a local variable named i has no direct relationship with that of a variable named i in its caller.
 * 		True
 * b. The value of a parameter named x has no direct relationship with that of a variable named x in its caller.
 * 		True
 */

public class Hogwarts extends ConsoleProgram {
	public void run() {
		bludger(2001);
	}
	public void bludger(int y) {
		int x = y / 1000;
		int z = (x + y);
		x = quaffle(z,y);
		println("bludger: x = " + x + ", y = " + y + ", z = " + z);
	}
	private int quaffle(int x, int y) {
		int z = snitch(x + y, y);
		y /= z;
		println("quaffle: x = " + x + ", y = " + y + ", z = " + z);
		return z;
	}
	private int snitch (int x, int y) {
		y = x / (x % 10);
		println("snitch: x = " + x + ", y = " + y);
		return y;
	}
}
