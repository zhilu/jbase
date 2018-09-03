package ds.stackqueue;
// priorityQ.java

import ds.refactor.ArrayPriorityQueue;

public class PriorityQApp {
	public static void main(String[] args) {
		ArrayPriorityQueue thePQ = new ArrayPriorityQueue(5);
		thePQ.insert(30);
		thePQ.insert(50);
		thePQ.insert(10);
		thePQ.insert(40);
		thePQ.insert(20);

		while (!thePQ.isEmpty()) {
			long item = thePQ.remove();
			System.out.print(item + " "); // 10, 20, 30, 40, 50
		} // end while
		System.out.println("");
	} // end main()
} // end class PriorityQApp
