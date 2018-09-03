package alg.sort;

public class SortLibrary {

	private SortLibrary() {
	}
	// �Ƚϴ�С����

	private static boolean less(Comparable v, Comparable w) {
		return (v.compareTo(w) < 0);
	}

	// ����λ�ú���
	private static void exch(Comparable[] a, int i, int j) {
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	/**
	 * ѡ������˼�룺 <br>
	 * 1����λ��Сֵ����һ������<br>
	 * 2����ʣ�µ������ж�λ��С���͵ڶ�������<br>
	 * 3���Դ˽���<br>
	 * <br>
	 * �Ƚϴ�����N^2/2<br>
	 * ����������N
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
	 * ��������˼�룺<br>
	 * 1������һ���������򣬲���ڶ���<br>
	 * 2�����κ�ǰ��ıȽϣ�������ֹͣ��С���򽻻�<br>
	 * 3���Դν��� <br>
	 * <br>
	 * �Ƚϴ�����<br>
	 * � N^2/2��<br>
	 * ��� N-1 ����������<br>
	 * �N^2/2�����0
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
	 * ð������˼�룺<br>
	 * 1���Ӻ���ǰ����������������������С��ǰ��ģ��򽻻���һ��֮�󣬵�һ����С<br>
	 * 2���ӵڶ�����ʼ���Ӻ���ǰ����������������������С��ǰ��ģ��򽻻�����ڶ����ڶ�С<br>
	 * 3���Դν��� <br>
	 * <br>
	 * �Ƚϴ�����<br>
	 * � N^2/2��<br>
	 * ��� N-1 �����������N^2/2�����0
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
