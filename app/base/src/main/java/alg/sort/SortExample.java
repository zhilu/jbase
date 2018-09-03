package alg.sort;

/**
 * 具体实现某个排序算法时候可以实现sort方法
 * @author shi
 *
 */
public class SortExample {
	
	public static void sort(Comparable[] a) {
		// 排序具体实现
	}

	private static boolean less(Comparable v, Comparable w) {
		return (v.compareTo(w) < 0);
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	public static boolean isSorted(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			if (less(a[i], a[i - 1])) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String[] a = args;
		sort(a);
		assert isSorted(a);
		show(a);
	}
}
