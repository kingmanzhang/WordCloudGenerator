import java.io.File;

public class WordCloudGenerator {
	static void main(String[] args) {
		if(args.length != 4) {
			System.out.println("Four arguments required: "
					+ "inputFileName outputFileName ignoreFileName maxWords");
			return;
		} else {
			File inputFile = new File(args[0]);
			File ignoreFile = new File(args[2]);
			if(! (inputFile.exists() && inputFile.canRead())) {
				System.out.println("Error: cannot access file " + inputFile);
				return;
			}
			if(! (ignoreFile.exists() && ignoreFile.canRead())) {
				System.out.println("Error: cannot access file " + ignoreFile);
				return;
			}
			if(!(digit(args[3]) && Integer.parseInt(args[3]) <= 0)) {
				System.out.println("Error: maxWords must be a positive integer");
				return;
			}
			
			
			
			
		}
	}
	
	private static boolean digit(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
