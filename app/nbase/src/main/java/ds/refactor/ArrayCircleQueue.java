package ds.refactor;

/**
 * <br>����ʵ��ѭ������ ���У�
 * <br>�Ƚ��ȳ������ݽṹ 
 * <br>����������
 * <br>   1.�����ݣ����ڶ�ͷ��������   insert 
 * <br>   2.ȡ���ݣ���ȡ��β���ݲ�ɾ��   remove 
 * <br>   3.�鿴   peekFront
 * <br>����������
 * <br>   4.�Ƿ�Ϊ��   isEmpty
 * <br>   5.�Ƿ���  isFull 
 * <br>   6.����  size
 * @author shi
 *
 */
public class ArrayCircleQueue {
	private int maxSize;
	private long[] queArray;
	private int front;
	private int rear;
	private int nItems;

	public ArrayCircleQueue(int s) {
		maxSize = s;
		queArray = new long[maxSize];
		front = 0;
		rear = -1;
		nItems = 0;
	}

	// --------------------------------------------------------------
	public void insert(long j) {
		if (rear == maxSize - 1)
			rear = -1;
		queArray[++rear] = j;
		nItems++;
	}

	// --------------------------------------------------------------
	public long remove() {
		long temp = queArray[front++];
		if (front == maxSize)
			front = 0;
		nItems--;
		return temp;
	}

	// --------------------------------------------------------------
	public long peekFront() {
		return queArray[front];
	}

	// --------------------------------------------------------------
	public boolean isEmpty() {
		return (nItems == 0);
	}

	// --------------------------------------------------------------
	public boolean isFull() {
		return (nItems == maxSize);
	}

	// --------------------------------------------------------------
	public int size() {
		return nItems;
	}
	// --------------------------------------------------------------
} 
