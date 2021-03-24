package week2;

import acm.graphics.*;
import acm.program.*;
import acm.util.RandomGenerator;

public class RandomCircles extends GraphicsProgram {
	/* Some of the random circles generated might be white colored,
	 * so it won't be visible with the white background. While some circles might overlap each other.
	 */
	
	//Sets a fixed size of the application window
	public static final int APPLICATION_WIDTH = 500;
	public static final int APPLICATION_HEIGHT = 500;
	//Constant for max # of circles
	private static final int NUMBER_OF_CIRCLES = 10;
	//Constant for minimum radius and maximum radius of the circles
	private static final int MIN_RADIUS = 5, MAX_RADIUS = 50;
	//Instance variable of RandomGenerator
	private RandomGenerator rand = RandomGenerator.getInstance();
	
	public void run() {
		for(int i = 0; i < NUMBER_OF_CIRCLES; i++) {
			double radius = rand.nextDouble(MIN_RADIUS, MAX_RADIUS);
			double x = rand.nextDouble(0, getWidth() - 2 * radius);
			double y = rand.nextDouble(0, getHeight() - 2 * radius);
			
			GOval circle = new GOval(x, y, 2 * radius, 2 * radius);
			circle.setFilled(true);
			circle.setColor(rand.nextColor());
			add(circle);
		}
	}
}
