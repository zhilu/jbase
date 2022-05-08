package shi.alg.ch23.core;

import java.util.Arrays;

public abstract class GameState {

	public static int BOARD_ROW = 0;
	public static int BOARD_COL = 0;
	public static int BOARD_CELLS = BOARD_ROW * BOARD_COL;
	
	protected int playerId;
	protected Evaluator evaluator = null;
	protected int[] board=null;

	public GameState(int row,int col){
		BOARD_ROW=row;
		BOARD_COL=col;
		BOARD_CELLS = BOARD_ROW * BOARD_COL;
		board = new int[BOARD_CELLS];
	}
	
	public GameState(GameState state){
		this.playerId=state.playerId;
		this.evaluator=state.evaluator;
		this.board=state.board.clone();
	}
	public void setCurrentPlayer(int player_id) {
		playerId = player_id;
	}

	public int getCurrentPlayer() {
		return playerId;
	}

	public void setGameCell(int cell, int player_id) {
		board[cell] = player_id;
	}

	public int getGameCell(int cell) {
		return board[cell];
	}

	public boolean isEmptyCell(int cell) {
		return board[cell] == Const.PLAYER_NULL;
	}

	

	public void initGameState(int firstPlayer) {
		for (int i = 0; i < BOARD_CELLS; i++) {
			board[i] = Const.PLAYER_NULL;
		}
		playerId = firstPlayer;
	}

	public void SetEvaluator(Evaluator evaluator) {
		this.evaluator = evaluator;
	}

	public int Evaluate(int max_player_id) {
		return evaluator.evaluate(this, max_player_id);
	}

	public void SwitchPlayer() {
		playerId = GameControl.getPeerPlayer(playerId);
	}

	public abstract void printGame();
	
	public abstract boolean isGameOver();

	public abstract int getWinner() ;

	
	@Override
	public String toString() {
		return "GameState [playerId=" + playerId + ", board=" + Arrays.toString(board) + "]";
	}
	
	
	
}
