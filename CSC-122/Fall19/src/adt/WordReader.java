package adt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordReader {

	public static void main(String[] args) {
		Scanner sc = null;
		Set<String> words = new Set<String>();
		try {
			sc = new Scanner(new File("words.txt"));
			while (sc.hasNext()) {
				String s = sc.next();
				if (s.length() > 3)
					words.add(s);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} finally {
			if (sc != null)
				sc.close();
		}
		
		System.out.println("Number of words: " + words.getCurrentSize());
		System.out.println("Does foreign exist: " + words.contains("foreign"));
		System.out.println("Does from exist: " + words.contains("from"));

	}

}
