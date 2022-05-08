package shi.alg.ch23.othello;

import shi.alg.ch23.core.Const;
import shi.alg.ch23.core.Evaluator;
import shi.alg.ch23.core.GameControl;
import shi.alg.ch23.core.GameState;
import shi.alg.ch23.core.Searcher;
import shi.alg.ch23.othello.evaluator.WzEvaluator;
import shi.alg.ch23.othello.player.ComputerPlayer;
import shi.alg.ch23.othello.player.HumanPlayer;
import shi.alg.ch23.othello.searcher.AlphaBetaSearcher;

public class Othello {
	public static int SEARCH_DEPTH = 6;

	public static void main(String[] args) {
		Searcher s = new AlphaBetaSearcher();

		HumanPlayer hp = new HumanPlayer("张三");
		hp.setPlayerId(Const.PLAYER_B);
		ComputerPlayer cp = new ComputerPlayer("KA47");
		cp.setPlayerId(Const.PLAYER_A);
		cp.setSearcher(s, SEARCH_DEPTH);

		Evaluator ev = new WzEvaluator();
		GameState init_state = new OthelloGameState();
		init_state.initGameState(Const.PLAYER_A);
		init_state.SetEvaluator(ev);

		GameControl gc = new GameControl();
		gc.InitGameState(init_state);
		gc.SetPlayer(cp, cp.getPlayerId());
		gc.SetPlayer(hp, hp.getPlayerId());

		gc.run();

	}

	public static int BOARD_CELL(int row, int col) {
		int x = (col - 1) + 10 + (row - 1) * 9;
		return x;
	}

	public static int getPeerPlayer(int player_id) {
		return (player_id == Const.PLAYER_A) ? Const.PLAYER_B : Const.PLAYER_A;
	}

}
