package shi.alg.ch23.ttt.evaluator;

import shi.alg.ch23.core.Const;
import shi.alg.ch23.core.Evaluator;
import shi.alg.ch23.core.GameControl;
import shi.alg.ch23.core.GameState;
import shi.alg.ch23.ttt.GameConst;
import shi.alg.ch23.ttt.Triple;

public class FeEvaluator implements Evaluator {

	@Override
	public int evaluate(GameState state, int max_player_id) {
		int min = GameControl.getPeerPlayer(max_player_id);

		Triple ta = countPlayerChess(state, max_player_id);
		Triple tb = countPlayerChess(state, min);

		// win
		if (ta.getThree() > 0) {
			return GameConst.INFINITY;
		}
		// failed
		if (tb.getThree() > 0) {
			return -GameConst.INFINITY;
		}

		// 自己的双连子的数目最大，对方的双连子数据最小
		// 自己的空行数最大，对方的空行数最小
		return (ta.getTwo() - tb.getTwo()) * GameConst.DOUBLE_WEIGHT + (ta.getOne() - tb.getOne());
	}

	/**
	 * 评估函数
	 * f(x)=
	 * 1  	 100    win
	 * 2    -100    lose
	 * 3     0      draw
	 * 4     (自己的双连子-对方的双连子)* 权重+（自己的空行数-对方的空行数）
	 * @param state
	 * @param player_id
	 * @return
	 */
	protected Triple countPlayerChess(GameState state, int player_id) {
		Triple res = new Triple();
		int countOne = 0;
		int countTwo = 0;
		int countThree = 0;

		int i, j;
		for (i = 0; i < GameConst.LINE_DIRECTION; i++) {
			int sameCount = 0;
			int empty = 0;
			for (j = 0; j < GameConst.LINE_CELLS; j++) {
				if (state.getGameCell(GameConst.line_idx_tbl[i][j]) == player_id) {
					sameCount++;
				}
				if (state.getGameCell(GameConst.line_idx_tbl[i][j]) == Const.PLAYER_NULL) {
					empty++;
				}
			}
			if ((sameCount == 1) && (empty == 2)) {
				countOne++;
			}
			if ((sameCount == 2) && (empty == 1)) {
				countTwo++;
			}
			if ((sameCount == 3) && (empty == 0)) {
				countThree++;
			}
		}

		res.setOne(countOne);
		res.setTwo(countTwo);
		res.setThree(countThree);
		return res;
	}

}
