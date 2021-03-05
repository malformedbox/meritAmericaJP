package week4;

import acm.program.ConsoleProgram;
import java.util.*;

public class UniqueNames extends ConsoleProgram {
	
	public void run() {
		ArrayList<String> list = new ArrayList<>();
		while(true) {
			String name = readLine("Enter name: ");
			if(name.equals("")) break;
			if(!list.contains(name)) { //If name is not on the list, add
				list.add(name);
			}
		}
		println("Unique name list contains: ");
		printList(list);
	}
	
	private void printList(ArrayList list) {
		for(int i = 0; i < list.size(); i++) {
			println(list.get(i));
		}
	}
}
