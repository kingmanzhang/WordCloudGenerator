///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            CS367 Assignment 3
// Files:            ArrayHeap.java, BSTDictionary.java, 
//							BSTDictionaryIterator.java, BSTnode.java, 
//							DictionaryADT.java, DuplicateException.java, KeyWord.java
//							Prioritizable.java, PriorityQueueADT.java, 
//							TestClassWordCloud.java
// Semester:         
//
// Author:           Xingmin Zhang 
// Email:            xzhang66@wisc.edu
// CS Login:         
// Lecturer's Name:  
// Lab Section:      
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   must fully acknowledge and credit those sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but tutors, roommates, relatives, strangers, etc do.
//
// Persons:          None
//
// Online sources:   None
//////////////////////////// 80 columns wide //////////////////////////////////



import java.util.*;
import java.io.*;

public class WordCloudGenerator {
    /**
     * The main method generates a word cloud as described in the program 
     * write-up.  You will need to add to the code given here.
     * 
     * @param args the command-line arguments that determine where input and 
     * output is done:
     * <ul>
     *   <li>args[0] is the name of the input file</li>
     *   <li>args[1] is the name of the output file</li>
     *   <li>args[2] is the name of the file containing the words to ignore 
     *       when generating the word cloud</li>
     *   <li>args[3] is the maximum number of words to include in the word 
     *       cloud</li> 
     * </ul>
     */
    public static void main(String[] args) {
        Scanner in = null;         // for input from text file
        PrintStream out = null;    // for output to html file
        Scanner inIgnore = null;   // for input from ignore file
        DictionaryADT<KeyWord> dictionary = new BSTDictionary<KeyWord>();  

        // Check the command-line arguments and set up the input and output
        
        if(args.length != 4) {
	  			System.out.println("Four arguments required: "
	  					+ "inputFileName outputFileName ignoreFileName maxWords");
	  			return;
	  		} else {
	  			File inputFile = new File(args[0]);
	  			File ignoreFile = new File(args[2]);
	  			if(!inputFile.exists() || !inputFile.canRead()) {
	  				System.out.println("Error: cannot access file " + inputFile);
	  				return;
	  			}
	  			if(!ignoreFile.exists() || !ignoreFile.canRead()) {
	  				System.out.println("Error: cannot access file " + ignoreFile);
	  				return;
	  			}
	  			if(!digit(args[3]) || Integer.parseInt(args[3]) <= 0) {
	  				System.out.println("Error: maxWords must be a positive integer");
	  				return;
	  			}
	  		

        // Create the dictionary of words to ignore
        DictionaryADT<KeyWord> ignoreDictionary = new BSTDictionary<KeyWord>();
        try {
			inIgnore = new Scanner(ignoreFile);
			while (inIgnore.hasNext()) {
            try {
            	KeyWord newKeyWord = new KeyWord(inIgnore.next().toLowerCase());
               ignoreDictionary.insert(newKeyWord);
            } catch (DuplicateException e) {
                // if there is a duplicate, we'll just ignore it
            }
			}
			} catch (FileNotFoundException e1) {
				// Not going to happen because the file is already checked
				e1.printStackTrace();
			}
        
        // Process the input file line by line
        // Note: the code below just prints out the words contained in each
        // line.  You will need to replace that code with code to generate
        // the dictionary of KeyWords.
        DictionaryADT<KeyWord> inDictionary = new BSTDictionary<KeyWord>();
        try {
			in = new Scanner(inputFile);
			while (in.hasNext()) {
            String line = in.nextLine();
            List<String> words = parseLine(line);           
            for (String word : words) {
            	KeyWord newKeyWord = new KeyWord(word);
            	if (ignoreDictionary.lookup(newKeyWord) == null) {
            		
            		try {
							inDictionary.insert(newKeyWord);
						} catch (DuplicateException e) {
							inDictionary.lookup(newKeyWord).increment();
						}
            	}
            }      
			}

			} catch (FileNotFoundException e) {
				// Not going to happen
				e.printStackTrace();
			}

        //print the BST of keywords from the input file
        System.out.println("# keys: " + inDictionary.size());
        System.out.printf("avg path length: %.1f\n", 
        inDictionary.totalPathLength()/(float)inDictionary.size());
        System.out.println("linear avg path: " + 
        (inDictionary.size() + 1) / 2.0);
        
        //create a priority queue and put all keyword of input file to the 
        //queue
        ArrayHeap<KeyWord> newHeap = new ArrayHeap<>();
        Iterator<KeyWord> itr = inDictionary.iterator();
        while(itr.hasNext()) {
      	  newHeap.insert(itr.next());
        }
        
        //Use the priority queue to create a list of KeyWords of 
        //the appropriate length
        for(int i = 0; i < Integer.parseInt(args[3]); i++) {
      	  try {
      		  if(!newHeap.isEmpty()){
      			  dictionary.insert(newHeap.removeMax());
      		  }
				} catch (DuplicateException e) {
					e.printStackTrace();
				}
        }
        
        //Generate the html output file
        try {
			out = new PrintStream(args[1]);
			generateHtml(dictionary, out);
		  } catch (FileNotFoundException e) {
				e.printStackTrace();
		  }
    
	  	  }

        // Close everything
        if (in != null) 
            in.close();
        if (inIgnore != null) 
            inIgnore.close();
        if (out != null) 
            out.close();
    }
    
