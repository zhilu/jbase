package ds.refactor;

/**
 * <br>数组实现循环优先 队列：
 * <br>先进先出的数据结构 
 * <br>常见操作：
 * <br>   1.存数据，即在对头插入数据   insert 
 * <br>   <b>注意：  在插入时候保持顺序</b>
 * <br>   2.取数据，即取对尾数据并删除   remove 
 * <br>   3.查看   peekFront
 * <br>其他操作：
 * <br>   4.是否为空   isEmpty
 * <br>   5.是否满  isFull 
 * <br>   6.容量  size
 * @author shi
 *
 */
public class ArrayPriorityQueue {
	private int maxSize;
	private long[] queArray;
	private int nItems;

	// -------------------------------------------------------------
	public ArrayPriorityQueue(int s) {
		maxSize = s;
		queArray = new long[maxSize];
		nItems = 0;
	}

	// -------------------------------------------------------------
	public void insert(long item) {
		int j;

		if (nItems == 0) // if no items,
			queArray[nItems++] = item; // insert at 0
		else // if items,
		{
			for (j = nItems - 1; j >= 0; j--) // start at end,
			{
				if (item > queArray[j]) // if new item larger,
					queArray[j + 1] = queArray[j]; // shift upward
				else // if smaller,
					break; // done shifting
			} // end for
			queArray[j + 1] = item; // insert it
			nItems++;
		} // end else (nItems > 0)
	} // end insert()
		// -------------------------------------------------------------

	public long remove() {
		return queArray[--nItems];
	}

	// -------------------------------------------------------------
	public long peekMin() {
		return queArray[nItems - 1];
	}

	// -------------------------------------------------------------
	public boolean isEmpty() {
		return (nItems == 0);
	}

	// -------------------------------------------------------------
	public boolean isFull() {
		return (nItems == maxSize);
	}
	// -------------------------------------------------------------
} // end class PriorityQ