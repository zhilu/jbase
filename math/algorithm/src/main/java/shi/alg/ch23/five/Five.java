package shi.alg.ch23.five;

import shi.alg.ch23.core.Const;
import shi.alg.ch23.core.Evaluator;
import shi.alg.ch23.core.GameControl;
import shi.alg.ch23.core.GameState;
import shi.alg.ch23.five.evaluator.WzEvaluator;
import shi.alg.ch23.five.player.ComputerPlayer;
import shi.alg.ch23.five.player.HumanPlayer;
import shi.alg.ch23.five.searcher.NegamaxAlphaBetaSearcher;

public class Five {
	public static  int SEARCH_DEPTH = 3;
	public static void main(String[] args) {
	    NegamaxAlphaBetaSearcher nabs = new NegamaxAlphaBetaSearcher();

	    HumanPlayer hp = new HumanPlayer("张三");
	    ComputerPlayer cp = new ComputerPlayer("ThinkPad X200");
	    cp.SetSearcher(nabs, SEARCH_DEPTH); 

	    Evaluator wzEv = new WzEvaluator();
	    GameState init_state = new FiveGameState();
	    init_state.initGameState(Const.PLAYER_A);
	    init_state.SetEvaluator(wzEv);

	    GameControl gc = new GameControl();
	    gc.SetPlayer(cp, Const.PLAYER_A);
	    gc.SetPlayer(hp, Const.PLAYER_B);
	    //gc.SetPlayer(&computer1, PLAYER_B);
	    gc.InitGameState(init_state);
	    gc.run();
	}
	
	public static int BOARD_CELL(int row, int col) {
		int x = (col - 1) + 11 + (row - 1) * 10;
		return x;
	}
	
	public static int getPeerPlayer(int player_id) {
		return (player_id == Const.PLAYER_A) ? Const.PLAYER_B : Const.PLAYER_A;
	}
}
