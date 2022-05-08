package shi.alg.ch23.ttt.player;

import java.util.Scanner;

import shi.alg.ch23.core.GameState;
import shi.alg.ch23.core.Player;


public class HumanPlayer extends Player {

	public HumanPlayer(String name) {
		setPlayerName(name);
	}

	@Override
	public int getNextPosition() {
		assert (state != null);

		int np = 0;
		while (true) {
			int row, col;
			System.out.print("Please select your position (row = 1-3,col = 1-3): ");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			String read = scan.next();
			String[] strs = read.split(",");
			row = Integer.parseInt(strs[0]);
			col = Integer.parseInt(strs[1]);
			np = (row - 1) * GameState.BOARD_COL + (col - 1);
			if (((np >= 0) && (np < 9)) && state.isEmptyCell(np)) {
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
