import java.util.Iterator;

public class TestClassWordCloud {
	public static void main(String[] args) {
		
		System.out.println("Start testing KeyWord class");
		KeyWord first = new KeyWord("5");
		System.out.println(first);
		first.increment();
		System.out.println(first);
		
		KeyWord second = new KeyWord("3");
		System.out.println("Is first and second keyword the same? " 
		+ first.equals(second));
		System.out.println("Is first ahead of second keyword? " + 
		first.compareTo(second));
		KeyWord third = new KeyWord("7");
		KeyWord fourth = new KeyWord("2");
		KeyWord fifth = new KeyWord("4");
		KeyWord sixth = new KeyWord("6");
		KeyWord seventh = new KeyWord("8");
		
		BSTDictionary<KeyWord> aBST = new BSTDictionary<>();
		System.out.println("Is tree empty? " + aBST.isEmpty());
		
		try {
			aBST.insert(first);
			System.out.println("Is tree empty? " + aBST.isEmpty());
			aBST.insert(second);
			
			aBST.insert(third);
			aBST.insert(fourth);
			aBST.insert(fifth);
			aBST.insert(sixth);
			aBST.insert(seventh);
			System.out.println("Tree size " + aBST.size());
			System.out.println("Total path " + aBST.totalPathLength());
			
			Iterator<KeyWord> itr = aBST.iterator();
			while(itr.hasNext()) {
				System.out.println("The next " + itr.next());
			}
			
			
		} catch (DuplicateException e) {
			
			e.printStackTrace();
		}
		
		//test PriorityQueue
		ArrayHeap<KeyWord> newHeap = new ArrayHeap<>();
		first.increment();
		first.increment();
		first.increment();
		
		second.increment();
		second.increment();
		second.increment();
		second.increment();
		second.increment();
		second.increment();
		second.increment();
		second.increment();
		
		third.increment();
		
		System.out.println("heap is empty?" + newHeap.isEmpty());
		System.out.println("heap size " + newHeap.size());
		newHeap.insert(first);
		newHeap.insert(second);
		newHeap.insert(third);
		newHeap.insert(fourth);
		System.out.println("heap is empty?" + newHeap.isEmpty());
		System.out.println("heap size " + newHeap.size());
		System.out.println("get max " + newHeap.getMax());
		System.out.println("heap size " + newHeap.size());
		System.out.println("remove max " + newHeap.removeMax());
		System.out.println("heap size " + newHeap.size());
		System.out.println("remove max " + newHeap.removeMax());
		System.out.println("heap size " + newHeap.size());
		System.out.println("remove max " + newHeap.removeMax());
		System.out.println("heap size " + newHeap.size());
		
	}

}
