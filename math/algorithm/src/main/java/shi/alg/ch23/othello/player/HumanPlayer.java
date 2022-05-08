package shi.alg.ch23.othello.player;

import java.util.Scanner;

import shi.alg.ch23.core.Player;
import shi.alg.ch23.othello.GameConst;
import shi.alg.ch23.othello.Othello;
import shi.alg.ch23.othello.OthelloGameState;


public class HumanPlayer extends Player {

	public HumanPlayer(String name) {
		setPlayerName(name);
	}

	@Override
	public int getNextPosition() {
		assert (state != null);

		OthelloGameState ostate = (OthelloGameState) state;
		if(!ostate.testMoves(getPlayerId()))
	    {
	        System.out.println("you have no valid position, skip this step!");
	        return -1;
	    }

		int np = 0;
		while (true) {
			int row, col;
			System.out.print("Please select your position (row = 1-8,col = 1-8): ");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			String read = scan.next();
			String[] strs = read.split(",");
			row = Integer.parseInt(strs[0]);
			col = Integer.parseInt(strs[1]);
			np = Othello.BOARD_CELL(row, col);
			if (((np >= 0) && (np < GameConst.BOARD_CELLS)) && ostate.isValidPosition(np, getPlayerId())) {
				break;
			} else {
				System.out.println("Invalid position on (" + row + " , " + col + ")");
				continue;
			}
		}
		return np;
	    
	}

	@Override
	public String toString() {
		return "HumanPlayer [playerId=" + playerId + ", playerName=" + playerName + "]";
	}
	
	
}
