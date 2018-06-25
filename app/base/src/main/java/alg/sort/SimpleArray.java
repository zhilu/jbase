package alg.sort;

public class SimpleArray {
	private long[] theArray;
	private int nElems;

	public long[] getTheArray() {
		return theArray;
	}

	public void setTheArray(long[] theArray) {
		this.theArray = theArray;
	}

	public int getnElems() {
		return nElems;
	}

	public void setnElems(int nElems) {
		this.nElems = nElems;
	}

	public SimpleArray(int max) {
		theArray = new long[max];
		nElems = 0;
	}

	public void insert(long value) 
	{
		theArray[nElems] = value; 
		nElems++;
	}

	public void display() 
	{
		System.out.print("A=");
		for (int j = 0; j < nElems; j++) 
			System.out.print(theArray[j] + " "); 
		System.out.println("");
	}

	public int size() {
		return nElems ;
	}

	
		// --------------------------------------------------------------
} // end class ArraySh
