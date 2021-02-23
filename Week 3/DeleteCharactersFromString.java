package week3;

import acm.program.ConsoleProgram;

public class DeleteCharactersFromString extends ConsoleProgram {
	
	public void run() {
		/*Alternate version without user input.
		String str1 = "This is a test";
		char char1 = 't';
		String str2 = "Summer is here!";
		char char2 = 'e';
		String str3 = "---0---";
		char char3 = '-';

		println(removeAllOccurrences(str1, char1));
		println(removeAllOccurrences(str2, char2));
		println(removeAllOccurrences(str3, char3));
		*/
		while(true) {
			String line = readLine("Enter a string: ");
			if(line.isEmpty())break;
			String toRemove = readLine("Enter the character you want to remove. (Case Sensitive): ");
			char ch = toRemove.charAt(0);
			println(removeAllOccurrences(line, ch));
		}
	}
	
	public String removeAllOccurrences(String string, char toRemove) {
		String result = "";
		for(int i = 0; i < string.length(); i++) {
			if(string.charAt(i) != toRemove){
				result = result + string.charAt(i);
			}
		}
		return result;
	}
}
