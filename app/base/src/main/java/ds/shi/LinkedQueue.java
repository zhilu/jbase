package ds.shi;
/**
 * 链表实现的队列 
 * 
 * @author shi
 *
 */
public class LinkedQueue {
	private DOneWayLinkedList theList;

	public LinkedQueue() {
		theList = new DOneWayLinkedList();
	}

	public boolean isEmpty() {
		return theList.isEmpty();
	}

	public void insert(long j) {
		theList.insertLast(j);
	}

	public Object remove() {
		return theList.deleteFirst();
	}

	public void displayQueue() {
		System.out.print("Queue (front-->rear): ");
		theList.displayList();
	}
}
