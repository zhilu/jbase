package ds.link;

import ds.refactor.SingleLink;
import ds.refactor.SortedLinkList;

// listInsertionSort.java
// demonstrates sorted list used for sorting
// to run this program: C>java ListInsertionSortApp

public class ListInsertionSortApp
   {
   public static void main(String[] args)
      {
      int size = 10;
                                 // create array of links
      SingleLink[] linkArray = new SingleLink[size];

      for(int j=0; j<size; j++)  // fill array with links
         {                            // random number
         int n = (int)(java.lang.Math.random()*99);
         SingleLink newLink = new SingleLink(n);  // make link
         linkArray[j] = newLink;      // put in array
         }
                                 // display array contents
      System.out.print("Unsorted array: ");
      for(int j=0; j<size; j++)
         System.out.print( linkArray[j].data + " " );
      System.out.println("");

                                 // create new list
      SortedLinkList theSortedList = new SortedLinkList(linkArray);

      for(int j=0; j<size; j++)  // links from list to array
         linkArray[j] = theSortedList.remove();

                                 // display array contents
      System.out.print("Sorted Array:   ");
      for(int j=0; j<size; j++)
         System.out.print(linkArray[j].data + " ");
      System.out.println("");
      }  // end main()
   }  // end class ListInsertionSortApp
////////////////////////////////////////////////////////////////
