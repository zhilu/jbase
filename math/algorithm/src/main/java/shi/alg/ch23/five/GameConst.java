package shi.alg.ch23.five;

public class GameConst {
	public static int GAME_ROW = 9;
	public static int GAME_COL = 9;
	public static int GAME_CELLS = 81;


	public static int BOARD_START = 11;
	public static int BOARD_COL = 10;
	public static int BOARD_CELLS = 111;

	public static int INFINITY = 10000;
	public static int DRAW = 0;
	
	//位置权值表
	public static int [] posValue = 
	{
	    0,0,0,0,0,0,0,0,0,0,
	    0,0,0,0,0,0,0,0,0,0,
	    0,0,1,1,1,1,1,1,1,0,
	    0,0,1,2,2,2,2,2,1,0,
	    0,0,1,2,3,3,3,2,1,0,
	    0,0,1,2,3,4,3,2,1,0,
	    0,0,1,2,3,3,3,2,1,0,
	    0,0,1,2,2,2,2,2,1,0,
	    0,0,1,1,1,1,1,1,1,0,
	    0,0,0,0,0,0,0,0,0,0,
	    0,0,0,0,0,0,0,0,0,0,0

	};
	
	public static int DIR_COUNT = 4;
	public static int DIR_H = 0; // --方向
	public static int DIR_B = 1; // / 方向
	public static int DIR_S = 2; // \ 方向
	public static int DIR_V = 3; // | 方向
	
	public static int [] dir_inc = {1, 9, 11, 10, -1, -9, -11,-10, 0};
	
	public static int cell2board[] = 
		{
		11,12,13,14,15,16,17,18,19,
		21,22,23,24,25,26,27,28,29,
		31,32,33,34,35,36,37,38,39,
		41,42,43,44,45,46,47,48,49,
		51,52,53,54,55,56,57,58,59,
		61,62,63,64,65,66,67,68,69,
		71,72,73,74,75,76,77,78,79,
		81,82,83,84,85,86,87,88,89,
		91,92,93,94,95,96,97,98,99
		};
}
