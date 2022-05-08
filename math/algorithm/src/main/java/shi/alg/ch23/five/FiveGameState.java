package shi.alg.ch23.five;

import java.util.List;

import shi.alg.ch23.core.Const;
import shi.alg.ch23.core.GameState;
import shi.alg.ch23.five.searcher.MOVES_LIST;
import shi.alg.ch23.ttt.CellType;

public class FiveGameState extends GameState {

	public static int DUMMY = -1;
	private boolean gameOver = false;
	private long hash;
	private int winnerId = Const.PLAYER_NULL;

	public FiveGameState() {
		super(9, 9);
	}

	public FiveGameState(FiveGameState state) {
		super(state);
		this.gameOver = state.gameOver;
		this.hash = state.hash;
		this.winnerId = state.winnerId;
	}

	@Override
	public void printGame() {
		System.out.println("Current game state : ");
		System.out.print("   ");
		for (int col = 0; col < GameConst.GAME_COL; col++) {
			char tit = (char) (col + 'A');
			System.out.print(tit + " ");
		}
		int row = 0;
		System.out.println();
		for (int i = 0; i < GameConst.GAME_CELLS; i++) {
			if ((i % GameConst.GAME_COL) == 0) {
				row++;
				System.out.print(row + " ");
			}

			System.out.print(getCellType(board[GameConst.cell2board[i]]));
			System.out.println(" ");
			if ((i % GameConst.GAME_COL) == (GameConst.GAME_COL - 1)) {
				System.out.println();
			}
		}
	}

	@Override
	public void initGameState(int firstPlayer) {
		int i;
		for (i = 0; i < BOARD_CELLS; i++) {
			board[i] = DUMMY;
		}
		for (i = 0; i < GameConst.GAME_CELLS; i++) {
			board[GameConst.cell2board[i]] = Const.PLAYER_NULL;
		}
		playerId = firstPlayer;
		gameOver = false;
		winnerId = Const.PLAYER_NULL;
		hash = 0;
	}

	@Override
	public boolean isGameOver() {
		return gameOver;
	}

	@Override
	public int getWinner() {
		if (isGameOver())
			return winnerId;
		return Const.PLAYER_NULL;
	}

	public int countEmptyCell() {
		int count = 0;
		for (int i = 0; i < BOARD_CELLS; i++) {
			if (board[i] == Const.PLAYER_NULL) {
				count++;
			}
		}
		return count;
	}

	protected CellType getCellType(int player_id) {
		if (player_id == Const.PLAYER_NULL)
			return CellType.CELL_EMPTY;
		else
			return (player_id == Const.PLAYER_B) ? CellType.CELL_X : CellType.CELL_O;
	}

	public int countPosValue(int player_id) {
		int value = 0;
		for (int i = 0; i < GameConst.GAME_CELLS; i++) {
			int cell = GameConst.cell2board[i];
			if (board[cell] == player_id) {
				value += GameConst.posValue[cell];
			}
		}
		return value;
	}

	boolean isValidPosition(int cell, int player_id) {
		if (board[cell] != Const.PLAYER_NULL)
			return false;

		return true;
	}

	boolean putChess(int cell, int player_id) {
		board[cell] = player_id;

		if (checkFiveInRow(cell, player_id)) {
			gameOver = true;
			winnerId = player_id;

			return true;
		}

		if (countEmptyCell() == 0) {
			gameOver = true;
			winnerId = Const.PLAYER_NULL;
		}

		return false;
	}

	public void doPutChess(int cell, int player_id) {

		int row = (cell - GameConst.BOARD_START) / BOARD_COL;
		int col = (cell - GameConst.BOARD_START) % BOARD_COL;

		int gc = row * GameConst.GAME_COL + col;
		hash ^= ZOBRIST_HASH.key[gc][board[cell]];
		board[cell] = player_id;
		hash ^= ZOBRIST_HASH.key[gc][board[cell]];
	}

	public void UndoPutChess(int cell) {

		int row = (cell - GameConst.BOARD_START) / BOARD_COL;
		int col = (cell - GameConst.BOARD_START) % BOARD_COL;

		int gc = row * GameConst.GAME_COL + col;
		hash ^= ZOBRIST_HASH.key[gc][board[cell]];
		board[cell] = Const.PLAYER_NULL;
		hash ^= ZOBRIST_HASH.key[gc][board[cell]];
	}

	public int searchPatterns(EvaluatorData ev_ata) {
		for (int i = 0; i < LINES.line_cpts.length; i++)// 每个方向
		{
			for (int j = 0; j < LINES.MAX_LINE_S; j++)// 每个方向9条线
			{
				analysisLine(LINES.line_cpts[i].line_s[j], LINES.line_cpts[i].off_dir, ev_ata);
			}
		}

		return 0;
	}

