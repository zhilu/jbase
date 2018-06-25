package ds.refactor;

/**
 * <br>数组实现循环队列 队列：
 * <br>先进先出的数据结构 
 * <br>常见操作：
 * <br>   1.存数据，即在对头插入数据   insert 
 * <br>   2.取数据，即取对尾数据并删除   remove 
 * <br>   3.查看   peekFront
 * <br>其他操作：
 * <br>   4.是否为空   isEmpty
 * <br>   5.是否满  isFull 
 * <br>   6.容量  size
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
