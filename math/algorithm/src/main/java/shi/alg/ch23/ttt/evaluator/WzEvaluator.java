package shi.alg.ch23.ttt.evaluator;

import shi.alg.ch23.core.Const;
import shi.alg.ch23.core.Evaluator;
import shi.alg.ch23.core.GameControl;
import shi.alg.ch23.core.GameState;
import shi.alg.ch23.ttt.GameConst;
import shi.alg.ch23.ttt.Triple;

public class WzEvaluator implements Evaluator {

	@Override
	public int evaluate(GameState state, int max_player_id) {
		int min = GameControl.getPeerPlayer(max_player_id);

		Triple ta = new Triple();
		countPlayerChess(state, max_player_id, ta);
		Triple tb = new Triple();
		countPlayerChess(state, min, tb);

		if (ta.getThree() > 0) {
			return GameConst.INFINITY;
		}
		if ((tb.getThree() > 0) || (tb.getTwo() > 0)) {
			return -GameConst.INFINITY;
		}

		return (ta.getThree() - tb.getThree()) + (ta.getTwo() - tb.getTwo());
	}

	private int countPlayerChess(GameState state, int max_player_id, Triple t) {
		int countThree = 0;
		int countTwo = 0;
		int lines = 0;
		for (int i = 0; i < GameConst.LINE_DIRECTION; i++) {
			int sameCount = 0;
			int empty = 0;
			for (int j = 0; j < GameConst.LINE_CELLS; j++) {
				if (state.getGameCell(GameConst.line_idx_tbl[i][j]) == max_player_id) {
					sameCount++;
				}
				if (state.getGameCell(GameConst.line_idx_tbl[i][j]) == Const.PLAYER_NULL) {
					empty++;
				}
			}
			if (sameCount == 3) {
				countThree++;
			}
			if ((sameCount == 2) && (empty == 1)) {
				countTwo++;
			}
			if ((sameCount + empty) == 3) {
				lines++;
			}
		}
		t.setThree(countThree);
		t.setTwo(countTwo);
		return lines;
	}
}
