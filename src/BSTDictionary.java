import java.util.Iterator;

public class BSTDictionary<K extends Comparable<K>> implements DictionaryADT<K> {
    private BSTnode<K> root;  // the root node
    private int numItems;     // the number of items in the dictionary

    
    public BSTDictionary() {
   	 this.root = null;
   	 this.numItems = 0;
    }
    
    public void insert(K key) throws DuplicateException {
        root = insert(root, key);
        numItems++;
    }
    
    private BSTnode<K> insert(BSTnode<K> n, K key) throws DuplicateException {
   	 if (n == null) {
   		 return new BSTnode<K>(key, null, null);
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

    public boolean delete(K key) {

       root = delete(root, key); 
       
       if(lookup(key) == null) {
      	 return false;
       } else {
      	 return true;
       }
    }
    
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
    
    private K smallest(BSTnode<K> n) {
   	 if(n.getLeft() == null) {
   		 return n.getKey();
   	 } else {
   		 return smallest(n.getLeft());
   	 }
   	 
    }

    public K lookup(K key) {
   	
        return lookup(root, key);  
    }
    
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

    public boolean isEmpty() {
        return numItems == 0;  
    }

    public int size() {
        return numItems;  
    }
    
    public int totalPathLength() {
   	 Iterator<K> itr = this.iterator();
   	 int total = 0;
   	 while(itr.hasNext()) {
   		 total += totalPathLength(new BSTnode<K>(itr.next()));
   	 }
        return total;  
    }
    
    private int totalPathLength(BSTnode<K> n) {
   	 int depth = 0;
   	 if (n == null) {
   		 return 0;
   	 }
   	 if (n.getLeft() == null && n.getRight() == null) {
   		 return depth;
   	 } else {
   		 depth++;
   		 return depth + totalPathLength(n.getLeft()) + totalPathLength(n.getRight());
   	 }
    }
    
    public Iterator<K> iterator() {
   	 return new BSTDictionaryIterator<K>(root);
    }
}
