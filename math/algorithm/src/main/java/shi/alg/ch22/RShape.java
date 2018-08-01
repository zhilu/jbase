package shi.alg.ch22;

public class RShape {

	private static BShape[] s_s = { BShape.S_S_1, BShape.S_S_2 };
	public static final RShape S_S = new RShape(s_s, 2);

	private static BShape[] s_z = { BShape.S_Z_1, BShape.S_Z_2 };
	public static final RShape S_Z = new RShape(s_z, 2);

	private static BShape[] s_l = { BShape.S_L_1, BShape.S_L_2, BShape.S_L_3, BShape.S_L_4 };
	public static final RShape S_L = new RShape(s_l, 4);

	private static BShape[] s_j = { BShape.S_J_1, BShape.S_J_2, BShape.S_J_3, BShape.S_J_4 };
	public static final RShape S_J = new RShape(s_j, 4);

	private static BShape[] s_o = { BShape.S_O_1 };
	public static final RShape S_O = new RShape(s_o, 1);
	
	private static BShape[] s_i = { BShape.S_I_1, BShape.S_I_2 };
	public static final RShape S_I = new RShape(s_i, 2);

	private static BShape[] s_t = { BShape.S_T_1, BShape.S_T_2, BShape.S_T_3, BShape.S_T_4 };
	public static final RShape S_T = new RShape(s_t, 4);

	BShape[] shape_r = null;
	int r_count;

	RShape(BShape[] shapes, int count) {
		this.shape_r=shapes;
		this.r_count=count;
	}

	BShape[] getBShapes() {
		return shape_r;
	}

	int getCount() {
		return r_count;
	}

}
