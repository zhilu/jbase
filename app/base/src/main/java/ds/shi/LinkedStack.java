package ds.shi;

public class LinkedStack {
	private OneWayLinkedList theList;

	public LinkedStack() {
		theList = new OneWayLinkedList();
	}

	public void push(long j) {
		theList.insertFirst(j);
	}

	public Object pop() {
		return theList.deleteFirst();
	}

	public boolean isEmpty() {
		return theList.isEmpty();
	}

	public void displayStack() {
		System.out.print("Stack (top-->bottom): ");
		theList.displayList();
	}
}
