package ds.refactor;

/**
 * 数组实现栈
 * 栈先进后出的数据结构
 * 主要操作：
 *     1.存数据，即压栈  : push
 *     2.取数据，即取栈顶数据并删除：pop
 * 其他操作：
 *     3.查看，即查看栈顶数据并不删除：peek
 *     4.是否为空：isEmpty
 *     5.是否满栈：isFull
 * ps：存取应校验
 * @author shi
 *
 */
public class ArrayStack {
	private int maxSize;             //栈最大容量
	private Object[] stackArray;       //栈存储空间
	private int top;
	
	/**
	 * 构造器
	 * @param s  容量
	 */
	public ArrayStack(int s) {
		maxSize = s;
		stackArray = new Object[maxSize];
		top = -1;
	}

	public void push(Object j) {
		stackArray[++top] = j;
	}

	public Object pop() {
		return stackArray[top--];
	}

	// --------------------------------------------------------------
	public Object peek() {
		return stackArray[top];
	}

	public boolean isEmpty() {
		return (top == -1);
	}

	public boolean isFull() {
		return (top == maxSize - 1);
	}

}
