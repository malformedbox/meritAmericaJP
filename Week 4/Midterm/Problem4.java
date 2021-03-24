package week4;

import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;

public class Problem4 extends GraphicsProgram {
	private GImage frog;
	private double fx, fy;
	
	private static final int SQUARE_SIZE = 75;
	private static final int NROWS = 4;
	private static final int NCOLS = 7;
	
	public static final int WINDOW_WIDTH = NCOLS * SQUARE_SIZE;
	public static final int WINDOW_HEIGHT = NROWS * SQUARE_SIZE;
	
	public void run() {
		frog = new GImage("frog.gif");
		fx = (NCOLS / 2 + 0.5) * SQUARE_SIZE;
		fy = (NROWS - 0.5) * SQUARE_SIZE;
		add(frog, fx - frog.getWidth() / 2, fy - frog.getHeight() / 2);
		addMouseListeners();
	}
	
	public void mouseClicked(MouseEvent e) {
		double mouseX = e.getX();
		double mouseY = e.getY();
		if(Math.abs(mouseX - fx) > Math.abs(mouseY - fy)) {
			if(mouseX > fx) {
				moveFrog(SQUARE_SIZE, 0);
			}else {
				moveFrog(-SQUARE_SIZE, 0);
			}
		}else {
			if(mouseY > fy) {
				moveFrog(0, SQUARE_SIZE);
			}else {
				moveFrog(0, -SQUARE_SIZE);
			}
		}
	}
	
	public void moveFrog(double dx, double dy) {
		if(insideWorld(fx + dx, fy + dy)) {
			fx += dx;
			fy += dy;
			frog.move(dx, dy);
		}
	}
	public boolean insideWorld(double x, double y) {
		return (x >= 0 && x <= NCOLS * SQUARE_SIZE && y >= 0 && y <= NROWS * SQUARE_SIZE);
	}
}
