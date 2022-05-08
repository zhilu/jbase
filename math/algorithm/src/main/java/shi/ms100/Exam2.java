package shi.ms100;

import java.util.Stack;

/**
 * 定义栈的数据结构，要求添加一个min函数，能够得到栈的最小元素。
 * <p>
 * 要求函数min、push以及pop的时间复杂度都是O(1)。
 * 
 * @author shi
 *
 */
public class Exam2 {

	Stack<Integer> st = null;
	Stack<Integer> min = null;

	public Exam2() {
		st = new Stack<>();
		min = new Stack<>();
	}

	public void push(int x) {
		st.push(x);
		if (min.isEmpty()) {
			min.push(x);
		} else {
			Integer m = min.peek();
			if (x < m) {
				min.push(x);
			} else {
				min.push(m);
			}
		}

	}

	public Integer pop() {
		min.pop();
		return st.pop();
	}

	public int min() {
		return min.peek();
	}

	public static void main(String[] args) {
		Exam2 e = new Exam2();
		e.push(4);
		System.out.println(e.min());
		e.push(6);
		System.out.println(e.min());
		e.push(3);
		System.out.println(e.min());
		e.push(7);
		System.out.println(e.min());
		e.push(2);
		System.out.println(e.min());
		e.push(9);
		System.out.println(e.min());
		e.push(1);
		System.out.println(e.min());

		while (!e.isEmpty()) {
			System.out.println(e.min());
			e.pop();
		}
	}

	private boolean isEmpty() {
		return st.isEmpty();
	}
}
