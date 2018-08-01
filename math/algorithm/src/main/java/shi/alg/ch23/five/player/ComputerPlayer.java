package shi.alg.ch23.five.player;

import shi.alg.ch23.core.Player;
import shi.alg.ch23.core.Searcher;
import shi.alg.ch23.five.GameConst;

public class ComputerPlayer extends Player {

	private Searcher searcher;
	private int depth;

	public ComputerPlayer(String string) {
		depth = 3;
		setPlayerName(string);
	}

	@Override
	public int getNextPosition() {
		assert (state != null);
		assert (searcher != null);

		int np = searcher.searchBestPlay(state, depth);
		int row = (np - GameConst.BOARD_START) / GameConst.BOARD_COL;
		int col = (np - GameConst.BOARD_START) % GameConst.BOARD_COL;

		char cc = (char) ('A' + col);
		System.out.println("Computer play at [" + (row + 1) + " , " + cc + "]");

		return np;
	}

	public void SetSearcher(Searcher searcher, int depth) {
		this.searcher = searcher;
		this.depth = depth;
	}

}
