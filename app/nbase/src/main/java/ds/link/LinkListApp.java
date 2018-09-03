package ds.link;

import ds.refactor.OneWayLinkedList;
import ds.refactor.SingleLink;

// linkList.java
// demonstrates linked list
// to run this program: C>java LinkListApp

public class LinkListApp {
	public static void main(String[] args) {
		OneWayLinkedList theList = new OneWayLinkedList(); // make new list

		theList.insertFirst(22); // insert four items
		theList.insertFirst(44);
		theList.insertFirst(66);
		theList.insertFirst(88);

		theList.displayList(); // display list

		while (!theList.isEmpty()) // until it's empty,
		{
			SingleLink aLink = theList.deleteFirst(); // delete link
			System.out.print("Deleted "); // display it
			System.out.println(aLink.toString());
			System.out.println("");
		}
		theList.displayList(); // display list
	} 
} 
