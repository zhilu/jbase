package ds.shi;
/**
 * 双向链表的实现<br>
 * 
 * 主要操作<br>
 * 1. 表头插入节点  insertFirst<br>
 * 2. 表尾插入节点  insertLast<br>
 * 3. 表头删除节点  deleteFirst<br>
 * 4. 表尾删除节点  deleteLast<br>
 * 5. 在某节点后插入数据 insertAfter<br>
 * 6. 删除某个节点 deleteKey<br>
 * 
 * 其他操作
 * 7. 链表是否为空  isEmpty<br>
 * 8. 从前向后输出  displayForward
 * 9. 从后向前输出  displayBackward
 * 
 * @author shi
 *
 */
public class TwoWayLinkedList {
	private DoubleLink first; // ref to first item
	private DoubleLink last; // ref to last item
	// -------------------------------------------------------------

	public TwoWayLinkedList() // constructor
	{
		first = null; // no items on list yet
		last = null;
	}



	public void insertFirst(long dd) 
	{
		DoubleLink newLink = new DoubleLink(dd); // make new link

		if (isEmpty()) // if empty list,
			last = newLink; // newLink <-- last
		else
			first.previous = newLink; // newLink <-- old first
		newLink.next = first; // newLink --> old first
		first = newLink; // first --> newLink
	}

	// -------------------------------------------------------------
	public void insertLast(long dd) // insert at end of list
	{
		DoubleLink newLink = new DoubleLink(dd); // make new link
		if (isEmpty()) // if empty list,
			first = newLink; // first --> newLink
		else {
			last.next = newLink; // old last --> newLink
			newLink.previous = last; // old last <-- newLink
		}
		last = newLink; // newLink <-- last
	}

	// -------------------------------------------------------------
	public DoubleLink deleteFirst() // delete first link
	{ // (assumes non-empty list)
		DoubleLink temp = first;
		if (first.next == null) // if only one item
			last = null; // null <-- last
		else
			first.next.previous = null; // null <-- old next
		first = first.next; // first --> old next
		return temp;
	}

	// -------------------------------------------------------------
	public DoubleLink deleteLast() // delete last link
	{ // (assumes non-empty list)
		DoubleLink temp = last;
		if (first.next == null) // if only one item
			first = null; // first --> null
		else
			last.previous.next = null; // old previous --> null
		last = last.previous; // old previous <-- last
		return temp;
	}

	// -------------------------------------------------------------
	// insert dd just after key
	public boolean insertAfter(Object key, Object dd) { // (assumes non-empty list)
		DoubleLink current = first; // start at beginning
		while (!current.data.equals(key)) // until match is found,
		{
			current = current.next; // move to next link
			if (current == null)
				return false; // didn't find it
		}
		DoubleLink newLink = new DoubleLink(dd); // make new link

		if (current == last) // if last link,
		{
			newLink.next = null; // newLink --> null
			last = newLink; // newLink <-- last
		} else // not last link,
		{
			newLink.next = current.next; // newLink --> old next
											// newLink <-- old next
			current.next.previous = newLink;
		}
		newLink.previous = current; // old current <-- newLink
		current.next = newLink; // old current --> newLink
		return true; // found it, did insertion
	}

	// -------------------------------------------------------------
	public DoubleLink deleteKey(Object key) // delete item w/ given key
	{ // (assumes non-empty list)
		DoubleLink current = first; // start at beginning
		while (!current.data.equals(key)) // until match is found,
		{
			current = current.next; // move to next link
			if (current == null)
				return null; // didn't find it
		}
		if (current == first) // found it; first item?
			first = current.next; // first --> old next
		else // not first
				// old previous --> old next
			current.previous.next = current.next;

		if (current == last) // last item?
			last = current.previous; // old previous <-- last
		else // not last
				// old previous <-- old next
			current.next.previous = current.previous;
		return current; // return value
	}

	public boolean isEmpty() // true if no links
	{
		return first == null;
	}
	// -------------------------------------------------------------
	public void displayForward() {
		System.out.print("List (first-->last): ");
		DoubleLink current = first; // start at beginning
		System.out.println("");
		while (current != null) // until end of list,
		{
			System.out.println(current.toString()); // display data
			current = current.next; // move to next link
		}
		System.out.println("");
		
	}

	// -------------------------------------------------------------
	public void displayBackward() {
		System.out.print("List (last-->first): ");
		DoubleLink current = last; // start at end
		System.out.println("");
		while (current != null) // until start of list,
		{
			System.out.println(current.toString()); // display data
			current = current.previous; // move to previous link
		}
		System.out.println("");
	}
	// -------------------------------------------------------------
}
