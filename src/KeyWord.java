
public class KeyWord implements Prioritizable {
	
	private String word;
	private int occurence;
	
	public KeyWord(String word) throws IllegalArgumentException {
		
		if(word == null || word.equals("")) {
			throw new IllegalArgumentException();
		}
		this.word = word.toLowerCase();
		occurence = 0;
		
	}
	
	public int compareTo(KeyWord other) {
		
		return word.compareTo(other.getWord());
		
	}
	
	@Override
	public boolean equals(Object other) {
		
		if (other instanceof KeyWord) {
			KeyWord newKeyWord = (KeyWord) other;
			return this.word.equals(newKeyWord.getWord());
		}
		return false;
		
	}
	
	public int getOccurrences() {
		
		return occurence;
		
	}
	
	public int getPriority() {
		
		return occurence;
		
	}
	
	public String getWord() {
		
		return word;
		
	}
	
	public void increment(){
		
		occurence++;
		
	}

}
