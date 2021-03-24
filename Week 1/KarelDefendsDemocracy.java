package week1;
import stanford.karel.*;

public class KarelDefendsDemocracy extends SuperKarel {
	public void run() {
	    while (frontIsClear()) { //While condtion allows for Karel to continue proceeding forward even if there are more avenues.
	        move();
	        checkMiddle();
	        cleanExtraBeepers();
	        move();
	    }
	}
	//Check for beepers in middle position that Karel is standing on.
	private void checkMiddle() {
	    if (beepersPresent()) {
	        move();
	        move();
	    }
	}
	//Method that makes Karel check up and down if there are no beepers in the middle.
	private void cleanExtraBeepers() {
	    if (noBeepersPresent()) {
	        checkSouth();
	        checkNorth();
	    }
	}
	//Check for beepers facing upwards if there are no beepers in the middle. Karel then turns around to proceed forward.
	private void checkNorth() {
	    move();
	    if (frontIsClear()) {
	        move();
	    }
	    while (beepersPresent()) {
	        pickBeeper();
	    }
	    turnAround();
	    move();
	    turnLeft();
	}
	//Check for beepers facing downwards if there are no beepers in the middle. Karel then turns around to proceed forward.
	private void checkSouth() {
	    turnRight();
	    move();
	    while (beepersPresent()) {
	        pickBeeper();
	    }
	    turnAround();
	    move();
	}
}
