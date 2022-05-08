package shi.alg.ch23.othello.evaluator;

import shi.alg.ch23.core.Evaluator;
import shi.alg.ch23.core.GameState;
import shi.alg.ch23.othello.Othello;
import shi.alg.ch23.othello.OthelloGameState;

public class OdEvaluator implements Evaluator {

	@Override
	public int evaluate(GameState state, int max_player_id) {
		OthelloGameState ostate = (OthelloGameState) state;
		int min =  Othello.getPeerPlayer(max_player_id);

	    int ev = (ostate.countPosValue(max_player_id) - ostate.countPosValue(min)) * 2;
	    ev += (ostate.countMobility(max_player_id) - ostate.countMobility(min)) * 7;
	    
	    return ev;
	}

}
