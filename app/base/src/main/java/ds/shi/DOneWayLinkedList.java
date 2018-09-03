package ds.shi;

/**
 * ˫�˵��������ʵ��<br>
 * ˫�˵��������һ��ָ�����һ���ڵ������
 * 
 * ��Ҫ������<br>
 * 1. ��ͷ�������ݽڵ� insertFirst<br>
 * 2. ��β�������ݽڵ� insertlast<br>
 * 3. ��ͷɾ�����ݽڵ㣬�������deleteFirst<br>
 * 
 * <b>��β�޷�ɾ������Ϊû��ǰһ���ڵ��Ӧ��</b>
 * 
 * ����������<br>
 * 3. �����Ƿ�� isEmpty
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