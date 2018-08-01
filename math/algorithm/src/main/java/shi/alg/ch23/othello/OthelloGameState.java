package shi.alg.ch23.othello;

import java.util.LinkedList;
import java.util.List;

import shi.alg.ch23.core.Const;
import shi.alg.ch23.core.GameState;
import shi.alg.ch23.ttt.CellType;

public class OthelloGameState extends GameState {

	public static final int DUMMY = -1;

	List<Integer> emptys = null;

	public OthelloGameState() {
		super(GameConst.BOARD_ROW, GameConst.BOARD_COL);
		BOARD_CELLS=GameConst.BOARD_CELLS;
		board = new int[BOARD_CELLS];
		InitEmptyList();
		System.out.println("init "+emptys.size());
	}

	public OthelloGameState(GameState state) {
		super(state);
		OthelloGameState ostate = (OthelloGameState) state;
		this.emptys = new LinkedList<Integer>(ostate.emptys);
	}

	@Override
	public void initGameState(int firstPlayer) {
		for (int i = 0; i < GameConst.BOARD_CELLS; i++) {
			board[i] = DUMMY;
		}
		for (int i = 0; i < GameConst.GAME_CELLS; i++) {
			board[GameConst.cell2board[i]] = Const.PLAYER_NULL;
		}
		board[Othello.BOARD_CELL(4, 4)] = Const.PLAYER_A;
		board[Othello.BOARD_CELL(5, 4)] = Const.PLAYER_B;
		board[Othello.BOARD_CELL(4, 5)] = Const.PLAYER_B;
		board[Othello.BOARD_CELL(5, 5)] = Const.PLAYER_A;
		playerId = firstPlayer;
		InitEmptyList();
	}

	@Override
	public void printGame() {
		System.out.println("Current game state : ");
		System.out.print("  ");
		for (int col = 0; col < GameConst.GAME_COL; col++) {
			System.out.print(col + 1 + " ");
		}
		int row = 0;
		System.out.println();
		for (int i = 0; i < GameConst.GAME_CELLS; i++) {
			if ((i % GameConst.GAME_COL) == 0) {
				row++;
				System.out.print(row + " ");
			}
			System.out.print(getCellType(board[GameConst.cell2board[i]]).getValue());
			System.out.print(' ');
			if ((i % GameConst.GAME_COL) == 7) {
				System.out.println();
			}
		}
	}

	@Override
	public boolean isGameOver() {
		if (!testMoves(Const.PLAYER_A) && !testMoves(Const.PLAYER_B)) {
			return true;
		}
		return false;
	}

	@Override
	public int getWinner() {
		int ca = countCell(Const.PLAYER_A);
		int cb = countCell(Const.PLAYER_B);
		if (ca == cb) {
			return Const.PLAYER_NULL;
		} else {
			return (ca > cb) ? Const.PLAYER_A : Const.PLAYER_B;
		}
	}

	@Override
	public void setGameCell(int cell, int player_id) {
		System.out.println("before " + emptys.size());
		DoPutChess(cell, player_id);
		System.out.println("after " + emptys.size());
	}

	protected CellType getCellType(int player_id) {
		if (player_id == Const.PLAYER_NULL)
			return CellType.CELL_EMPTY;
		else
			return (player_id == Const.PLAYER_B) ? CellType.CELL_X : CellType.CELL_O;

	}

	private void InitEmptyList() {
		emptys = new LinkedList<Integer>();
		for (int i = 0; i < BOARD_CELLS; i++) {
			if (board[i] == Const.PLAYER_NULL) {
				emptys.add(i);
			}
		}
	}

	public void SingleDirFlips(int cell, int dir, int player_id, int opp_player_id) {
		int pt = cell + dir;
		if (board[pt] == opp_player_id) {
			while (board[pt] == opp_player_id) {
				pt += dir;
			}
			if (board[pt] == player_id) {
				pt -= dir;
				do {
					board[pt] = player_id;
					pt -= dir;
				} while (pt != cell);
			}
		}
	}

	public void DoFlips(int cell, int player_id, int opp_player_id) {
		/* 在8个方向试探 */
		for (int i = 0; i < 8; i++) {
			short mask = (short) (1 << i);
			if ((GameConst.dir_mask[cell] & mask) != 0) {
				SingleDirFlips(cell, GameConst.dir_inc[i], player_id, opp_player_id);
			}
		}
	}

	public boolean canSingleDirFlips(int cell, int dir, int player_id, int opp_player_id) {
		int pt = cell + dir;
		if (board[pt] == opp_player_id) {
			while (board[pt] == opp_player_id) {
				pt += dir;
			}
			return (board[pt] == player_id) ? true : false;
		}
		return false;
	}

	public boolean canFlips(int cell, int player_id, int opp_player_id) {
		/* 在8个方向试探，任何一个方向可以翻转对方的棋子就返回true */
		for (int i = 0; i < 8; i++) {
			short mask = (short) (1 << i);
			if ((GameConst.dir_mask[cell] & mask) != 0) {
				if (canSingleDirFlips(cell, GameConst.dir_inc[i], player_id, opp_player_id)) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean isValidPosition(int cell, int player_id) {
		if (emptys.contains(cell)) {
			int opp_player_id = Othello.getPeerPlayer(player_id);
			return canFlips(cell, player_id, opp_player_id);
		}
		return false;
	}

	public int countMobility(int player_id) {
		int opp_player_id = Othello.getPeerPlayer(player_id);
		int mobility = 0;
		for (Integer i : emptys) {
			if (canFlips(i, player_id, opp_player_id)) {
				mobility++;
			}
		}
		return mobility;
	}

	public void DoPutChess(int cell, int player_id) {
		int opp_player_id = Othello.getPeerPlayer(player_id);
		DoFlips(cell, player_id, opp_player_id);
		board[cell] = player_id;
		emptys.remove(new Integer(cell));
	}

	public int findMoves(int player_id, int opp_player_id, List<Integer> moves) {
		moves.clear();
		for (Integer cell : emptys) {
			if (canFlips(cell, player_id, opp_player_id)) {
				moves.add(cell);
			}
		}
		return moves.size();
	}

	public int countEmptyCells() {
		return emptys.size();
	}

	public int countCell(int playerId) {
		int count = 0;
		for (int i = 0; i < BOARD_CELLS; i++) {
			if (board[i] == playerId) {
				count++;
			}
		}
		return count;
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

	public boolean testMoves(int playerA) {
		int opp_player_id = Othello.getPeerPlayer(playerA);
		for (Integer cell : emptys) {
			if (canFlips(cell, playerA, opp_player_id)) {
				return true;
			}
		}
		return false;
	}
}
