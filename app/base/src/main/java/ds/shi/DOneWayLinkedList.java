package ds.shi;

/**
 * 双端单向链表的实现<br>
 * 双端单向链表多一个指向最后一个节点的引用
 * 
 * 主要操作：<br>
 * 1. 表头插入数据节点 insertFirst<br>
 * 2. 表尾插入数据节点 insertlast<br>
 * 3. 表头删除数据节点，获得数据deleteFirst<br>
 * 
 * <b>表尾无法删除，因为没有前一个节点的应用</b>
 * 
 * 其他操作：<br>
 * 3. 链表是否空 isEmpty
 *
 * 
 * @author shi
 *
 */
public class DOneWayLinkedList {
	private SingleLink first; // ref to first link
	private SingleLink last; // ref to last link
	// -------------------------------------------------------------

	public DOneWayLinkedList() {
		first = null; // no links on list yet
		last = null;
	}

	// -------------------------------------------------------------
	public boolean isEmpty() {
		return first == null;
	}

	// -------------------------------------------------------------
	public void insertFirst(Object dd) {
		SingleLink newLink = new SingleLink(dd); // make new link

		if (isEmpty()) // if empty list,
			last = newLink; // newLink <-- last
		newLink.next = first; // newLink --> old first
		first = newLink; // first --> newLink
	}

	// -------------------------------------------------------------
	public void insertLast(Object dd) {
		SingleLink newLink = new SingleLink(dd); // make new link
		if (isEmpty()) // if empty list,
			first = newLink; // first --> newLink
		else
			last.next = newLink; // old last --> newLink
		last = newLink; // newLink <-- last
	}

	// -------------------------------------------------------------
	public Object deleteFirst() { // (assumes non-empty list)
		Object temp =  first.data;
		if (first.next == null) // if only one item
			last = null; // null <-- last
		first = first.next; // first --> old next
		return temp;
	}

	// -------------------------------------------------------------
	public void displayList() {
		System.out.print("List (first-->last): ");
		SingleLink current = first; // start at beginning
		while (current != null) // until end of list,
		{
			System.out.println(current.toString());// print data
			current = current.next; // move to next link
		}
		System.out.println("");
	}
}