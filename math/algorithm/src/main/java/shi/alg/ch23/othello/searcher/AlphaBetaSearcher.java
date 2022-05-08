package shi.alg.ch23.othello.searcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import shi.alg.ch23.core.GameState;
import shi.alg.ch23.core.Searcher;
import shi.alg.ch23.othello.GameConst;
import shi.alg.ch23.othello.Othello;
import shi.alg.ch23.othello.OthelloGameState;

public class AlphaBetaSearcher implements Searcher {

	private Random r = new Random();
	private int searcherCounter = 0;

	@Override
	public int searchBestPlay(GameState state, int depth) {
		List<Integer> bestCell = new Vector<Integer>();
		int bestValue = -GameConst.INFINITY;
		int bestPos = 0;
		searcherCounter = 0;

		OthelloGameState tryState = new OthelloGameState(state);
		int playerId = tryState.getCurrentPlayer();
		int oddPlayerId = Othello.getPeerPlayer(playerId);
		List<Integer> validPos = new ArrayList<Integer>();
		int moves = tryState.findMoves(playerId, oddPlayerId, validPos);
		if (moves == 0) {
			return -1;
		}
		for (Integer cell : validPos) {
			tryState.DoPutChess(cell, tryState.getCurrentPlayer());
			tryState.SwitchPlayer();
			int value = AlphaBeta(tryState, depth - 1, -GameConst.INFINITY, GameConst.INFINITY,
					state.getCurrentPlayer());
			if (value > bestValue) {
				bestValue = value;
				bestCell.clear();
				bestCell.add(cell);
			} else if (value == bestValue) {
				bestCell.add(cell);
			}
		}

		if (bestCell.size() > 0)
			bestPos = r.nextInt(bestCell.size());

		System.out.println("MinimaxSearcher " + searcherCounter + " (with Alpha-Beta)");

		return bestCell.get(bestPos);
	}

	private int AlphaBeta(OthelloGameState state, int depth, int alpha, int beta, int max_player_id) {

		if (state.isGameOver() || (depth == 0)) {
			searcherCounter++;
			return state.Evaluate(max_player_id);
		}

		List<Integer> validPos = new ArrayList<Integer>();
		int playerId = state.getCurrentPlayer();
		int oddPlayerId = Othello.getPeerPlayer(playerId);
		int moves = state.findMoves(state.getCurrentPlayer(), oddPlayerId, validPos);
		if (moves == 0) {
			return -1;
		}

		if (state.getCurrentPlayer() == max_player_id) /* 极大值节点 */
		{
			for (Integer cell : validPos) {
				OthelloGameState tryState = new OthelloGameState(state);
				tryState.DoPutChess(cell, tryState.getCurrentPlayer());
				tryState.SwitchPlayer();
				int value = AlphaBeta(tryState, depth - 1, alpha, beta, max_player_id);
				alpha = Math.max(alpha, value);
				if (beta <= alpha)/* beta 剪枝 */
					break;
			}
			return alpha;
		} else {
			for (Integer cell : validPos) {
				OthelloGameState tryState = new OthelloGameState(state);
				tryState.DoPutChess(cell, tryState.getCurrentPlayer());
				tryState.SwitchPlayer();
				int value = AlphaBeta(tryState, depth - 1, alpha, beta, max_player_id);
				beta = Math.min(beta, value);
				if (beta <= alpha)/* alpha 剪枝 */
					break;
			}

			return beta;
		}
	}

}
