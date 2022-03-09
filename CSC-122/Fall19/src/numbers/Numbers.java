package numbers;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Given a text file of integers,
 * Main method should read the file
 * using try and catch for exceptions.
 * Output the average of the numbers
 * to another file
 * @author Boone Tison
 * Date: 10/02/2019
 */

public class Numbers {
	
	/**
	 * Reads a file of integers
	 * and finds the average
	 * Catches exceptions if file
	 * not found or file contains
	 * variables other than integers
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = null;
		PrintWriter out = null;
		try {
			sc = new Scanner(new File("numbers.txt"));
			double average = averageInt(sc);
			out = new PrintWriter(new File("numbersOutput.txt"));
			out.print(average);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (InputMismatchException e) {
			System.out.println("Invalid file input");
		} finally {
			if (sc != null)
				sc.close();
			if (out != null)
				out.close();
		}
	}
	
	/**
	 * Averages all the integers from the file
	 * @param sc
	 * @return double average of file
	 * @throws InputMismatchException
	 */
	public static double averageInt(Scanner sc) throws InputMismatchException {
		int sum = 0;
		double count = 0;
		while (sc.hasNext()) {
			sum += sc.nextInt();
			count += 1.0;
		}
		return sum / count;
	}
	
	
	
}