	public int skipEmptyCell(int cs, int dir_inc) {
		int ct = cs;
		while (board[ct] == Const.PLAYER_NULL) {
			ct += dir_inc;
		}
		return ct;
	}

	public int searchAndCountChess(int cs, int dir_inc, int chess_id, int count) {
		int ct = cs;
		while (board[ct] == chess_id) {
			count++;
			ct += dir_inc;
		}
		return ct;
	}

	public int analysisLine(int st, int dir_inc, EvaluatorData ev_ata) {
		int mark_cell, mark_player_id;
		int ct = st;
		while (board[ct] != DUMMY) {
			ct = skipEmptyCell(ct, dir_inc);// 向后跳过空位
			if (board[ct] == DUMMY) // 已经到哨兵位？直接结束
				break;

			mark_cell = ct;
			mark_player_id = board[ct];
			int count = 0;
			ct = searchAndCountChess(ct, dir_inc, mark_player_id, count);
			if (count >= 5) {
				ev_ata.increaseCounter(5, mark_player_id, false);
			} else if (count >= 2) {
				int pre_space = 0;
				int succ_space = 0;
				// 向前寻找空位
				int tmp_t = mark_cell - dir_inc;
				tmp_t = searchAndCountChess(tmp_t, -dir_inc, Const.PLAYER_NULL, pre_space);
				// 向后寻找空位
				ct = searchAndCountChess(ct, dir_inc, Const.PLAYER_NULL, succ_space);
				if ((board[ct] == mark_player_id) && (succ_space == 1)) {
					// 处理“跳”的情况
					count++; // 多了一个棋子
					int space_need = 5 - count;
					boolean succ_close = (board[ct + dir_inc] != Const.PLAYER_NULL);
					if ((pre_space + succ_space) >= space_need) {
						ev_ata.increaseCounter(count, mark_player_id, succ_close);
					}
				} else {
					// 除了count个连子之外，还需要5-count个空位，才能构成冲x或活x
					int space_need = 5 - count;
					// 两端都有空位，且任意一端的空位数大于等于space_need，直接定为活x
					if (((pre_space > 0) && (succ_space > 0))
							&& ((pre_space >= space_need) || (succ_space >= space_need))) {
						ev_ata.increaseCounter(count, mark_player_id, false);
					} else {
						// 两端是否有封闭
						boolean pre_close = (board[mark_cell - dir_inc] != Const.PLAYER_NULL);
						boolean succ_close = (board[ct] != Const.PLAYER_NULL);
						// 空位足够连成5子才统计
						if ((pre_space + succ_space) >= space_need) {
							ev_ata.increaseCounter(count, mark_player_id, pre_close || succ_close);
						}
					}
				}
			}
		}

		return 0;
	}

	public boolean checkFiveInRow(int cell, int player_id) {
		for (int i = 0; i < GameConst.DIR_COUNT; i++) {
			if (checkLinefive(cell, GameConst.dir_inc[i], player_id)) {
				return true;
			}
		}

		return false;
	}

	public boolean checkLinefive(int cell, int dir_inc, int player_id) {
		int count = 1;
		int ct = cell - dir_inc;
		while (board[ct] == player_id) {
			count++;
			ct -= dir_inc;
		}

		ct = cell + dir_inc;
		while (board[ct] == player_id) {
			count++;
			ct += dir_inc;
		}

		return (count >= 5);
	}

	public long calcZobristHash() {
		long hash = 0;
		for (int i = 0; i < GameConst.GAME_CELLS; i++) {
			int cell = GameConst.cell2board[i];
			hash ^= ZOBRIST_HASH.key[i][board[cell]];
		}

		return hash;
	}

	public boolean isForbidden(int cell, int player_id) {
		for (int i = 0; i < FORBIDDEN_ITEM.forbidden_patterns.length; i++) {
			if (isMatchSingleForbidden(FORBIDDEN_ITEM.forbidden_patterns[i], cell, player_id)) {
				return true;
			}
		}
		return false;
	}

	boolean isMatchSingleForbidden(FORBIDDEN_ITEM item, int cell, int player_id) {
		int match_cnt = 0;
		for (int j = 0; j < item.off_cnt; j++) {
			int cf = cell + item.off_inc[j];
			if ((cf >= 0) && (cf < BOARD_CELLS)) {
				match_cnt += ((board[cf] == player_id) ? 1 : 0);
			}
		}

		return (match_cnt == item.off_cnt);
	}

	public int FindMoves(int player_id, List<MOVES_LIST> moves) {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getHash() {
		return hash;
	}
}
