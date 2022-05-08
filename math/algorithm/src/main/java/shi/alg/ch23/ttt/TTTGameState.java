package shi.alg.ch23.ttt;

import shi.alg.ch23.core.Const;
import shi.alg.ch23.core.GameControl;
import shi.alg.ch23.core.GameState;

public class TTTGameState extends GameState {

	public TTTGameState(){
		super(3,3);
	}
	
	public TTTGameState(GameState state) {
		super(state);
	}

	public void printGame() {
		System.out.println("Current game state : ");
		for (int i = 0; i < BOARD_CELLS; i++) {
			System.out.print(getCellType(board[i]).getValue());
			if ((i % BOARD_COL) == 2) {
				System.out.println();
			}
		}
	}
	protected CellType getCellType(int player_id) {
		if(player_id == Const.PLAYER_NULL)
	        return CellType.CELL_EMPTY;
	    else
	        return (player_id == Const.PLAYER_B) ? CellType.CELL_X : CellType.CELL_O;

	}

	protected int countThreeLine(int player_id) {
		int lines = 0;
		for (int i = 0; i < GameConst.LINE_DIRECTION; i++) {
			int sameCount = 0;
			for (int j = 0; j < GameConst.LINE_CELLS; j++) {
				if (board[GameConst.line_idx_tbl[i][j]] == player_id) {
					sameCount++;
				}
			}
			if (sameCount == 3) {
				lines++;
			}
		}
		return lines;
	}

	protected int countEmptyCell() {
		int count = 0;
		int i;
		for (i = 0; i < BOARD_CELLS; i++) {
			if (board[i] == Const.PLAYER_NULL) {
				count++;
			}
		}
		return count;
	}
	
	public boolean isGameOver() {
		if (countEmptyCell() == 0)
			return true;

		int aThree = countThreeLine(playerId);
		if (aThree != 0)
			return true;

		int min = GameControl.getPeerPlayer(playerId);
		int bThree = countThreeLine(min);
		if (bThree != 0)
			return true;

		return false;

	}

	public int getWinner() {
		int aThree = countThreeLine(playerId);
		if (aThree != 0)
			return playerId;

		int min = GameControl.getPeerPlayer(playerId);
		int bThree = countThreeLine(min);
		if (bThree != 0)
			return min;

		return Const.PLAYER_NULL;
	}

}
