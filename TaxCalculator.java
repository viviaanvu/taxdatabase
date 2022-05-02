/****************************************************************
 * Vivian Vu
 * Team 05 - CMSC355
 ****************************************************************
 * Task Module: Tax Calculator
 * 
 * Description: - program calculates taxes using a year and a 
 * 				  specific tax table
 * Input:       - String year - tax year and file type
 *              - String income - gross income
 * Output:      - String tax - amount of taxes to be paid
 * 
 * Last Edited: 5/5/2021
 ****************************************************************/

/***************************************************
 * ADDED NEW TAX BRACKET TO 2020S
 * ADDED NEW TAX TABLE FOR 2022S, 2022J, 2022H
 ***************************************************/
package Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaxCalculator extends Message {
	/* 
	 * CALCULATE METHOD
	 * parameters   - String year, String income
	 * returns      - String taxCalculation
	 */
	static String calculate(String year, double income) throws FileNotFoundException {
		boolean isFoundFlag = false; 
		boolean isFoundFlag2 = false;
		String taxTableFile = null;
		
		Scanner read = new Scanner(new FileInputStream("tax.txt"));
		while(isFoundFlag==false&&read.hasNext()) {
			String line = read.nextLine();
			String[] taxFile = line.split(",");
			if(line.indexOf(year)!=-1) {
				isFoundFlag = true;
				taxTableFile = taxFile[1];
			}
		}
		if(isFoundFlag==true) {
			Scanner sc = new Scanner(new FileInputStream(taxTableFile));
			while(isFoundFlag2==false&&sc.hasNext()) {
				String line = sc.nextLine();
				String[] taxBracket = line.split(",");
				double percentage = Double.parseDouble(taxBracket[1]);
				double taxCalculation = income*percentage;
				if(taxBracket[0].equals("Over")) {
					isFoundFlag2 = true;
					return "$" + taxCalculation;
				}
				double incomeBracket = Double.parseDouble(taxBracket[0]);
				if(income>0&&income<incomeBracket+1) {
					isFoundFlag2 = true;
					return "$" + taxCalculation;
				}
			}
		}
		//return (messages("MSGENG",903));
		return "ERROR903\n";
	}
	/* 
	 * MAIN METHOD - REMOVE COMMENTS FOR UNIT TESTING
	 * 
	 * public static void main(String[] args) throws FileNotFoundException {
		
		//UNIT TESTING WITH ACTUAL AND EXPECTED VALUES
		System.out.println("Year: 2020S\nIncome: $9875");
		System.out.println("Expected: $987.5");
		System.out.println("Actual: " + calculate("2020S",9875) + "\n");
		
		System.out.println("Year: 2020S\nIncome: $9876");
		System.out.println("Expected: $1185.12");
		System.out.println("Actual: " + calculate("2020S",9876) + "\n");
		
		System.out.println("Year: 2021J\nIncome: $5000000");
		System.out.println("Expected: $1850000.0");
		System.out.println("Actual: " + calculate("2021J",5000000) + "\n");
		
		System.out.println("Year: 2021H\nIncome: $209400");
		System.out.println("Expected: $67008.0");
		System.out.println("Actual: " + calculate("2021H",209400) + "\n");
		
		System.out.println("Year: 1990S\nIncome: $403529");
		System.out.println("Expected: ERROR 903");
		System.out.println("Actual: " + calculate("1990J",403529) + "\n");
		
		//PROMPTING USER INPUT TESTING
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the year followed by filing type(S,J,H): ");
			String year  = sc.nextLine();
			System.out.println("Enter your annual gross income for " + year + ":");
			double income = sc.nextDouble();
			System.out.println("Tax to Be Paid: $" + calculate(year,income));
		}
	}
	 */
	
}
