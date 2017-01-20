///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  WordCloudGenerator.java
// File:             BSTDictionary.java
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


import java.util.Iterator;

public class BSTDictionary<K extends Comparable<K>> implements DictionaryADT<K> {
    private BSTnode<K> root;  // the root node
    private int numItems;     // the number of items in the dictionary
    

    
    public BSTDictionary() {
   	 this.root = null;
   	 this.numItems = 0;
   	 
    }
    
    /**
     * A method to insert an object to the BST dictionary. 
     * @param: object to insert
     */
    public void insert(K key) throws DuplicateException {
        root = insert(root, key);
        numItems++;
    }
    
    /**
     * A helper method to insert a node that has an K object
     * @param n: node of a BST
     * @param key: object to insert
     * @return the root node after insertion
     * @throws DuplicateException
     */
    private BSTnode<K> insert(BSTnode<K> n, K key) throws DuplicateException {
   	 if (n == null) {
   		 return new BSTnode<K>(key);
   	 }
   	 
   	 if (n.getKey().equals(key)) {
   		 throw new DuplicateException();
   	 }
		 if (key.compareTo(n.getKey()) < 0) {
			 n.setLeft(insert(n.getLeft(), key));
			 return n;
		 }
		 else {
   		 n.setRight(insert(n.getRight(), key));
   		 return n;
   	 }
    }

    /**
     * A method to delete an K object key. If key is found, the object is 
     * deleted and return true; otherwise, return false.
     */
    public boolean delete(K key) {

       if(lookup(key) == null) {
      	 return false;
       } else {
      	 root = delete(root, key); 
      	 numItems--;
      	 return true;
       }
    }
    
    /**
     * A helper method to delete an K object key. 
     * @param n: root node of a BST
     * @param key: object to delete
     * @return the new tree after deletion.
     */
    private BSTnode<K> delete(BSTnode<K> n, K key) {
   	 if (n == null) {
   		 return null;
   	 }
   	 
   	 if (key.equals(n.getKey())) {
   		 if(n.getLeft() == null && n.getRight() == null) {
   			 return null;
   		 }
   		 if(n.getLeft() == null) {
   			 return n.getRight();
   		 }
   		 if(n.getRight() == null) {
   			 return n.getLeft();
   		 }
   		 
   		 K smallVal = smallest(n.getRight());
   		 n.setKey(smallVal);
   		 n.setRight(delete(n.getRight(), smallVal));
   		 return n;
   		 
   	 } else if (key.compareTo(n.getKey()) < 0) {
   		 n.setLeft(delete(n.getLeft(), key));
   		 return n;
   	 } else {
   		 n.setRight(delete(n.getRight(), key));
   		 return n;
   	 }
   	 
    }
    
    /**
     * A helper method to return the smallest node of a BST.
     * @param n: root node of a BST.
     * @return the smallest node of the BST. 
     */
    private K smallest(BSTnode<K> n) {
   	 if(n.getLeft() == null) {
   		 return n.getKey();
   	 } else {
   		 return smallest(n.getLeft());
   	 }
   	 
    }

    /**
     * A method to look up K object key. 
     * @return key if it is found; otherwise return null. 
     */
    public K lookup(K key) {
   	
        return lookup(root, key);  
    }
    
    /**
     * A helper method to look up an K object key. 
     * @param n: root node of a BST.
     * @param key: an object to look up
     * @return key if it is found; otherwise return null. 
     */
    private K lookup(BSTnode<K> n, K key) {
   	 
   	 if (n == null) {
   		 return null;
   	 }
   	 
   	 if (n.getKey().equals(key)) {
   		 return n.getKey();
   	 }
   	 
   	 if (key.compareTo(n.getKey()) < 0) {
   		 return lookup(n.getLeft(), key);
   	 }
   	 
   	 else {
   		 return lookup(n.getRight(), key);
   	 }
 
    }

    /**
     * A method to determine whether the BST is empty. 
     * @return: true if the BST is empty.
     */
    public boolean isEmpty() {
        return numItems == 0;  
    }

    /**
     * A method to return the size of a BST.
     * @return the size of a BST. 
     */
    public int size() {
        return numItems;  
    }
    
    /**
     * A method to return the total path length of all nodes. 
     * @return the total path length of all nodes. 
     */
    public int totalPathLength() {
   	 
        return totalPathLength(root, 1);  
    }
    
    /**
     * A helper method to return the total path of a BST tree. 
     * @param n: root node of a BST
     * @param depth: depth of root node n. 
     * @return the total path of a BST with a root node of n. 
     */
    private int totalPathLength(BSTnode<K> n, int depth) {
   	 if (n == null) {
   		 return 0;
   	 }
   	 if (n.getLeft() == null && n.getRight() == null) {
   		 return depth;
   	 } else {
   		 return depth + totalPathLength(n.getLeft(), depth + 1) + totalPathLength(n.getRight(), depth + 1);
   	 }
    }
    
    /**
     * A method to return an iterator of the BST. 
     * @return an iterator of the BST
     */
    public Iterator<K> iterator() {
   	 return new BSTDictionaryIterator<K>(root);
    }
}
