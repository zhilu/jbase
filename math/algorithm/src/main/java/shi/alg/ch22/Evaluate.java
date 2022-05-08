package shi.alg.ch22;

public class Evaluate {

	public static int sum_n[] = { 0, 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 66, 78, 91, 105, 120, 136, 153, 171, 190,
			210 };

	public static boolean IsFullRowStatus(RussiaGame game, int row) {
		for (int i = 0; i < RC.GAME_COL; i++) {
			if (game.board[row + 1][i + 1] == 0)
				return false;
		}
		return true;
	}

	public static int GetLandingHeight(RussiaGame game, BShape bs, int row, int col) {
		return (RC.GAME_ROW - row);
	}

	public static int GetErodedPieceCellsMetric(RussiaGame game, BShape bs, int row, int col) {
		int erodedRow = 0;
		int erodedShape = 0;
		int i = game.top_row;
		while (i < RC.GAME_ROW) {
			if (IsFullRowStatus(game, i)) {
				erodedRow++;
				if ((i >= row) && (i <= (row + bs.height))) {
					int sline = i - row;
					for (int j = 0; j < bs.width; j++) {
						if (bs.shape[sline][j] != 0) {
							erodedShape++;
						}
					}
				}
			}
			i++;
		}

		return (erodedRow * erodedShape);
	}

	public static int GetBoardRowTransitions(RussiaGame game, BShape bs, int row, int col) {
		int transitions = 0;
		for (int i = game.top_row; i < RC.GAME_ROW; i++) {
			for (int j = 0; j < (RC.BOARD_COL - 1); j++) {
				if ((game.board[i + 1][j] != 0) && (game.board[i + 1][j + 1] == 0)) {
					transitions++;
				}
				if ((game.board[i + 1][j] == 0) && (game.board[i + 1][j + 1] != 0)) {
					transitions++;
				}
			}
		}

		return transitions;
	}

	public static int GetBoardColTransitions(RussiaGame game, BShape bs, int row, int col) {
		int transitions = 0;
		for (int j = 0; j < RC.GAME_COL; j++) {
			for (int i = game.top_row; i < RC.GAME_ROW; i++) {
				if ((game.board[i + 1][j + 1] != 0) && (game.board[i + 2][j + 1] == 0)) {
					transitions++;
				}
				if ((game.board[i + 1][j + 1] == 0) && (game.board[i + 2][j + 1] != 0)) {
					transitions++;
				}
			}
		}

		return transitions;
	}

	// 连续的空Cell记为一个hole

	public static int GetBoardBuriedHoles(RussiaGame game, BShape bs, int row, int col) {
		int holes = 0;
		for (int j = 0; j < RC.GAME_COL; j++) {
			int i = game.top_row;
			while ((game.board[i + 1][j + 1] == 0) && (i < RC.GAME_ROW))
				i++;
			while (i < RC.GAME_ROW) {
				if (game.board[i + 1][j + 1] == 0) {
					holes++;
				}
				i++;
			}
		}

		return holes;
	}

	public static int GetBoardWells(RussiaGame game, BShape bs, int row, int col) {
		int wells = 0;
		int sum = 0;
		for (int j = 0; j < RC.GAME_COL; j++) {
			for (int i = game.top_row; i <= RC.GAME_ROW; i++) {
				if (game.board[i + 1][j + 1] == 0) {
					if ((game.board[i + 1][j] != 0) && (game.board[i + 1][j + 2] != 0)) {
						wells++;
					}
				} else {
					sum += sum_n[wells];
					wells = 0;
				}
			}
		}

		return sum;
	}

	// Pierre Dellacherie算法评价函数
	/*
	 * rating = (-1.0) * landingHeight + ( 1.0) * erodedPieceCellsMetric +
	 * (-1.0) * boardRowTransitions + (-1.0) * boardColTransitions + (-4.0) *
	 * boardBuriedHoles + (-1.0) * boardWells;
	 * 
	 * 1 -4.500158825082766 2 3.4181268101392694 3 -3.2178882868487753 4
	 * -9.348695305445199 5 -7.899265427351652 6 -3.3855972247263626
	 * 
	 */
	public static int EvaluateFunction(RussiaGame game, BShape bs, int row, int col) {
		int evalue = 0;

		int lh = GetLandingHeight(game, bs, row, col);
		int epcm = GetErodedPieceCellsMetric(game, bs, row, col);
		int brt = GetBoardRowTransitions(game, bs, row, col);
		int bct = GetBoardColTransitions(game, bs, row, col);
		int bbh = GetBoardBuriedHoles(game, bs, row, col);
		int bw = GetBoardWells(game, bs, row, col);

		evalue = (-1) * lh + epcm - (4 * bbh) - (1 * bw);
		// evalue = (-1) * lh + epcm - (0.5 * brt) - (0.5 * bct) - (6 * bbh) -
		// (1 * bw);
		// evalue = (-1) * lh + epcm - brt - bct - (8 * bbh) - (2 * bw);
		// evalue = (-1) * lh + epcm - brt - bct - (4 * bbh) - bw;

		return evalue;
	}

	/*
	 * 若落子落于左侧:priority = 100 * 落子水平平移格子数 + 10 + 落子旋转次数;
	 * 
	 * 若落子落于右侧:priority = 100 * 落子水平平移格子数 + 落子旋转次数;
	 */
	public static int PrioritySelection(RussiaGame game, int r_index, int row, int col) {
		int priority = 0;

		if (col < (RC.GAME_COL / 2)) {
			priority = 100 * ((RC.GAME_COL / 2 - 1) - col) + 10 + r_index;
		} else {
			priority = 100 * (col - (RC.GAME_COL / 2)) + r_index;
		}

		return priority;
	}
}
