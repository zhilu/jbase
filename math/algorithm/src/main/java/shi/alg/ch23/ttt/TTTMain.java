package shi.alg.ch23.ttt;

import shi.alg.ch23.core.Const;
import shi.alg.ch23.core.Evaluator;
import shi.alg.ch23.core.GameControl;
import shi.alg.ch23.core.GameState;
import shi.alg.ch23.core.Searcher;
import shi.alg.ch23.ttt.evaluator.FeEvaluator;
import shi.alg.ch23.ttt.player.ComputerPalyer;
import shi.alg.ch23.ttt.player.HumanPlayer;
import shi.alg.ch23.ttt.searcher.AlphaBetaSearcher;

public class TTTMain {
	public static final  int SEARCH_DEPTH = 6;
	
	public static void main(String[] args) {
	    
	    HumanPlayer hp = new HumanPlayer("张三");
	    hp.setPlayerId(Const.PLAYER_B);
	   
	    ComputerPalyer cp = new   ComputerPalyer("ThinkPad X200");
	    cp.setPlayerId(Const.PLAYER_A);
	    Searcher nabs = new AlphaBetaSearcher();
	    cp.setSearcher(nabs, SEARCH_DEPTH);

	    Evaluator feFunc = new FeEvaluator();
	    GameState init_state = new TTTGameState();
	  
	    init_state.initGameState(Const.PLAYER_A);
	    init_state.SetEvaluator(feFunc);
	
	    GameControl gc = new GameControl();
	    gc.InitGameState(init_state);
	    gc.SetPlayer(cp, cp.getPlayerId());
	    gc.SetPlayer(hp, hp.getPlayerId());
	    gc.run();

	}
}
//	/*
//	int MiniMax(node, depth, isMaxPlayer)
//	{
//	    if(depth == 0)
//	    {
//	        return Evaluate(node);
//	    }
//
//	    int score = isMaxPlayer ? -INFINITY : INFINITY;
//	    for_each(node的子节点child_node)
//	    {
//	        int value = MiniMax(child_node, depth - 1, !isMaxPlayer);
//	        if(isMaxPlayer)
//	            score = max(score, value);
//	        else
//	            score = min(score, value);
//	    }
//	}
//	*/
//	/*
//	int MiniMax_AlphaBeta(node, depth, α, β, isMaxPlayer)
//	{
//	    if(depth == 0)
//	    {
//	        return Evaluate(node);
//	    }
//
//	    if(isMaxPlayer)
//	    {
//	        for_each(node的子节点child_node)
//	        {
//	            int value = MiniMax_AlphaBeta(child_node, depth - 1, α, β, FALSE);
//	            α = max(α, value);
//	            if(β <= α) //β 剪枝
//	                break;
//	        }
//
//	        return α;
//	    }
//	    else
//	    {
//	        for_each(node的子节点child_node)
//	        {
//	            int value = MiniMax_AlphaBeta(child_node, depth - 1, α, β, TRUE);
//	            β = min(β, value);
//	            if(β <= α) //α 剪枝
//	                break;
//	        }
//
//	        return β;
//	    }
//	}
//	*/
//	/*
//	int NegaMax(node, depth, color)
//	{
//	    if(depth == 0)
//	    {
//	        return color * Evaluate(node);
//	    }
//
//	    int score = -INFINITY;
//	    for_each(node的子节点child_node)
//	    {
//	        int value = -NegaMax(child_node, depth - 1, -color);
//	        score = max(score, value);
//	    }
//	}
//	*/
//	/*
//	int NegaMax_AlphaBeta(node, depth, α, β, color)
//	{
//	    if(depth == 0)
//	    {
//	        return color * Evaluate(node);
//	    }
//
//	    int score = -INFINITY;
//	    for_each(node的子节点child_node)
//	    {
//	        int value = -NegaMax_AlphaBeta(child_node, depth - 1, -β, -α, -color);
//	        score = max(score, value);
//	        α = max(α, value);
//	        if(α >= β)
//	            break;
//	    }
//
//	    return score;
//	}
//	}
//}
