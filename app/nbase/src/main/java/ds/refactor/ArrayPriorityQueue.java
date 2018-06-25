package ds.refactor;

/**
 * <br>����ʵ��ѭ������ ���У�
 * <br>�Ƚ��ȳ������ݽṹ 
 * <br>����������
 * <br>   1.�����ݣ����ڶ�ͷ��������   insert 
 * <br>   <b>ע�⣺  �ڲ���ʱ�򱣳�˳��</b>
 * <br>   2.ȡ���ݣ���ȡ��β���ݲ�ɾ��   remove 
 * <br>   3.�鿴   peekFront
 * <br>����������
 * <br>   4.�Ƿ�Ϊ��   isEmpty
 * <br>   5.�Ƿ���  isFull 
 * <br>   6.����  size
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