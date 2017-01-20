///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  WordCloudGenerator.java
// File:             BSTDictionaryIterator.java
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

import java.util.*;

/**
 * BSTDictionaryIterator implements an iterator for a binary search tree (BST)
 * implementation of a Dictionary.  The iterator iterates over the tree in 
 * order of the key values (from smallest to largest).
 */
public class BSTDictionaryIterator<K> implements Iterator<K> {
	private BSTnode<K> root; //for root node of a BST
	private Stack<BSTnode<K>> newStack = null; //for the constructor of the class
	
	/**
	 * Constructor of the iterator class. It pushes the leftmost nodes to a 
	 * stack. 
	 * @param n: root node of a BST. 
	 */
	public BSTDictionaryIterator(BSTnode<K> n) {
		this.root = n;
		this.newStack = new Stack<>();
		while(root.getLeft() != null) {
			newStack.push(root);
			root = root.getLeft();
		}
		newStack.push(root);	
	}

	/**
	 * A method to determine whether there is a remaining K object. 
	 * @return true is there is at least one more object. 
	 */
    public boolean hasNext() {
   	 
        return !newStack.isEmpty();  
    }

    /**
     * A method to return the next node. 
     * @return the next K object. 
     */
    public K next() {
   	 BSTnode<K> topNode = newStack.peek(); //peek the node to pop out
   	 BSTnode<K> toPop = null; //for node to pop out
   	 //if the node to pop out has a right child, push the right child and
   	 //its left children to the stack. 
   	 if(topNode.getRight() != null) {
   		 topNode = topNode.getRight();
   		 toPop = newStack.pop();
   		 while(topNode.getLeft() != null) {
   			 newStack.push(topNode);
   			 topNode = topNode.getLeft();
   		 }
   		 newStack.push(topNode);
   		 return toPop.getKey();
   	 } else {
   		 //otherwise, return the top node of the stack
   		 return newStack.pop().getKey(); 
   	 }
         
    }

    public void remove() {
        // DO NOT CHANGE: you do not need to implement this method
        throw new UnsupportedOperationException();
    }    
}
