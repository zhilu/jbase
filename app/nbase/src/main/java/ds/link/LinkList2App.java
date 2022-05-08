package ds.link;

import ds.refactor.OneWayLinkedlistEnhenced;
import ds.refactor.SingleLink;

// linkList2.java
// demonstrates linked list
// to run this program: C>java LinkList2App
////////////////////////////////////////////////////////////////
public class LinkList2App {
	public static void main(String[] args) {
		OneWayLinkedlistEnhenced theList = new OneWayLinkedlistEnhenced(); // make
																			// list

		theList.insertFirst(22); // insert 4 items
		theList.insertFirst(44);
		theList.insertFirst(66);
		theList.insertFirst(88);

		theList.displayList(); // display list

		SingleLink f = theList.find(44); // find item
		if (f != null)
			System.out.println("Found link with key " + f.data);
		else
			System.out.println("Can't find link");

		SingleLink d = theList.delete(66); // delete item
		if (d != null)
			System.out.println("Deleted link with key " + d.data);
		else
			System.out.println("Can't delete link");

		theList.displayList(); // display list
	} // end main()
} 