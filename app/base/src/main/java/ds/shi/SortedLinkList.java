package ds.shi;


public class SortedLinkList {

	private SingleLink first; // ref to first item
	// -------------------------------------------------------------

	public SortedLinkList() // constructor
	{
		first = null;
	}

	public SortedLinkList(SingleLink[] linkArr)  // constructor (array
	      {                               // as argument)
	      first = null;                        // initialize list
	      for(int j=0; j<linkArr.length; j++)  // copy array
	         insert( linkArr[j] );             // to list
	      }

	// -------------------------------------------------------------
	public boolean isEmpty() // true if no links
	{
		return (first == null);
	}

	// -------------------------------------------------------------
	public void insert(Object key) // insert, in order
	{
		SingleLink newLink = new SingleLink(key); // make new link
		SingleLink previous = null; // start at first
		SingleLink current = first;
		// until end of list,
		// 此处强制转换，可见存储数据应该实现comparable接口，因此不应该直接用Object对象存储数据
		while (current != null && (int) key > (int) current.data) { // or key >
																	// current,
			previous = current;
			current = current.next; // go to next item
		}
		if (previous == null) // at beginning of list
			first = newLink; // first --> newLink
		else // not at beginning
			previous.next = newLink; // old prev --> newLink
		newLink.next = current; // newLink --> old currnt
	} // end insert()
		// -------------------------------------------------------------

	public SingleLink remove() // return & delete first link
	{ // (assumes non-empty list)
		SingleLink temp = first; // save first
		first = first.next; // delete first
		return temp; // return value
	}

	// -------------------------------------------------------------
	public void displayList() {
		System.out.print("List (first-->last): ");
		SingleLink current = first; // start at beginning of list
		while (current != null) // until end of list,
		{
			System.out.println(current.toString()); // print data
			current = current.next; // move to next link
		}
		System.out.println("");
	}
} // end class SortedList
	////////////////////////////////////////////////////////////////