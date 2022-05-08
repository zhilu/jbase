package ds.link;
// linkStack.java
// demonstrates a stack implemented as a list
// to run this program: C>java LinkStackApp

import ds.shi.LinkedStack;

public class LinkStackApp
   {
   public static void main(String[] args)
      {
      LinkedStack theStack = new LinkedStack(); // make stack

      theStack.push(20);                    // push items
      theStack.push(40);

      theStack.displayStack();              // display stack

      theStack.push(60);                    // push items
      theStack.push(80);

      theStack.displayStack();              // display stack

      theStack.pop();                       // pop items
      theStack.pop();

      theStack.displayStack();              // display stack
      }  // end main()
   }  // end class LinkStackApp
////////////////////////////////////////////////////////////////
