package shi.alg.ch23.ttt.searcher;

import java.util.List;
import java.util.Random;
import java.util.Vector;

import shi.alg.ch23.core.GameState;
import shi.alg.ch23.core.Searcher;
import shi.alg.ch23.ttt.GameConst;
import shi.alg.ch23.ttt.TTTGameState;

/**
 * 利用 max(a,b)=-min(-a,-b) 统一形式
 * 
 * @author shi
 *
 */
public class NegamaxSearcher implements Searcher {

	private int searcherCounter;
	Random rand = new Random();

	@Override
	public int searchBestPlay(GameState state, int depth) {

		List<Integer> bestCell = new Vector<Integer>();
		int bestValue = -GameConst.INFINITY;
		int bestPos = 0;

		searcherCounter = 0;

		for (int i = 0; i < GameState.BOARD_CELLS; i++) {
			GameState tryState = new TTTGameState(state);
			if (tryState.isEmptyCell(i)) {
				tryState.setGameCell(i, tryState.getCurrentPlayer());
				tryState.SwitchPlayer();
				int value = -NegaMax(tryState, depth - 1, -1, state.getCurrentPlayer());
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

		System.out.println("NegamaxSearcher " + searcherCounter + " (without Alpha-Beta)");

		return bestCell.get(bestPos);
	}

	private int NegaMax(GameState state, int depth, int color, int max_player_id) {

		if (state.isGameOver() || (depth == 0)) {
			searcherCounter++;
			int val = EvaluateNegaMax(state, max_player_id);
			return val;
		}

		int score = -GameConst.INFINITY;
		for (int i = 0; i < GameState.BOARD_CELLS; i++) {
			GameState tryState = new TTTGameState(state);
			if (tryState.isEmptyCell(i)) {
				tryState.setGameCell(i, tryState.getCurrentPlayer());
				tryState.SwitchPlayer();
				int value = -NegaMax(tryState, depth - 1, -color, max_player_id);
				score = Math.max(score, value);
			}
		}

		return score;
	}

	private int EvaluateNegaMax(GameState state, int max_player_id) {
		if (state.getCurrentPlayer() == max_player_id)
			return state.Evaluate(max_player_id);
		else
			return -state.Evaluate(max_player_id);
	}
}
