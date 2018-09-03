package ds.refactor;

/**
 * ����ʵ��ջ
 * ջ�Ƚ���������ݽṹ
 * ��Ҫ������
 *     1.�����ݣ���ѹջ  : push
 *     2.ȡ���ݣ���ȡջ�����ݲ�ɾ����pop
 * ����������
 *     3.�鿴�����鿴ջ�����ݲ���ɾ����peek
 *     4.�Ƿ�Ϊ�գ�isEmpty
 *     5.�Ƿ���ջ��isFull
 * ps����ȡӦУ��
 * @author shi
 *
 */
public class ArrayStack {
	private int maxSize;             //ջ�������
	private Object[] stackArray;       //ջ�洢�ռ�
	private int top;
	
	/**
	 * ������
	 * @param s  ����
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
