
import java.util.NoSuchElementException;

public class ArrayHeap<E extends Prioritizable> implements PriorityQueueADT<E> {

    // default number of items the heap can hold before expanding
    private static final int INIT_SIZE = 100;
    private E[] newArray;
    private int numItems;

    // TO DO:
    //
    // Add a no-argument constructor that constructs a heap whose underlying
    // array has enough space to store INIT_SIZE items before needing to 
    // expand.
    //
    // Add a 1-argument constructor that takes an integer parameter and 
    // constructs a heap whose underlying array has enough space to store the 
    // number of items given in the parameter before needing to expand.  If
    // the parameter value is less 0, an IllegalArgumentException is thrown.
    //
    // Add your code to implement the PriorityQueue ADT operations using a
    // heap whose underlying data structure is an array.
    
    public ArrayHeap() {
   	 this.newArray = (E[])(new Prioritizable[INIT_SIZE]);
   	 numItems = 0;
    }
    
    public ArrayHeap(int arraySize) {
   	 if (arraySize < 0) {
   		 throw new IllegalArgumentException();
   	 }
   	 this.newArray = (E[])(new Prioritizable[arraySize]);
   	 numItems = 0;
    }


    public boolean isEmpty() {
        return numItems == 0;  
    }

    public void insert(E item) {
   	 numItems++;
   	 newArray[numItems] = item;
   	 int k = numItems;
   	 while(k > 1) {
   		 if (item.getPriority() > newArray[k / 2].getPriority()) {
   			newArray[k] = newArray[k/2];
   			newArray[k/2] = item;
   		 }
   		 k = k / 2;
   	 }
    }

    public E removeMax() {
   	 if(numItems == 0) {
   		 throw new NoSuchElementException();
   	 }
   	 E max = newArray[1];
   	 newArray[1] = newArray[numItems];
   	 int k = 1;
   	 while(2 * k < numItems) {
   		 if(newArray[k].getPriority() < newArray[2*k].getPriority()
   				 || newArray[k].getPriority() < newArray[2*k+1].getPriority()) {
   			 E largerChild;
   			 if(newArray[2*k].getPriority() < newArray[2*k + 1].getPriority()) {
   				 largerChild = newArray[2*k + 1];
   				 newArray[2*k + 1] = newArray[k];
   				 newArray[k] = largerChild;
   			 } else {
   				 largerChild = newArray[2*k];
   				 newArray[2*k] = newArray[k];
   				 newArray[k] = largerChild;
   			 }
   		 }
   		 k = k * 2;
   	 }
   	 numItems--;
       return max;
    }

    public E getMax() {
   	 if(numItems == 0) {
   		 throw new NoSuchElementException();
   	 }
        return newArray[1]; 
    }

    public int size() {
        return numItems; 
    }
}
