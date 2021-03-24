package week4;

import acm.program.ConsoleProgram;

public class Problem5 extends ConsoleProgram{
	public void run() {
		while(true) {
			String word = readLine("Enter string you want doubled letters removed from: ");
			if(word.isEmpty())break;
			println(removeDoubledLetters(word));
		}
	}
	public String removeDoubledLetters(String word) {
		String removedDoubled = "";
		for(int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if(i == 0 || ch != word.charAt(i-1)) {
				removedDoubled += ch;
			}
		}
		return removedDoubled;
	}
}
