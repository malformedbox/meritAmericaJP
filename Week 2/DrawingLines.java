package week2;

import java.awt.event.*;
import acm.graphics.*;
import acm.program.*;

public class DrawingLines extends GraphicsProgram {
	public static final int APPLICATION_HEIGHT = 500;
	public static final int APPLICATION_WIDTH = 500;
	
	private GLine drawLine;
	
	//Add mouse listener when interacting in the window
	public void run() {
		addMouseListeners();
	}
	//When mouse is pressed, the first point is created for the line
	public void mousePressed(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		drawLine = new GLine(x, y, x, y);
		add(drawLine);
	}
	//When mouse is pressed and moved, the end point is created for the line
	public void mouseDragged(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		drawLine.setEndPoint(x, y);
	}
}