    /**
     * Parses the given line into an array of words.
     * 
     * @param line a line of input to parse
     * @return a list of words extracted from the line of input in the order
     *         they appear in the line
     *         
     * DO NOT CHANGE THIS METHOD.
     */
    private static List<String> parseLine(String line) {
        String[] tokens = line.split("[ ]+");
        ArrayList<String> words = new ArrayList<String>();
        for (int i = 0; i < tokens.length; i++) {  // for each word
            
            // find index of first digit/letter
              boolean done = false; 
              int first = 0;
            String word = tokens[i];
            while (first < word.length() && !done) {
                if (Character.isDigit(word.charAt(first)) ||
                    Character.isLetter(word.charAt(first)))
                    done = true;
                else first++;
            }
            
            // find index of last digit/letter
            int last = word.length()-1;
            done = false;
            while (last > first && !done) {
                if (Character.isDigit(word.charAt(last)) ||
                        Character.isLetter(word.charAt(last)))
                        done = true;
                    else last--;
            }
            
            // trim from beginning and end of string so that is starts and
            // ends with a letter or digit
            word = word.substring(first, last+1);
  
            // make sure there is at least one letter in the word
            done = false;
            first = 0;
            while (first < word.length() && !done)
                if (Character.isLetter(word.charAt(first)))
                    done = true;
                else first++;           
            if (done)
                words.add(word);
        }
        
        return words;
    }
    
    /**
     * Generates the html file using the given list of words.  The html file
     * is printed to the provided PrintStream.
     * 
     * @param words a list of KeyWords
     * @param out the PrintStream to print the html file to
     * 
     * DO NOT CHANGE THIS METHOD
     */
    private static void generateHtml(DictionaryADT<KeyWord> words, 
                                     PrintStream out) {
           String[] colors = { 
         	"#CD5C5C",      //INDIANRED
		"#5F9EA0",      //CADETBLUE
	 	"#FA8072",      //SALMON
	 	"#E9967A",      //DARKSALMON
	 	"#FF69B4",      //HOTPINK
	 	"#FFA500",      //ORANGE
		"#B22222",	// FIREBRICK
	 	"#E6E6FA",      //LAVENDER
	 	"#8A2BE2",      //BLUEVIOLET
	 	"#6A5ACD",      //SLATEBLUE
	 	"#7FFF00",      //CHARTREUSE
	 	"#32CD32",      //LIMEGREEN
	 	"#228B22",      //FORESTGREEN
	 	"#66CDAA",      //MEDIUMAQUAMARINE
	 	"#00FFFF",      //CYAN
	 	"#1E90FF",      //DODGERBLUE
	 	"#FFE4C4",      //BISQUE
	 	"#8B4513",      //SADDLEBROWN
	 	"#F5F5DC",      //BEIGE
	 	"#C0C0C0"       //Silver       
	     };
           int initFontSize = 100;
   	   String fontFamily = "Cursive";
           
        // Print the header information including the styles
        out.println("<head>\n<title>Word Cloud</title>");
        out.println("<style type=\"text/css\">");
        out.println("body { font-family: "+fontFamily+" }");
        
        // Each style is of the form:
        // .styleN {
        //      font-size: X%;
        //      color: #YYYYYY;

        // }
        //  where N and X are integers and Y is a hexadecimal digit
        for (int i = 0; i < colors.length; i++)
            out.println(".style" + i + 
                    " {\n    font-size: " + (initFontSize + i*20)
                    + "%;\n    color: " + colors[i] + ";\n}");

        
        out.println("</style>\n</head>\n<body><p>");        
        
        // Find the minimum and maximum values in the collection of words
        int min = Integer.MAX_VALUE, max = 0;
        for (KeyWord word : words) {
            int occur = word.getOccurrences();
            if (occur > max)
                max = occur;
            if (occur < min)
                min = occur;
        }

        double slope = (colors.length - 1.0)/(max - min);
        
        for (KeyWord word : words) {
            out.print("<span class=\"style");
            
            // Determine the appropriate style for this value using
            // linear interpolation
            // y = slope *(x - min) (rounded to nearest integer)
            // where y = the style number
            // and x = number of occurrences
            int index = (int)Math.round(slope*(word.getOccurrences() - min));
            
            out.println(index + "\">" + word.getWord() + "</span>&nbsp;");
        }
        
        // Print the closing tags
        out.println("</p></body>\n</html>");
    }
    
    /**
     * A helper method to determine whether a string contains only digits.
     * @param str: a string to check
     * @return true if all characters of the string are digits
     */
    private static boolean digit(String str) {
 		for (int i = 0; i < str.length(); i++) {
 			if (!Character.isDigit(str.charAt(i))) {
 				return false;
 			}
 		}
 		return true;
 	}
 }
