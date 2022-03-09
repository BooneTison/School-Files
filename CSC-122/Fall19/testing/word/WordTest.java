package word;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Test suite for Word syllable count program
 * and debugging lab
 * @author Boone Tison
 * 19 September 2019
 */
public class WordTest {

	@Test
	public void testOneSyllable() {
		Word w = new Word("Cat");
		int count = w.countSyllables();
		assertTrue ("Cat :", count == 1 );
		
		w = new Word("Ape");
		count = w.countSyllables();
		assertTrue ("Ape :", count == 1 );
		
		w = new Word("twelve");
		count = w.countSyllables();
		assertTrue("Twelve", count == 1);
	}
	
	@Test
	public void testTwoSyllable() {
		Word w = new Word("human");
		int count = w.countSyllables();
		assertTrue ("Human", count == 2);
	}
	
	@Test
	public void testThreeSyllable() {
		Word w = new Word("inferno");
		int count = w.countSyllables();
		assertTrue ("Inferno", count == 3);
	}
	
	@Test
	public void testFourSyllable() {
		Word w = new Word("alligator");
		int count = w.countSyllables();
		assertTrue ("Alligator", count == 4);
	}
	
	@Test
	public void testFiveSyllable() {
		Word w = new Word("Unidentified");
		int count = w.countSyllables();
		assertTrue ("Unidentified", count == 5);
	}
	
	@Test
	public void testDoubleVowel() {
		Word w = new Word("bookish");
		int count = w.countSyllables();
		assertTrue("Bookish", count == 2);
	}
	
	public void testChallenges() {
		Word w = new Word("beautiful");
		int count = w.countSyllables();
		assertTrue("Beautiful", count == 4);
		
		w = new Word("Australia");
		count = w.countSyllables();
		assertTrue("Australia", count == 4);
		
	}
	
	@Test
	public void testBuggyCode() {
		Word w = new Word("AAA");
		w.otherBuggyCode();
	}


}