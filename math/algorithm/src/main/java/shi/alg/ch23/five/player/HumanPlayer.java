package shi.alg.ch23.five.player;

import java.util.Scanner;

import shi.alg.ch23.core.Player;
import shi.alg.ch23.five.Five;
import shi.alg.ch23.five.GameConst;

public class HumanPlayer extends Player {

	public HumanPlayer(String string) {
		setPlayerName(string);
	}

	@Override
	public int getNextPosition() {
		assert (state != null);
		int np = 0;
		while (true) {
			int row, col;
			char cc;
			System.out.println("Please select your position (row = 1-15,col = A-O): ");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			String read = scan.next();
			String[] strs = read.split(",");
			row = Integer.parseInt(strs[0]);
			cc = strs[1].charAt(0);
			col = cc - 'A' + 1;
			np = Five.BOARD_CELL(row, col);
			if (((np >= 0) && (np < GameConst.BOARD_CELLS)) && state.isEmptyCell(np)) {
				break;
			} else {
				System.out.println("Invalid position on (" + row + " , " + cc + ")");
			}
		}

		return np;
	}

}
