package math.simplex;

public class Model {
	private Matrix A = null;               //约束矩阵
	private double[] b = null;             //约束边界
	private double[] f = null;             //检验数

	private double value = 0;              //最优值
	private double[] solution = null;      //最优解
	
	public Model(Matrix a, double[] b, double[] f) {
		A = a;
		this.b = b;
		this.f = f;
	}
		
	
	public Matrix getA() {
		return A;
	}
	public double[] getB() {
		return b;
	}

	public double[] getF() {
		return f;
	}
	
	
	public void setValue(double value) {
		this.value = value;
	}


	public void setSolution(double[] solution) {
		this.solution = solution;
	}


	public double getValue() {
		return value;
	}
	public double[] getSolution() {
		return solution;
	}
	
	
	
}
