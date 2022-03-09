package word;
/**
 * Models a word so that we can count syllables
 * @author Boone Tison
 * 19 September 2019
 */
public class Word {
	private String text;

	/**
    Constructs a word by removing leading and trailing non-
    letter characters, such as punctuation marks.
    @param s the input string
	 */
	public Word(String s) {
		int i = 0;
		while (i < s.length() && !Character.isLetter(s.charAt(i)))
			i++;
		int j = s.length() - 1;
		while (j > i && !Character.isLetter(s.charAt(j)))
			j--;
		text = s.substring(i, j+1);
	}
	
	/**
    Returns the text of the word, after removal of the
    leading and trailing non-letter characters.
    @return the text of the word
	 */
	public String getText() {
		return text;
	}
	
	/**
    Counts the syllables in the word.
    @return the syllable count
	 */
	public int countSyllables(){
		int count = 0;
		int end = text.length() - 1;
		if (end < 0) 
			return 0; // the empty string has no syllables

		// an e at the end of the word doesn't count as a vowel
		char ch = Character.toLowerCase(text.charAt(end));
		char ch2;
		if (ch == 'e') 
			end--;

		boolean insideVowelGroup = false;
		for (int i = 0; i <= end; i++)
		{
			ch = Character.toLowerCase(text.charAt(i));
			if (i != end) 
				ch2 = Character.toLowerCase(text.charAt(i+1));
			else
				ch2 = 'b';
			if ("aeiouy".indexOf(ch) >= 0 && "aeiouy".indexOf(ch2) < 0) 
			{
				// ch is a vowel
				if (!insideVowelGroup || count > 0)
				{ 
					// start of new vowel group
					count++; 
					insideVowelGroup = true;
				}
			}
		}

		// every word has at least one syllable
		if (count == 0) 
			count = 1;

		return count;      
	}

	//After fixing syllable count...if you finish
	public void otherBuggyCode () {
		//Buggy code Part A
		String y;
		String x = "a test";
		String z = new String ("a");
		z = z + " test";
		y = x;
		if (y.equals(x)) {
			System.out.println("check equality 1");
		}
		if (y.equals(z)) {
			System.out.println("check equality 2");
		}
		
		//buggy code part B
		double length = 15.0000000000000001;
		double area = 10.0 * length;
		int value = 150;
		if (value == area) {
			System.out.println("check equality 3: "+area);
		}
		
		double cratio = circleRatio(78.5398, 5);
		System.out.println("Circle ratio is: "+cratio);

	}
	
	//takes the area of a circle and the radius of that circle and returns the ratio
	public double circleRatio (double area, int radius) {
		double circleRatio = area / (radius*radius);
		return circleRatio;
	}
}