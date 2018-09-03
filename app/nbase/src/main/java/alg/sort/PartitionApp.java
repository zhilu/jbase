package alg.sort;

//演示将一个数组分为两部分
//快排序的主要实现步骤
public class PartitionApp {

	public static int partitionIt(SimpleArray arr, int left, int right, long pivot) {
		long[] theArray = arr.getTheArray();
		int leftPtr = left - 1; // right of first elem
		int rightPtr = right + 1; // left of pivot
		while (true) {
			while (leftPtr < right && // find bigger item
					theArray[++leftPtr] < pivot)
				;
			while (rightPtr > left && // find smaller item
					theArray[--rightPtr] > pivot)
				;
			if (leftPtr >= rightPtr) // if pointers cross,
				break; // partition done
			else // not crossed, so
				swap(arr, leftPtr, rightPtr); // swap elements
		} // end while(true)
		return leftPtr; // return partition
	} // end partitionIt()

	public static void swap(SimpleArray arr, int dex1, int dex2)

	{
		long[] theArray = arr.getTheArray();
		long temp;
		temp = theArray[dex1];
		theArray[dex1] = theArray[dex2];
		theArray[dex2] = temp;
	}

	public static void main(String[] args) {
		int maxSize = 16;
		SimpleArray arr;
		arr = new SimpleArray(maxSize);

		for (int j = 0; j < maxSize; j++) {
			long n = (int) (java.lang.Math.random() * 199);
			arr.insert(n);
		}
		arr.display();

		long pivot = 99;
		System.out.print("Pivot is " + pivot);
		int size = arr.size();

		int partDex = partitionIt(arr, 0, size - 1, pivot);

		System.out.println(", Partition is at index " + partDex);
		arr.display();
	}
}
