package shi.alg.ch23.ttt.player;

import shi.alg.ch23.core.GameState;
import shi.alg.ch23.core.Player;
import shi.alg.ch23.core.Searcher;

public class ComputerPalyer extends Player {

	private Searcher searcher;
	private int depth;

	public ComputerPalyer(String name) {
		searcher = null;
		depth = 3;
		setPlayerName(name);
	}

	@Override
	public int getNextPosition() {
		assert (state != null);
		assert (searcher != null);

		int np = searcher.searchBestPlay(state, depth);
		int row = np / GameState.BOARD_COL;
		int col = np % GameState.BOARD_COL;

		System.out.println("Computer play at [" + (row + 1) + " , " + (col + 1) + "]");

		return np;
	}

	public void setSearcher(Searcher searcher,int depth) {
	    this.searcher = searcher;
	    this.depth = depth;
	}

	@Override
	public String toString() {
		return "ComputerPalyer [playerId=" + playerId + ", playerName=" + playerName + "]";
	}


}
