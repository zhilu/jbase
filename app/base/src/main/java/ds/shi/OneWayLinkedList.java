package ds.shi;
/**
 * ���������ʵ��<br>
 * 
 * ��Ҫ������<br>
 * 1. ��ͷ�������ݽڵ� insertFirst<br>
 * 2. ��ͷɾ�����ݽڵ㣬�������deleteFirst<br>
 * 
 * ����������<br>
 * 3. �����Ƿ��  isEmpty
 *
 * 
 * @author shi
 *
 */
public class OneWayLinkedList {
	private SingleLink first; // ref to first link on list

	public OneWayLinkedList() {
		first = null; // no links on list yet
	}

	public void insertFirst(Object data) { // make new link
		SingleLink newLink = new SingleLink(data);
		newLink.next = first; // newLink --> old first
		first = newLink; // first --> newLink
	}

	public SingleLink deleteFirst() { // (assumes list not empty)
		SingleLink temp = first; // save reference to link
		first = first.next; // delete it: first-->old next
		return temp; // return deleted link
	}

	public boolean isEmpty() {
		return (first == null);
	}
	
	public void displayList() {
		System.out.print("List (first-->last): ");
		System.out.println("");
		SingleLink current = first; 
		while (current != null) 
		{
			System.out.println(current.toString());
			current = current.next; 
		}
		System.out.println("");
	}

}
