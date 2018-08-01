package shi.alg.ch23.othello.player;

import shi.alg.ch23.core.GameState;
import shi.alg.ch23.core.Player;
import shi.alg.ch23.core.Searcher;

public class ComputerPlayer extends Player {

	private Searcher searcher;
	private int depth;

	public ComputerPlayer(String name) {
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
		System.out.println("Computer play at [" + row + " , " + col + "]");
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
