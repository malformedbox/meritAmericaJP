package week4;

import java.io.*;

import acm.program.ConsoleProgram;

public class WordCount extends ConsoleProgram {
	
	public void run() {
		int lines = 0;
		int words = 0;
		int chars = 0;
		String fileName = readLine("File: ");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while(true) {
				String line = br.readLine();
				if(line == null) break;		
				lines++;
				words += countWords(line);
				chars += line.length();
			}
			br.close();
		}catch(IOException e) {
			println("File not found. " + e);
		}
		
		println("Lines = " + lines);
		println("Words = " + words);
		println("Chars = " + chars);
	}
	
	private int countWords(String line) {
		boolean aWord = false;
		int words = 0;
		
		for(int i = 0; i< line.length(); i++) {
			char ch = line.charAt(i);
			if(Character.isLetterOrDigit(ch)) {
				aWord = true;
			}else {
				if(aWord) words++;
				aWord = false;
			}
		}
		if(aWord) words++;
		return words;
	}
}
