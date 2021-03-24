package week4;

import java.io.*;
import java.util.ArrayList;

import acm.program.ConsoleProgram;
import acm.util.ErrorException;

public class Histograms extends ConsoleProgram {
	private static final String SCORES_FILE = "MidtermScores.txt";
	private int[] histogramArray;
	
	public void run() {
		makeHistogramArray();
		readScores();
		printRangesAndHistogram();
	}
	private void makeHistogramArray() {
		histogramArray = new int[11];
		for(int i = 0; i <= 10; i++) {
			histogramArray[i] = 0;
		}
	}
	//method for reading the lines
	private void readScores(){
		try {
			BufferedReader read = new BufferedReader(new FileReader(SCORES_FILE));
			while(true) {
				String line = read.readLine();
				if(line == null) break;
				int score = Integer.parseInt(line);
				if(score < 0 || score > 100) {
					throw new ErrorException("Score out of range.");
				}else {
					int range = score /10;
					histogramArray[range]++;
				}
			}
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
	private void printRangesAndHistogram() {
		for(int scores = 0; scores <= 10; scores++) {
			String range;
			switch(scores) {
				case 0: range = "00 - 09"; break;
				case 100: range = " 100"; break;
				default: range = (10 * scores) + " - " + (10 * scores + 9); break;
			}
			String stars = createStars(histogramArray[scores]);
			println(range + ": " + stars);
		}
	}

	private String createStars(int i) {
		String stars = "";
		for(int j = 0; j < i; j++) {
			stars += "*";
		}
		return stars;
	}
}
