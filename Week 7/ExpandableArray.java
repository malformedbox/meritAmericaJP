package week6;

import acm.program.ConsoleProgram;

/* 1. Primitive vs. Objects
	String name = readLine("Enter name: ");
	Name is the type String. String is an object, to compare Strings, you would need to compare references (use name.equals)
	(name == "Q") would not work because == is used for primitive types (int, double, boolean, char)
	- In the second example, (ch == 'Q') will work because ch is a char, which is a primitive type.
 */

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
