package shi.alg.ch23.ttt.searcher;

import java.util.List;
import java.util.Random;
import java.util.Vector;

import shi.alg.ch23.core.GameState;
import shi.alg.ch23.core.Searcher;
import shi.alg.ch23.ttt.GameConst;
import shi.alg.ch23.ttt.TTTGameState;

public class AlphaBetaSearcher implements Searcher {

	int searcherCounter;

	public AlphaBetaSearcher() {
		this.searcherCounter = 0;
	}

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
				int value = AlphaBeta(tryState, depth - 1, -GameConst.INFINITY, GameConst.INFINITY,
						state.getCurrentPlayer());
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
			bestPos = new Random().nextInt(bestCell.size());

		System.out.println("MinimaxSearcher " + searcherCounter + " (with Alpha-Beta)" );
		return bestCell.get(bestPos);
	}

	private int AlphaBeta(GameState state, int depth, int alpha, int beta, int max_player_id) {
		if (state.isGameOver() || (depth == 0)) {
			searcherCounter++;
			return state.Evaluate(max_player_id);
		}

		if (state.getCurrentPlayer() == max_player_id) /* 极大值节点 */
		{
			for (int i = 0; i < GameState.BOARD_CELLS; i++) {
				GameState tryState = new TTTGameState(state);
				if (tryState.isEmptyCell(i)) {
					tryState.setGameCell(i, tryState.getCurrentPlayer());
					tryState.SwitchPlayer();
					int value = AlphaBeta(tryState, depth - 1, alpha, beta, max_player_id);
					alpha = Math.max(alpha, value);
					if (beta <= alpha)/* beta 剪枝 */
						break;
				}
			}

			return alpha;
		} else {
			for (int i = 0; i < GameState.BOARD_CELLS; i++) {
				GameState tryState = new TTTGameState(state);
				if (tryState.isEmptyCell(i)) {
					tryState.setGameCell(i, tryState.getCurrentPlayer());
					tryState.SwitchPlayer();
					int value = AlphaBeta(tryState, depth - 1, alpha, beta, max_player_id);
					beta = Math.min(beta, value);
					if (beta <= alpha)/* alpha 剪枝 */
						break;
				}
			}

			return beta;
		}
	}
}
