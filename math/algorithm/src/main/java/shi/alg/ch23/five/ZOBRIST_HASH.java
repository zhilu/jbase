package shi.alg.ch23.five;

import java.util.Random;

public class ZOBRIST_HASH {

	static long key[][] = new long[GameConst.GAME_CELLS][3];
	static {
		Random r = new Random();
		for (int i = 0; i < GameConst.GAME_CELLS; i++) {
			for (int j = 0; j < 3; j++) {
				key[i][j] = r.nextInt() | r.nextLong();
			}
		}
	}
}
