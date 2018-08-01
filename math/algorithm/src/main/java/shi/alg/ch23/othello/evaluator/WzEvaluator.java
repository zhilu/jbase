package shi.alg.ch23.othello.evaluator;

import shi.alg.ch23.core.Evaluator;
import shi.alg.ch23.core.GameState;
import shi.alg.ch23.othello.Othello;
import shi.alg.ch23.othello.OthelloGameState;

public class WzEvaluator implements Evaluator {

	@Override
	public int evaluate(GameState gameState, int max_player_id) {
		OthelloGameState ostate = (OthelloGameState) gameState;
		int min =  Othello.getPeerPlayer(max_player_id);
	    int empty = ostate.countEmptyCells();
	    
	    int ev = 0;
	    if(empty >= 40) /*只考虑行动力*/
	    {
	        ev += (ostate.countMobility(max_player_id) - ostate.countMobility(min)) * 7;
	    }
	    else if((empty >= 18) && (empty < 40))
	    {
	        ev += (ostate.countPosValue(max_player_id) - ostate.countPosValue(min)) * 2;
	        ev += (ostate.countMobility(max_player_id) - ostate.countMobility(min)) * 7;
	    }
	    else
	    {
	        ev += (ostate.countPosValue(max_player_id) - ostate.countPosValue(min)) * 2;
	        ev += (ostate.countMobility(max_player_id) - ostate.countMobility(min)) * 7;
	        ev += (ostate.countCell(max_player_id) - ostate.countCell(min)) * 2;
	    }
	    
	    return ev;
	}

}
