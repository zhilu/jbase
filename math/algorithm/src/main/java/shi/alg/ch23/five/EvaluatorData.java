package shi.alg.ch23.five;

public class EvaluatorData {
	public static int C_TWO = 0;
	public static int H_TWO = 1;
	public static int C_THREE = 2;
	public static int H_THREE = 3;
	public static int C_FOUR = 4;
	public static int H_FOUR = 5;
	public static int S_FIVE = 6;
	int[][] counter;

	public EvaluatorData() {
		counter = new int[2][7];
	}

	public void increaseCounter(int rl_count, int player_id, boolean bClose) {
		int player_idx = player_id - 1;
		int type_base = (rl_count - 2) * 2;
		if (rl_count != 5) {
			type_base += (bClose ? 0 : 1);
		}
		counter[player_idx][type_base]++;
	}

	public int getCounter(int rl_count, int player_id, boolean bClose) {
		int player_idx = player_id - 1;
		int type_base = (rl_count - 2) * 2;
		if (rl_count != 5) {
			type_base += (bClose ? 0 : 1);
		}
		return counter[player_idx][type_base];
	}
}
