package shi.alg.ch23.five.evaluator;

import shi.alg.ch23.core.Evaluator;
import shi.alg.ch23.core.GameState;
import shi.alg.ch23.five.EvaluatorData;
import shi.alg.ch23.five.Five;
import shi.alg.ch23.five.FiveGameState;

public class WzEvaluator implements Evaluator {

	@Override
	public int evaluate(GameState gameState, int max_player_id) {
		 int opp_id =  Five.getPeerPlayer(max_player_id);

		    EvaluatorData ev_data = new EvaluatorData();
		    FiveGameState state =(FiveGameState) gameState;
		    state.searchPatterns(ev_data);

		    if(ev_data.getCounter(5, opp_id, false) > 0)
		        return -10000;
		    if( (ev_data.getCounter(4, opp_id, false) > 0) 
		        || (ev_data.getCounter(4, opp_id, true) > 1)
		        || ((ev_data.getCounter(4, opp_id, true) > 0)&&(ev_data.getCounter(3, opp_id, false) > 0)) )
		    {
		        return -9900;
		    }
		    if(ev_data.getCounter(3, opp_id, false) > 1) 
		    {
		        return -9800;
		    }
		    if( (ev_data.getCounter(3, opp_id, false) > 0) 
		        && (ev_data.getCounter(3, opp_id, true) > 0) )
		    {
		        return -9700;
		    }
		    if(ev_data.getCounter(4, opp_id, true) > 1) 
		    {
		        return -9600;
		    }
		    if( (ev_data.getCounter(4, opp_id, true) > 0) 
		        || (ev_data.getCounter(3, opp_id, false) > 0) )
		    {
		        return -9500;
		    }
		    if(ev_data.getCounter(5, max_player_id, false) > 0)
		        return 10000;
		    if( (ev_data.getCounter(4, max_player_id, false) > 0) 
		        || (ev_data.getCounter(4, max_player_id, true) > 1)
		        || ((ev_data.getCounter(4, max_player_id, true) > 0)&&(ev_data.getCounter(3, max_player_id, false) > 0)) )
		    {
		        return 9900;
		    }
		    if(ev_data.getCounter(3, max_player_id, false) > 1) 
		    {
		        return 9800;
		    }
		    if( (ev_data.getCounter(3, max_player_id, false) > 0) 
		        && (ev_data.getCounter(3, max_player_id, true) > 0) )
		    {
		        return 9700;
		    }
		    if(ev_data.getCounter(4, max_player_id, true) > 1) 
		    {
		        return 9600;
		    }
		    if( (ev_data.getCounter(4, max_player_id, true) > 0) 
		        || (ev_data.getCounter(3, max_player_id, false) > 0) )
		    {
		        return 9500;
		    }
		    int maxev = ev_data.getCounter(3, max_player_id, true) * 300;
		    int minev = ev_data.getCounter(3, opp_id, true) * 300;
		    maxev += ev_data.getCounter(2, max_player_id, false) * 200;
		    minev += ev_data.getCounter(2, opp_id, false) * 200;
		    maxev += ev_data.getCounter(2, max_player_id, true) * 50;
		    minev += ev_data.getCounter(2, opp_id, true) * 50;
		    maxev += state.countPosValue(max_player_id);
		    minev += state.countPosValue(opp_id);

		    return (maxev - minev);
	}

}
