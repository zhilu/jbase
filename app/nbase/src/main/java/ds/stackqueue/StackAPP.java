package ds.stackqueue;

import ds.refactor.ArrayStack;

public class StackAPP {
	public static void main(String[] args) {
		ArrayStack theStack = new ArrayStack(10); // make new stack
		theStack.push(20); // push items onto stack
		theStack.push(40);
		theStack.push(60);
		theStack.push(80);

		while (!theStack.isEmpty()) // until it's empty,
		{ // delete item from stack
			int value =  (int) theStack.pop();
			System.out.print(value); // display it
			System.out.print(" ");
		} // end while
		System.out.println("");
	} 
} 
