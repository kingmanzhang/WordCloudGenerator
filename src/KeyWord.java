///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  WordCloudGenerator.java
// File:             KeyWord.java
// Semester:         
//
// Author:           Xingmin Zhang xzhang66@wisc.edu
// CS Login:         (your login name)
// Lecturer's Name:  (name of your lecturer)
// Lab Section:      (your lab section number)
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          None
//                   
//
// Online sources:   none
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * KeyWord defines a class that storing key words. It stores a word and times 
 * of occurrence. 
 * @author Xingmin Zhang
 *
 */
public class KeyWord implements Prioritizable, Comparable<KeyWord> {
	
	private String word; //word of an key
	private int occurrence; //times of occurrence
	
	public KeyWord(String word) throws IllegalArgumentException {
		
		if(word == null || word.equals("")) {
			throw new IllegalArgumentException();
		}
		this.word = word.toLowerCase();
		occurrence = 1; //a key word occurs once when instantiated
		
	}
	
	
	@Override
	/**
	 * A method to determine whether a keyword equals another one. 
	 * @return true if the words are the same. 
	 */
	public boolean equals(Object other) {
		
		if (other != null && other instanceof KeyWord) {
			KeyWord newKeyWord = (KeyWord) other;
			return word.equals(newKeyWord.getWord());
		}
		return false;
		
	}
	
	/**
	 * A method to return the times of occurrence. 
	 * @return the number of occurrence. 
	 */
	public int getOccurrences() {
		
		return occurrence;
		
	}
	
	@Override
	/**
	 * A method to return the priority of a keyword. 
	 * @return the priority (occurrence ) of a keyword. 
	 */
	public int getPriority() {
		
		return occurrence;
		
	}
	
	/**
	 * A method to return the word of a keyword.
	 * @return the word of a keyword. 
	 */
	public String getWord() {
		
		return word;
		
	}
	
	/**
	 * A method to increment the occurrence of an keyword by one.
	 */
	public void increment(){
		
		occurrence++;
		
	}

	@Override
	/**
	 * A method to compare one keyword to another. 
	 * @return string comparison of the words. 
	 */
	public int compareTo(KeyWord other) {
		
		return word.compareTo(other.getWord());
		
	}
	
	@Override
	/**
	 * A method to convert a keyword to string. 
	 * @return the word, its occurrence and its priority as a string. 
	 */
	public String toString() {
		return this.getWord() + " Occurrence " + this.getOccurrences() + 
				" Priority " + this.getPriority();
	}

}
