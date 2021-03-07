package week4;

import stanford.karel.*;

public class Problem1 extends SuperKarel{
	public void run() {
		moveAcrossRow();
		for(int i = 0; i < 4; i++) {
			placeBeepers();
			continueMoving();
		}
	}
	private void moveAcrossRow() {
		turnLeft();
		move();
		turnRight();
	}
	private void placeBeepers() {
		move();
		while(frontIsClear()) {
			if(noBeepersPresent()) {
				putBeeper();
			}
			move();
		}
	}
	private void continueMoving() {
		turnRight();
		move();
		turnRight();
		move();
		turnRight();
	}
}
