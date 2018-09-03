package alg.sort;

public class SortLibrary {

	private SortLibrary() {
	}
	// 比较大小函数

	private static boolean less(Comparable v, Comparable w) {
		return (v.compareTo(w) < 0);
	}

	// 交换位置函数
	private static void exch(Comparable[] a, int i, int j) {
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	/**
	 * 选择排序思想： <br>
	 * 1、定位最小值，和一个交换<br>
	 * 2、在剩下的数据中定位最小，和第二个交换<br>
	 * 3、以此进行<br>
	 * <br>
	 * 比较次数：N^2/2<br>
	 * 交换次数：N
	 */
	public static void selection(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int min = i;
			for (int j = i + 1; j < N; j++) {
				if (less(a[j], a[min])) {
					min = j;
				}
			}
			exch(a, i, min);
		}
	}

	/**
	 * 插入排序思想：<br>
	 * 1、将第一个看成有序，插入第二个<br>
	 * 2、依次和前面的比较，大于则停止，小于则交换<br>
	 * 3、以次进行 <br>
	 * <br>
	 * 比较次数：<br>
	 * 最坏 N^2/2，<br>
	 * 最好 N-1 交换次数：<br>
	 * 最坏N^2/2，最好0
	 */
	public static void insertion(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			for (int j = i; j > 0; j--) {
				if (less(a[j], a[j - 1])) {
					exch(a, j, j - 1);
				}

			}
		}
	}

	/**
	 * 冒泡排序思想：<br>
	 * 1、从后向前遍历，如果相邻两个后面的小于前面的，则交换，一次之后，第一个最小<br>
	 * 2、从第二个开始，从后向前遍历，如果相邻两个后面的小于前面的，则交换，则第二个第二小<br>
	 * 3、以次进行 <br>
	 * <br>
	 * 比较次数：<br>
	 * 最坏 N^2/2，<br>
	 * 最好 N-1 交换次数：最坏N^2/2，最好0
	 */
	public static void bubble(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			for (int j = N - 1; j > i; j--) {
				if (less(a[j], a[j - 1])) {
					exch(a, j, j - 1);
				}

			}
		}
	}
	

}
