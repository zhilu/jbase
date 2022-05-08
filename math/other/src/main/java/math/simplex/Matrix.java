package math.simplex;

//工具矩阵，便于理解和处理
public class Matrix {

	private int row = 0;
	private int col = 0;
	private double[] element = null;    //矩阵元素按数组存储
	
	//构造全0矩阵
	public Matrix(int m, int n) {
		row = m;
		col = n;
		element = new double[m * n];
	}
	
	//构造含元素矩阵
	public Matrix(int m, int n, double[] data) {
		row = m;
		col = n;
		element = data;
	}
//  定义六个矩阵访问函数，分别
//		1、按位置获得元素
//		2、按位置设置元素		
//		3、按列号获得列向量
//	    4、按行号获得行向量
//		5、获得矩阵行数
//		6、获得矩阵列数
	public double getElement(int m, int n) {
		return element[n + m * col];
	}

	public void setElement(int m, int n, double d) {
		element[n + m * col] = d;
	}

	public double[] getColVec(int c) {
		double[] vec = new double[row];
		for (int i = 0; i < vec.length; i++) {
			vec[i] = getElement(i,c);
		}
		return vec;
	}

	public double[] getRowVec(int r) {
		double[] vec = new double[col];
		for (int i = 0; i < vec.length; i++) {
			vec[i] = getElement(r,i);
		}
		return vec;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	//���������ʽ
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				sb.append(String.format("%.2f",getElement(i, j))).append("\t");
			}
			sb.append("\n");
		}
		return sb.toString();

	}
}
