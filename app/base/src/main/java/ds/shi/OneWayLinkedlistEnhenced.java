package ds.shi;

/**
 * 单向链表的加强版实现<br>
 * 
 * 主要操作：<br>
 * 1. 表头插入数据节点 insertFirst<br>
 * 2. 按key删除节点  delete<br>
 * 3. 按key索引节点 find <br>
 * 
 * 其他操作：<br>
 * 4. 链表是否空 isEmpty
 *
 * 
 * @author shi
 *
 */
public class OneWayLinkedlistEnhenced {
	private SingleLink first; // ref to first link on list

	public OneWayLinkedlistEnhenced() {
		first = null; // no links on list yet
	}

	public void insertFirst(Object data) { // make new link
		SingleLink newLink = new SingleLink(data);
		newLink.next = first; // newLink --> old first
		first = newLink; // first --> newLink
	}

	public SingleLink delete(Object key) // delete link with given key
	{ // (assumes non-empty list)
		SingleLink current = first; // search for link
		SingleLink previous = first;
		while (!current.data.equals(key)) {
			if (current.next == null)
				return null; // didn't find it
			else {
				previous = current; // go to next link
				current = current.next;
			}
		} // found it
		if (current == first) // if first link,
			first = first.next; // change first
		else // otherwise,
			previous.next = current.next; // bypass it
		return current;
	}

	public SingleLink find(Object key) { // (assumes non-empty list)
		SingleLink current = first; // start at 'first'
		while (!current.data.equals(key)) // while no match,
		{
			if (current.next == null) // if end of list,
				return null; // didn't find it
			else // not end of list,
				current = current.next; // go to next link
		}
		return current; // found it
	}

	public boolean isEmpty() {
		return (first == null);
	}

	public void displayList() {
		System.out.print("List (first-->last): ");
		System.out.println("");
		SingleLink current = first;
		while (current != null) {
			System.out.println(current.toString());
			current = current.next;
		}
		System.out.println("");
	}

}
