package bigOh;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * CSC122 String operations comparison program
 * @author bcatron
 * Fall 2019
 */
public class StringTest {
	public static final int MAX_TRIES = 15000;	//
	public static final double SCALE = 10000000; //scales # downwards

	public static void main(String[] args) {
		long startTime;
		long endTime;
		int loops = 500;
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File("StringTestOutput.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} 
		while (loops < MAX_TRIES) {
			startTime = System.nanoTime();
			String s = "";
			//build a very long string using concatenation of strings
			for (int i=0; i < loops; i++) {
				s = s + i;
			}
			endTime = System.nanoTime();
			writer.print (loops+"," + (endTime-startTime)/SCALE);

			//Now replicate this, but use StringBuilder class
			//and the append method instead
			startTime = System.nanoTime();
			StringBuilder sb = new StringBuilder();
			//build a very long string using StringBuilder
			for (int i=0; i < loops; i++) {
				sb.append(i);
			}
			endTime = System.nanoTime();
			writer.println (loops+"," + (endTime-startTime)/SCALE);
			
			loops = loops + 500;
		}
		writer.close();
	}
}
