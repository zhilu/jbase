package shi.alg.ch23.five.searcher;

import java.util.List;
import java.util.Random;
import java.util.Vector;

import shi.alg.ch23.core.GameState;
import shi.alg.ch23.core.Searcher;
import shi.alg.ch23.five.FiveGameState;
import shi.alg.ch23.five.GameConst;
import shi.alg.ch23.five.TT_ENTRY;
import shi.alg.ch23.five.TranspositionTable;

public class NegamaxAlphaBetaSearcher implements Searcher {

	public static int TT_FLAG_EXACT = 1;
	public static int TT_FLAG_LOWERBOUND = 2;
	public static int TT_FLAG_UPPERBOUND = 3;
	private int searcherCounter = 0;
	private Random r = new Random();

	@Override
	public int searchBestPlay(GameState state, int depth) {
		List<Integer> bestCell = new Vector<Integer>();
		int bestValue = -GameConst.INFINITY;
		int bestPos = 0;
		searcherCounter = 0;

		TranspositionTable.ResetTranspositionTable();

		FiveGameState tryState = new FiveGameState((FiveGameState) state);
		int player_id = state.getCurrentPlayer();

		List<MOVES_LIST> moves = new Vector<MOVES_LIST>();
		int mc = tryState.FindMoves(player_id, moves);
		if (mc == 0) // 遇到无路可走的情况，比如被对方逼着走禁手，可放弃一步
		{
			return -1;
		}
		SortMoves(moves);
		for (int i = 0; i < mc; i++) {
			tryState.doPutChess(moves.get(i).cell, player_id);
			int value = NegaMax(tryState, depth - 1, -GameConst.INFINITY, GameConst.INFINITY, player_id);
			tryState.UndoPutChess(moves.get(i).cell);
			if (value > bestValue) {
				bestValue = value;
				bestCell.clear();
				bestCell.add(moves.get(i).cell);
			} else if (value == bestValue) {
				bestCell.add(moves.get(i).cell);
			}
		}

		if (bestCell.size() > 0)
			bestPos = r.nextInt(bestCell.size());
		System.out.println("NegamaxSearcher " + searcherCounter + " (with Alpha-Beta)");

		return bestCell.get(bestPos);
	}

	private void SortMoves(List<MOVES_LIST> moves) {
		// TODO Auto-generated method stub

	}

	private int NegaMax(FiveGameState state, int depth, int alpha, int beta, int max_player_id) {
		int alphaOrig = alpha;
		long state_hash = state.getHash();

		// 查询置换表
		TT_ENTRY ttEntry = new TT_ENTRY();
		if (TranspositionTable.LookupTranspositionTable(state_hash, ttEntry) && (ttEntry.depth >= depth)) {
			if (ttEntry.flag == TT_FLAG_EXACT)
				return ttEntry.value;
			else if (ttEntry.flag == TT_FLAG_LOWERBOUND)
				alpha = Math.max(alpha, ttEntry.value);
			else// if(ttEntry.flag == TT_FLAG_UPPERBOUND)
				beta = Math.min(beta, ttEntry.value);

			if (beta <= alpha)
				return ttEntry.value;
		}
		if (state.isGameOver() || (depth == 0)) {
			searcherCounter++;
			return EvaluateNegaMax(state, max_player_id);
		}

		state.SwitchPlayer();
		int score = -GameConst.INFINITY;
		int player_id = state.getCurrentPlayer();

		List<MOVES_LIST> moves = new Vector<MOVES_LIST>();
		int mc = state.FindMoves(player_id, moves);
		SortMoves(moves);
		for (int i = 0; i < mc; i++) {
			state.doPutChess(moves.get(i).cell, player_id);
			int value = -NegaMax(state, depth - 1, -beta, -alpha, max_player_id);
			state.UndoPutChess(moves.get(i).cell);
			score = Math.max(score, value);
			alpha = Math.max(alpha, value);
			if (beta <= alpha)
				break;
		}

		state.SwitchPlayer();
		// 写入置换表
		ttEntry.value = score;
		if (score <= alphaOrig)
			ttEntry.flag = TT_FLAG_UPPERBOUND;
		else if (score >= beta)
			ttEntry.flag = TT_FLAG_LOWERBOUND;
		else
			ttEntry.flag = TT_FLAG_EXACT;

		ttEntry.depth = depth;
		TranspositionTable.StoreTranspositionTable(state_hash, ttEntry);
		return score;
	}

	private int EvaluateNegaMax(GameState state, int max_player_id) {
		if (state.getCurrentPlayer() == max_player_id)
			return state.Evaluate(max_player_id);
		else
			return -state.Evaluate(max_player_id);
	}

}
