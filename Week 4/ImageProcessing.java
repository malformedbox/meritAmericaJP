package week4;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;

public class ImageProcessing extends GraphicsProgram {
	//Fixed size for window
	public static final int APPLICATION_HEIGHT = 350;
	public static final int APPLICATION_WIDTH = 500;
	
	public void run() {
		int x = (int)(getWidth() - APPLICATION_WIDTH / 2);
		int y = (int)(getHeight() - APPLICATION_HEIGHT /2);
		
		GImage image = new GImage("Milkmaid.jpg"); //Image placed in Stanford folder for this assignment
		add(image, 0, 0); //Original image
		add(flipHorizontally(image), 250, 0); //Image flipped horizontally
	}
	private GImage flipHorizontally(GImage image) {
		int[][]array = image.getPixelArray();
		int height = array.length;
		int width = array[0].length;
		
		for(int row = 0; row < height; row++) {
			for(int i = 0; i < width /2; i++) {
				int flipped = width - i - 1;
				int temp = array[row][i];
				array[row][i] = array[row][flipped];
				array[row][flipped] = temp;
			}
		}		
		return new GImage(array);
	}	
}
