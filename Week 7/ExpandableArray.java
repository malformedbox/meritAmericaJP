package week6;

import acm.program.ConsoleProgram;

public class ExpandableArray extends ConsoleProgram {
	private Object[] array;
	
	public ExpandableArray() {
		array = new Object[0];
	}
	public void set(int index, Object value) {
		if(index >= array.length) {
			Object[] newArray = new Object[index + 1];
			for(int i = 0; i < array.length; i++) {
				newArray[i] = array[i];
			}
			array = newArray;
		}
		array[index] = value;
	}
	public Object get(int index) {
		if(index >= array.length)return null;
		return array[index];
	}
}
