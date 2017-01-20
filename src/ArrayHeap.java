///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  WordCloudGenerator.java
// File:             ArrayHeap.java
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

import java.util.NoSuchElementException;

/**
 * ArrayHeap is an implementation of priority queue class.
 * @author Aaron
 *
 * @param <E>
 */
public class ArrayHeap<E extends Prioritizable> implements PriorityQueueADT<E> {

    // default number of items the heap can hold before expanding
    private static final int INIT_SIZE = 100;
    private E[] itemsArray;
    private int numItems;


    /**
     * No argument constructor creates an array of INIT_SIZE
     */
    public ArrayHeap() {
   	 this.itemsArray = (E[])(new Prioritizable[INIT_SIZE]);
   	 numItems = 0;
    }
    
    /**
     * The constructor creates an array of E with specified size
     * @param arraySize: size of array during class instantiation
     */
    public ArrayHeap(int arraySize) {
   	 if (arraySize < 0) {
   		 throw new IllegalArgumentException();
   	 }
   	 this.itemsArray = (E[])(new Prioritizable[arraySize]);
   	 numItems = 0;
    }

	/**
	 * A method to determine whether the heap is empty
	 */
    public boolean isEmpty() {
        return numItems == 0;  
    }

    /**
     * A method to insert an item to the heap.
     * @param: a new item to insert
     */
    public void insert(E item) {
   	 
   	 numItems++;
   	 //if array if full, double the size of the array
   	 if(numItems == itemsArray.length) {
   		 itemsArray = expand(itemsArray);
   	 }
   	 //insert the new item as a leaf and then swap the item to the proper 
   	 //place
   	 itemsArray[numItems] = item;
   	 int k = numItems;
   	 while(k > 1) {
   		 if (item.getPriority() > itemsArray[k / 2].getPriority()) {
   			itemsArray[k] = itemsArray[k/2];
   			itemsArray[k/2] = item;
   		 }
   		 k = k / 2;
   	 }
    }
    
    /**
     * A helper method to expand the array
     * @param oriArray: original array
     * @return the expanded array
     */
    private E[] expand(E[] oriArray) {
   	 
   	 int largerSize = 2 * oriArray.length; //new array size
   	 itemsArray = (E[])(new Prioritizable[largerSize]); //new array
   	 for (int i = 1; i < oriArray.length; i++) { //copy items to new array
   		 itemsArray[i] = oriArray[i];
   	 }
   	 return itemsArray;
   	 
    }

    /**
     * A method to remove the max item from the queue
     * @return the max item
     */
    public E removeMax() {
   	 
   	 if(numItems == 0) {
   		 throw new NoSuchElementException();
   	 }
   	 
   	 E max = itemsArray[1]; //root (first item of array) is the max
   	//move the last item to the first place, then move it to the right place
   	 itemsArray[1] = itemsArray[numItems]; 
   	 int k = 1;
   	 //compare the first item with its children, swap with the larger child
   	 //if it is smaller than either of its child; continue until it is bigger
   	 //than both of its children
   	 while(2 * k < numItems) {
   		 if(itemsArray[k].getPriority() < itemsArray[2*k].getPriority()
   				 || itemsArray[k].getPriority() < itemsArray[2*k+1].getPriority()) {
   			 E largerChild;
   			 if(itemsArray[2*k].getPriority() < itemsArray[2*k + 1].getPriority()) {
   				 largerChild = itemsArray[2*k + 1];
   				 itemsArray[2*k + 1] = itemsArray[k];
   				 itemsArray[k] = largerChild;
   				 k = 2 * k + 1;
   			 } else {
   				 largerChild = itemsArray[2*k];
   				 itemsArray[2*k] = itemsArray[k];
   				 itemsArray[k] = largerChild;
   				 k = 2 * k;
   			 }
   		 } else {
   			 break;
   		 }
   		 
   	 }
   	 numItems--;
       return max;
    }

    /**
     * A method to return but not remove the max item. 
     * @return the max item.
     */
    public E getMax() {
   	 if(numItems == 0) {
   		 throw new NoSuchElementException();
   	 }
        return itemsArray[1]; 
    }

    /**
     * A method to return the size of the heap. 
     * @return the size of the heap. 
     */
    public int size() {
        return numItems; 
    }
}
