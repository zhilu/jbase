package shi.alg.ch23.ttt;

public class GameConst {
	public static int INFINITY = 100;
	public static int WIN_LEVEL = 80;
	public static int DRAW = 0;
	public static int DOUBLE_WEIGHT = 10;
	
	public static int LINE_DIRECTION = 8;
	public static int LINE_CELLS = 3;
	
	public static int line_idx_tbl[][] = 
		{
		    {0, 1, 2}, //第一行
		    {3, 4, 5}, //第二行
		    {6, 7, 8}, //第三行
		    {0, 3, 6}, //第一列
		    {1, 4, 7}, //第二列
		    {2, 5, 8}, //第三列
		    {0, 4, 8}, //正交叉线
		    {2, 4, 6}, //反交叉线
		};
}
