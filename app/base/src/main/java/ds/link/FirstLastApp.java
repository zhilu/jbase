package ds.link;

import ds.shi.DOneWayLinkedList;

// firstLastList.java
// demonstrates list with first and last references
// to run this program: C>java FirstLastApp


public class FirstLastApp
   {
   public static void main(String[] args)
      {                              // make a new list
	   DOneWayLinkedList theList = new DOneWayLinkedList();

      theList.insertFirst(22);       // insert at front
      theList.insertFirst(44);
      theList.insertFirst(66);

      theList.insertLast(11);        // insert at rear
      theList.insertLast(33);
      theList.insertLast(55);

      theList.displayList();         // display the list

      theList.deleteFirst();         // delete first two items
      theList.deleteFirst();

      theList.displayList();         // display again
      }  // end main()
   }  // end class FirstLastApp
////////////////////////////////////////////////////////////////
