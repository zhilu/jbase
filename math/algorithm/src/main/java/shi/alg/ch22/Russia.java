package shi.alg.ch22;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Russia {
	
	public static int eliminate_score[] = { 0, 100, 200, 400, 800, 0, 0, 0, 0 };

	public static void main(String[] args) {
		List<ShapeT> shape_list = new ArrayList<ShapeT>();
		RussiaGame game = new RussiaGame();
		initGame(game);

		generateShapeList(100000, shape_list);
		for (int i = 0; i < shape_list.size(); i++) {
			if (!computerAIPlayer(game, shape_list.get(i))) {
				System.out.println("Failed at: " + (i + 1) + " pieces!");
				break;
			}
		}
		printGame(game, null);

	}

	private static void initGame(RussiaGame game) {
		game.top_row = RC.GAME_ROW;
		game.old_top_row = -1;
		game.score = 0;
		game.lines = 0;
		for (int i = 0; i < RC.BOARD_ROW; i++) {
			game.board[i][0] = RC.BOUNDRY_INDEX;
			game.board[i][RC.BOARD_COL - 1] = RC.BOUNDRY_INDEX;
		}
		for (int j = 1; j < RC.BOARD_COL - 1; j++) {
			game.board[0][j] = RC.BOUNDRY_INDEX;
			game.board[RC.BOARD_ROW - 1][j] = RC.BOUNDRY_INDEX;
		}
	}

	private static void generateShapeList(int count, List<ShapeT> sl) {
		ShapeT last = null;
		Random r = new Random();
		sl.clear();
		int i = 0;
		while (i < count) {
			ShapeT t = ShapeT.values()[r.nextInt(ShapeT.values().length)];
			if (t != last) {
				sl.add(t);
				last = t;
				i++;
			}
		}
	}

	private static void printGame(RussiaGame game, ShapeT next) {
		for (int i = 0; i < RC.BOARD_ROW; i++) {
			for (int j = 0; j < RC.BOARD_COL; j++) {
				System.out.print(Data.shape_char[game.board[i][j]] + " ");
			}

			if ((next != null) && (i <= RC.SHAPE_BOX)) {
				System.out.print("\t");
				if (i == 0) {
					System.out.print("next : ");
				} else {
					RShape rs = Data.g_shapes[next.getIdx() - 1];
					BShape bs = rs.shape_r[RC.EMPTY_INDEX];
					for (int k = 0; k < RC.SHAPE_BOX; k++) {
						System.out.print(Data.shape_char[bs.shape[i - 1][k]] + ' ');
					}

				}
			}
			System.out.println();
		}
		System.out.println("Total Lines : " + game.lines + ", Total Socre: " + game.score);
	}

	private static boolean computerAIPlayer(RussiaGame game, ShapeT shapeT) {
		System.out.println(Data.shape_char[shapeT.getIdx()+1]);
		
		boolean res_find = false;
		EvaluateResult best_r = new EvaluateResult(0, 0, 0, -999999, -999999);
		RShape rs = Data.g_shapes[shapeT.getIdx()];
		// 遍历每个板块的形状，相当于旋转板块
		for (int i = 0; i < rs.r_count; i++) {
			BShape bs = rs.shape_r[i];
			EvaluateResult evr = new EvaluateResult(i, 0, 0, -999999, -999999);
			int rtn = EvaluateShape(game, bs, evr);
			if ((evr.value > best_r.value) || ((evr.value == best_r.value) && (evr.prs > best_r.prs))) {
				res_find = true;
				best_r = evr;
			}
		}
		if (res_find) {
			PutShapeInPlace(game, rs.shape_r[best_r.r_index], best_r.row, best_r.col);
		} else {
			int sss = 0;
		}

		printGame(game,null);
		return res_find;
	}

	private static void PutShapeInPlace(RussiaGame game, BShape bs, int row, int col) {
		   AddShapeOnGame(game, bs, row, col, false);
		    
		    boolean done = false;
		    int max_row = row + bs.height;
		    int total = 0;
		    do{
		        done = false;
		        for(int i = game.top_row; i < max_row; i++)
		        {
		            if(Evaluate.IsFullRowStatus(game, i))
		            {
		                EliminateRow(game, i);
		                game.top_row++;
		                total++;
		                done = true;
		                break;
		            }
		        }
		    }while(done);

		    game.score += eliminate_score[total];
		    game.lines += total;

	}

	private static void EliminateRow(RussiaGame game, int row) {
	    if((row == 0) || (row == game.top_row))
	    {
	        ClearRowStatus(game, row);
	    }
	    else
	    {
	        for(int i = row; i > game.top_row; i--)
	        {
	            CopyRowStatus(game, i - 1, i);
	        }
	        ClearRowStatus(game, game.top_row);
	    }
		
	}

	private static void CopyRowStatus(RussiaGame game, int from, int to) {
	    for(int i = 0; i < RC.GAME_COL; i++){
	        game.board[to + 1][i + 1] = game.board[from + 1][i + 1];
	    }
		
	}

	private static void ClearRowStatus(RussiaGame game, int row) {
		 for(int i = 0; i < RC.GAME_COL; i++){
		        game.board[row + 1][i + 1] = RC.EMPTY_INDEX;
		 }
		
	}

	/* rtn >= 0表示可以放下这个形状，< 0表示无法放下这个形状，可能到顶了 */
	private static int EvaluateShape(RussiaGame game, BShape bs, EvaluateResult result) {
		int start_row = GetTouchStartRow(game, bs);
		if (start_row < 0)
			return -1;

		for (int col = 0; col < (RC.GAME_COL - bs.width + 1); col++) {
			int row = start_row;
			// 是否还能向下？如果能就再下降一行，直到停下
			while (CanShapeMoveDown(game, bs, row, col)) {
				row++;
			}
			AddShapeOnGame(game, bs, row, col, true);
			int values = Evaluate.EvaluateFunction(game, bs, row, col);
			int prs = Evaluate.PrioritySelection(game, bs.r_index, row, col);
			RemoveShapeFromGame(game, bs, row, col);
			if ((values > result.value) || ((values == result.value) && (prs > result.prs))) {
				result.row = row;
				result.col = col;
				result.value = values;
				result.prs = prs;
			}
		}

		return 1;
	}

	private static void RemoveShapeFromGame(RussiaGame game, BShape bs, int row, int col) {
	    for(int i = 0; i < bs.height; i++)
	    {
	        for(int j = 0; j < bs.width; j++)
	        {
	            game.board[row + i + 1][col + j + 1] -= bs.shape[i][j];
	        }
	    }

	    if(game.old_top_row != -1)
	    {
	        //std::cout << "RemoveShapeFromGame change top " << game->top_row << " -> " << game->old_top_row << std::endl;
	        game.top_row = game.old_top_row;
	    }

	}

	private static void AddShapeOnGame(RussiaGame game, BShape bs, int row, int col, boolean temp) {
		for (int i = 0; i < bs.height; i++) {
			for (int j = 0; j < bs.width; j++) {
				game.board[row + i + 1][col + j + 1] += bs.shape[i][j];
			}
		}
		if (temp) {
			game.old_top_row = game.top_row;
		} else {
			game.old_top_row = -1;
		}
		if (game.top_row >= row) {
			game.top_row = row;
		}

	}

	private static boolean CanShapeMoveDown(RussiaGame game, BShape bs, int row, int col) {
		int shape[][] = new int[RC.SHAPE_BOX][RC.SHAPE_BOX];

		if ((row + bs.height) >= RC.GAME_ROW) /* 已经到底部了 */
			return false;
		row++;
		for (int i = 0; i < bs.height; i++) {
			for (int j = 0; j < bs.width; j++) {
				shape[i][j] = game.board[row + i + 1][col + j + 1] + bs.shape[i][j];
				if ((shape[i][j] > 1) && (shape[i][j] != RC.EMPTY_INDEX)) {
					return false;
				}
			}
		}
		return true;
	}

	private static int GetTouchStartRow(RussiaGame game, BShape bs) {
		return game.top_row - bs.height;
	}

}
