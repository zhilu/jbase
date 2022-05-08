package shi.alg.ch23.ttt.searcher;

import java.util.List;
import java.util.Random;
import java.util.Vector;

import shi.alg.ch23.core.GameState;
import shi.alg.ch23.core.Searcher;
import shi.alg.ch23.ttt.GameConst;
import shi.alg.ch23.ttt.TTTGameState;

public class NegamaxAlphaBetaSearcher implements Searcher {

	private int searcherCounter = 0;
	Random r = new Random();
	@Override
	public int searchBestPlay(GameState state, int depth) {
		List<Integer> bestCell = new Vector<Integer>();
	    int bestValue = -GameConst.INFINITY;
	    int bestPos = 0;
	    searcherCounter = 0;

	    for(int i = 0; i < GameState.BOARD_CELLS; i++)
	    {
	        GameState tryState = new TTTGameState(state);
	        if(tryState.isEmptyCell(i))
	        {
	            tryState.setGameCell(i, tryState.getCurrentPlayer());
	            tryState.SwitchPlayer();
	            int value = -NegaMax(tryState, depth - 1, -GameConst.INFINITY, GameConst.INFINITY, state.getCurrentPlayer());
	            if(value > bestValue)
	            {
	                bestValue = value;
	                bestCell.clear();
	                bestCell.add(i);
	            }
	            else if(value == bestValue)
	            {
	                bestCell.add(i);
	            }
	        }
	    }

	    if(bestCell.size() > 0)
	        bestPos = r.nextInt(bestCell.size()) ;

	    System.out.println("NegamaxSearcher " + searcherCounter + " (with Alpha-Beta)");

	    return bestCell.get(bestPos);
	}
	private int NegaMax(GameState state, int depth, int alpha, int beta, int max_player_id) {
	    if(state.isGameOver() || (depth == 0))
	    {
	        searcherCounter++;
	        return evaluateNegaMax(state, max_player_id);
	        //return state.Evaluate(max_player_id);
	    }
	    
	    int score = -GameConst.INFINITY;
	    for(int i = 0; i < GameState.BOARD_CELLS; i++)
	    {
	        GameState tryState = new TTTGameState(state);
	        if(tryState.isEmptyCell(i))
	        {
	            tryState.setGameCell(i, tryState.getCurrentPlayer());
	            tryState.SwitchPlayer();
	            int value = -NegaMax(tryState, depth - 1, -beta, -alpha, max_player_id);
	            score = Math.max(score, value);
	            alpha = Math.max(alpha, value);
	            if(beta <= alpha)
	                break;
	        }
	    }
	    
	    return score;
	}
	private int evaluateNegaMax(GameState state, int max_player_id) {
		if(state.getCurrentPlayer() == max_player_id)
	        return state.Evaluate(max_player_id);
	    else
	        return -state.Evaluate(max_player_id);
	}

}
