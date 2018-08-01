package shi.alg.ch23.ttt.searcher;

import java.util.Random;
import java.util.Vector;

import shi.alg.ch23.core.GameState;
import shi.alg.ch23.core.Searcher;
import shi.alg.ch23.ttt.GameConst;
import shi.alg.ch23.ttt.TTTGameState;

public class MiniMaxSearcher implements Searcher {

	private int searcherCounter;

	private Random rand = new Random();

	public MiniMaxSearcher() {
		searcherCounter = 0;
	}
	@Override
	public int searchBestPlay(GameState state, int depth) {

		Vector<Integer> bestCell = new Vector<Integer>();
		int bestValue = -GameConst.INFINITY;
		int bestPos = 0;

		// debug 初始化
		searcherCounter = 0;
		for (int i = 0; i < GameState.BOARD_CELLS; i++) {
			GameState tryState = new TTTGameState(state);
			if (tryState.isEmptyCell(i)) {
				tryState.setGameCell(i, tryState.getCurrentPlayer());
				tryState.SwitchPlayer();
				int value = MiniMax(tryState, depth - 1, state.getCurrentPlayer());
				if (value > bestValue) {
					bestValue = value;
					bestCell.clear();
					bestCell.add(i);
				} else if (value == bestValue) {
					bestCell.add(i);
				}
			}
		}

		if (bestCell.size() > 0)
			bestPos = rand.nextInt(bestCell.size());
		System.out.println("MinimaxSearcher " + searcherCounter + " (without Alpha-Beta)");
		return bestCell.get(bestPos);
	}

	protected int MiniMax(GameState state, int depth, int max_player_id) {
		if (state.isGameOver() || (depth == 0)) {
			searcherCounter++; // debug
			return state.Evaluate(max_player_id);
		}

		int score = (state.getCurrentPlayer() == max_player_id) ? -GameConst.INFINITY : GameConst.INFINITY;
		for (int i = 0; i < GameState.BOARD_CELLS; i++) {
			GameState tryState = new TTTGameState(state);
			if (tryState.isEmptyCell(i))/* 此位置可以落子 */
			{
				tryState.setGameCell(i, tryState.getCurrentPlayer());
				tryState.SwitchPlayer();
				int value = MiniMax(tryState, depth - 1, max_player_id);
				if (state.getCurrentPlayer() == max_player_id) {
					score = Math.max(score, value);
				} else {
					score = Math.min(score, value);
				}
			}
		}
		return score;
	}

}
