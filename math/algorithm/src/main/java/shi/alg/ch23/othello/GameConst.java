package shi.alg.ch23.othello;

public class GameConst {
	public static int GAME_ROW = 8;
	public static int GAME_COL = 8;
	public static int GAME_CELLS = 64;
	
	public static int BOARD_ROW = 10;
	public static int BOARD_COL = 9;
	public static int BOARD_CELLS = 91;
	
	public static int INFINITY = 10000;
	public static int DRAW = 0;
	
	public static int PLAYER_TYPE = 3;
	//位置权值表
	public static  int[] posValue = 
	{
	    0,0,0,0,0,0,0,0,0,
	    0,100,  -5,  10,   5,   5,  10,  -5, 100,
	    0,-5, -45,   1,   1,   1,   1, -45,  -5,
	    0,10,   1,   3,   2,   2,   3,   1,  10,
	    0,5,   1,   2,   1,   1,   2,   1,   5,
	    0,5,   1,   2,   1,   1,   2,   1,   5,
	    0,10,   1,   3,   2,   2,   3,   1,  10,
	    0,-5, -45,   1,   1,   1,   1, -45,  -5,
	    0,100,  -5,  10,   5,   5,  10,  -5, 100,
	    0,0,0,0,0,0,0,0,0
	};
	
	public static short dir_mask[] = 
		{
		0,0,0,0,0,0,0,0,0,
		0,81,81,87,87,87,87,22,22,
		0,81,81,87,87,87,87,22,22,
		0,121,121,255,255,255,255,182,182,
		0,121,121,255,255,255,255,182,182,
		0,121,121,255,255,255,255,182,182,
		0,121,121,255,255,255,255,182,182,
		0,41,41,171,171,171,171,162,162,
		0,41,41,171,171,171,171,162,162,
		0,0,0,0,0,0,0,0,0,0
		};
	
	public static int dir_inc[] = {1, -1, 8, -8, 9, -9, 10, -10, 0};

	/* Inside this fast endgame solver, the board is represented by
	* a 1D array of 91 uchars board[0..90]:
	   ddddddddd
	   dxxxxxxxx 10
	   dxxxxxxxx 19
	   dxxxxxxxx 28
	   dxxxxxxxx 37
	   dxxxxxxxx 46
	   dxxxxxxxx 55
	   dxxxxxxxx 64    where A1 is board[10], H8 is board[80].
	   dxxxxxxxx 73    square(a,b) = board[10+a+b*9] for 0<= a,b <=7.
	   dddddddddd   
	where d (dummy) squares contain DUMMY, x are EMPTY, BLACK, or WHITE: */

	public static int cell2board[] = 
	{
	10,11,12,13,14,15,16,17,
	19,20,21,22,23,24,25,26,
	28,29,30,31,32,33,34,35,
	37,38,39,40,41,42,43,44,
	46,47,48,49,50,51,52,53,
	55,56,57,58,59,60,61,62,
	64,65,66,67,68,69,70,71,
	73,74,75,76,77,78,79,80
	};
}
