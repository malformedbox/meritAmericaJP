package week5;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.*;

import acm.graphics.*;
import acm.program.GraphicsProgram;

public class BoxDiagram extends GraphicsProgram {
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	private static final int MAX_NAME = 25;
	
	private JTextField nameBox;
	private JButton addButton;
	private JButton removeButton;
	private JButton clearButton;
	private GObject current;
	private GPoint last;
	
	private Map<String, GObject> objects;
	
	public void init() {
		objects = new HashMap<String, GObject>();
		bottomMenu();
		addActionListeners();
		addMouseListeners();
	}
	//JLabel, JTextField, 3 JButtons: Add, Remove, Clear
	private void bottomMenu() {
		nameBox = new JTextField(MAX_NAME);
		nameBox.addActionListener(this);
		addButton = new JButton("Add");
		removeButton =  new JButton("Remove");
		clearButton = new JButton("Clear");
		
		add(new JLabel("Name"), SOUTH);
		add(nameBox, SOUTH);
		add(addButton, SOUTH);
		add(removeButton, SOUTH);
		add(clearButton, SOUTH);
	}
	//Typing into JTextField -> add creates GRect JLabel box in center in a GCompound
	private void addBox(String name) { //Called in add button action
		GCompound addedBox = new GCompound();
		GRect boxBorder = new GRect(BOX_WIDTH, BOX_HEIGHT);
		GLabel label = new GLabel(name);
		
		addedBox.add(boxBorder, -BOX_WIDTH / 2, -BOX_HEIGHT /2);
		addedBox.add(label, -label.getWidth() / 2, label.getAscent() /2);
		
		add(addedBox, getWidth() /2, getHeight() /2);
		objects.put(name, addedBox);
	}
	private void removeBox(String name) { //Called in remove button action
		GObject toRemove = objects.get(name);
		if(toRemove !=  null) {
			remove(toRemove);
		}
	}
	private void clearAllBoxes() { //Called in clear button action
		Iterator<String> it = objects.keySet().iterator();
		while(it.hasNext()) {
			removeBox(it.next());
		}
		objects.clear();
	}
	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
		current = getElementAt(last);
	}
	public void mouseDragged(MouseEvent e) {
		if(current != null) {
			current.move(e.getX() - last.getX(), e.getY() - last.getY());
			last = new GPoint(e.getPoint());
		}
	}
	public void mouseClicked(MouseEvent e) {
		if(current != null) {
			current.sendToFront();
		}
	}
	public void actionPerformed(ActionEvent e) { //the method actionPerformed is called on from addActionListeners
		Object source = e.getSource();
		if(source == addButton || source == nameBox) {
			addBox(nameBox.getText());
		}else if(source == removeButton) {
			removeBox(nameBox.getText());
		}else if(source == clearButton) {
			clearAllBoxes();
		}
	}
}
