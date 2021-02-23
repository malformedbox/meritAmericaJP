package week3;

import acm.program.ConsoleProgram;

public class AddCommasToNumberStrings extends ConsoleProgram {
	
	public void run() {
		while(true) {
			String digits = readLine("Enter a number: ");
			if (digits.equals("0")) break;
			println(addCommasToNumericString(digits));
		}
	}
	public String addCommasToNumericString(String digits) {
		String withCommas = "";
		int digitForComma = 0;
		
		for(int i = digits.length()-1; i >= 0; i--) {
			withCommas = digits.charAt(i) + withCommas;
			digitForComma++;
			
			if((digitForComma % 3 == 0) && (i > 0)) {
				withCommas = "," + withCommas;
			}	
		}
		return withCommas;
	}
	
}
