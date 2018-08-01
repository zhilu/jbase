package shi.alg.ch22;

public class BShape {
    private static int[][]  S_S_A1={
    	{0,1,1,0},
        {1,1,0,0},
        {0,0,0,0},
        {0,0,0,0}
    };
	private static int[][]  S_S_A2={
        {1,0,0,0},
        {1,1,0,0},
        {0,1,0,0},
        {0,0,0,0}
    };
	public static BShape S_S_1=new BShape(0,S_S_A1,3,2);
	public static BShape S_S_2=new BShape(1,S_S_A2,2,3);
	
	private static int[][]  S_Z_A1={
		{1,1,0,0},
        {0,1,1,0},
        {0,0,0,0},
        {0,0,0,0}
    };
	private static int[][]  S_Z_A2={
		{0,1,0,0},
        {1,1,0,0},
        {1,0,0,0},
        {0,0,0,0}
    };
	public static BShape S_Z_1=new BShape(0,S_Z_A1,3,2);
	public static BShape S_Z_2=new BShape(1,S_Z_A2,2,3);
	
	private static int[][]  S_L_A1={
	    {1,0,0,0},
        {1,0,0,0},
        {1,1,0,0},
        {0,0,0,0}
    };
	private static int[][]  S_L_A2={
		{1,1,1,0},
        {1,0,0,0},
        {0,0,0,0},
        {0,0,0,0}
    };
	private static int[][]  S_L_A3={
		{1,1,0,0},
		{0,1,0,0},
		{0,1,0,0},
        {0,0,0,0}
    };
	private static int[][]  S_L_A4={
		{0,0,1,0},
		{1,1,1,0},
		{0,0,0,0},
		{0,0,0,0}
    };
	
	public static BShape S_L_1=new BShape(0,S_L_A1,2,3);
	public static BShape S_L_2=new BShape(1,S_L_A2,3,2);
	public static BShape S_L_3=new BShape(2,S_L_A3,2,3);
	public static BShape S_L_4=new BShape(3,S_L_A4,3,2);
	
	private static int[][]  S_J_A1={
		{0,1,0,0},
        {0,1,0,0},
        {1,1,0,0},
        {0,0,0,0}
	};
	private static int[][]  S_J_A2={
		{1,0,0,0},
        {1,1,1,0},
        {0,0,0,0},
        {0,0,0,0}
	};
	private static int[][]  S_J_A3={
		{1,1,0,0},
        {1,0,0,0},
        {1,0,0,0},
        {0,0,0,0}
    };
	private static int[][]  S_J_A4={
		{1,1,1,0},
        {0,0,1,0},
        {0,0,0,0},
        {0,0,0,0}
	};
		
	public static BShape S_J_1=new BShape(0,S_J_A1,2,3);
	public static BShape S_J_2=new BShape(1,S_J_A2,3,2);
	public static BShape S_J_3=new BShape(2,S_J_A3,2,3);
	public static BShape S_J_4=new BShape(3,S_J_A4,3,2);
	
	
    private static int[][]  S_I_A1={
		{1,1,1,1},
        {0,0,0,0},
        {0,0,0,0},
        {0,0,0,0}
    };
    private static int[][]  S_I_A2={
		{1,0,0,0},
        {1,0,0,0},
        {1,0,0,0},
        {1,0,0,0}
    };
	public static BShape S_I_1=new BShape(0,S_I_A1,4,1);
	public static BShape S_I_2=new BShape(1,S_I_A2,1,4);
	
	private static int[][]  S_O_A1={
		{1,1,0,0},
        {1,1,0,0},
        {0,0,0,0},
        {0,0,0,0}
    };
	public static BShape S_O_1=new BShape(0,S_O_A1,2,2);
	
	private static int[][]  S_T_A1={
		{0,1,0,0},
        {1,1,1,0},
        {0,0,0,0},
        {0,0,0,0}
	};
	private static int[][]  S_T_A2={
		{1,0,0,0},
        {1,1,0,0},
        {1,0,0,0},
        {0,0,0,0}
	};
	private static int[][]  S_T_A3={
	   {1,1,1,0},
       {0,1,0,0},
       {0,0,0,0},
       {0,0,0,0}
    };
    private static int[][]  S_T_A4={
	   {0,1,0,0},
       {1,1,0,0},
       {0,1,0,0},
       {0,0,0,0}
    };
		
	public static BShape S_T_1=new BShape(0,S_T_A1,3,2);
	public static BShape S_T_2=new BShape(1,S_T_A2,2,3);
	public static BShape S_T_3=new BShape(2,S_T_A3,3,2);
	public static BShape S_T_4=new BShape(3,S_T_A4,2,3);
	
	int r_index;
	int shape [][]= new int[RC.SHAPE_BOX][RC.SHAPE_BOX];
	int width;
	int height;
	 
	public BShape(int index,int[][] shape,int w,int h){
		this.r_index=index;
		this.shape=shape;
		this.width=w;
		this.height=h;
	}
}
