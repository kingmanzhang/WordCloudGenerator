
public class KeyWord implements Prioritizable, Comparable<KeyWord> {
	
	private String word;
	private int occurence;
	
	public KeyWord(String word) throws IllegalArgumentException {
		
		if(word == null || word.equals("")) {
			throw new IllegalArgumentException();
		}
		this.word = word.toLowerCase();
		occurence = 0;
		
	}
	
	
	@Override
	public boolean equals(Object other) {
		
		if (other != null && other instanceof KeyWord) {
			KeyWord newKeyWord = (KeyWord) other;
			return word.equals(newKeyWord.getWord());
		}
		return false;
		
	}
	
	public int getOccurrences() {
		
		return occurence;
		
	}
	
	@Override
	public int getPriority() {
		
		return occurence;
		
	}
	
	public String getWord() {
		
		return word;
		
	}
	
	public void increment(){
		
		occurence++;
		
	}

	@Override
	public int compareTo(KeyWord other) {
		
		return word.compareTo(other.getWord());
		
	}
	
	@Override
	public String toString() {
		return this.getWord() + " Occurence " + this.getOccurrences() + 
				" Priority " + this.getPriority();
	}

}
