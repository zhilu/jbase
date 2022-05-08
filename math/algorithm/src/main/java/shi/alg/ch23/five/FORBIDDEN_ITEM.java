package shi.alg.ch23.five;

public class FORBIDDEN_ITEM {
	public static int MAX_FORBIDDEN_PATTERN = 12;

	int[] off_inc = new int[MAX_FORBIDDEN_PATTERN];
	int off_cnt;

	public static FORBIDDEN_ITEM[] forbidden_patterns = new FORBIDDEN_ITEM[4];
	static {
		for (int i = 0; i < forbidden_patterns.length; i++) {
			forbidden_patterns[i] = new FORBIDDEN_ITEM();
		}
	}
}
