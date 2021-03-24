package week2;

import acm.graphics.GCompound;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import java.awt.*;

public class RobotFace extends GraphicsProgram {
	public static final int APPLICATION_WIDTH = 410; 
	public static final int APPLICATION_HEIGHT = 200;
	
	private static final int HEAD_WIDTH = 100;
	private static final int HEAD_HEIGHT = 150;
	private static final int EYE_RADIUS = 8;
	private static final int MOUTH_WIDTH = 60;
	private static final int MOUTH_HEIGHT = 20;
	
	public void run() {
		double width = getWidth();
		double height = getHeight();
		
		//GRect head = new GRect(x, y, width, height)
		GRect head = new GRect((width - HEAD_WIDTH) / 2, (height - HEAD_HEIGHT) / 2, HEAD_WIDTH, HEAD_HEIGHT);
		head.setFilled(true);
		head.setFillColor(Color.GRAY);
		
		//set horizontally a quarter of the head width
		GOval leye = makeEye(width/2 - HEAD_WIDTH/4, height/2 - HEAD_HEIGHT/4);
		GOval reye = makeEye(width/2 + HEAD_WIDTH/4, height/2 - HEAD_HEIGHT/4);		
		
		//GRect head = new GRect(x, y, width, height)
		GRect mouth = new GRect((width - MOUTH_WIDTH) / 2, (height - MOUTH_HEIGHT) / 2 + HEAD_HEIGHT / 4, MOUTH_WIDTH, MOUTH_HEIGHT);
		mouth.setFilled(true);
		mouth.setFillColor(Color.WHITE);
		
		//Face
		GCompound face = new GCompound();
		//Add components to the face
		face.add(head);
		face.add(leye);
		face.add(reye);
		face.add(mouth);
		add(face);
	}
	private GOval makeEye(double ex, double ey) {
		double x = ex - EYE_RADIUS;
		double y = ey - EYE_RADIUS;
		//GOval eye = new GOval(x-r, y-r, 2*r, 2*r);
		GOval eye = new GOval(x, y, 2 * EYE_RADIUS, 2 * EYE_RADIUS);
		eye.setFilled(true);
		eye.setColor(Color.YELLOW);
		return eye;
	}
}
