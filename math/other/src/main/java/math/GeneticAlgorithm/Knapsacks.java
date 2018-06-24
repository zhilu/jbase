package math.GeneticAlgorithm;

import java.util.Arrays;

//遗传算法解决0-1背包问题  修改
//这种实现方法效果很差
public class Knapsacks {

// 算法参数
	public final static int POP_SIZE = 80;             // 种群的规模，50-180
	public final static int MAX_ITERS = 1000;          // 遗传的最大代数
	public final static double CROSS_RATE = 1;	       // 交叉率，0.6-1
	public final static double MUTA_RATE = 0.1;        // 变异率,0.05-0.1

	//问题参数
	private double capacity = 0;                // 背包的容量--约束条件的右侧
	private double[] coefs = null;              // 物品的价值--目标函数的系数
	private double[]  cons= null;               // 物品的重量--约束条件的系数
	private int len = 0;             //问题的维数
//	private static int[][] pop = {{1,0,0,0,1,1,0,0,1},
//			               {1,0,0,0,1,0,0,0,1},
//			               {0,0,0,0,1,0,1,0,1},
//			               {1,1,0,1,1,0,0,0,0},
//			               {0,0,1,0,1,0,0,1,1},
//			               {0,0,1,1,0,0,0,1,1}};    
	private int[][] pop = null;            // 种群
	double [] fitness = null;
	double [] prob =null;
	double [] cProb = null;
	//问题的解
	private int[]  solution= null;
	private double target = 0;

	public static void main(String[] args) {
		
		int capacity = 22;
		double[] coefs = {2,2,6,5,4,2,6,5,4,2,6,5,4,2,6,5,4,2,6,5,4,2,6,5,4};
		double[] cons = {6,3,5,4,6,3,5,4,6,3,5,4,6,3,5,4,6,3,5,4,6,3,5,4,6};
		
		int gen = 0;
		Knapsacks pack = new Knapsacks();
		pack.setParms(capacity, coefs, cons);
		pack.init();
			
		while(gen<MAX_ITERS){
			pack.prepare();
			pack.evolve();
			pack.saveBest();		
			gen++;
		}
		
		pack.show();
			
	}
	/**
	 * 设置问题的数据
	 * @param capacity 背包容量
	 * @param coefs  价值--目标函数的系数
	 * @param cons   重量--约束条件的系数
	 */
	public void setParms(double capacity,double [] coefs,double [] cons){
		this.capacity = capacity;
		this.coefs = coefs;
		this.cons = cons;	
		if(coefs.length != cons.length){
			System.out.println("the length must be match");
		}
		this.len=cons.length;
	}
	/**
	 * 初始化总群
	 */
	public void init(){
		pop = new int[POP_SIZE][len];
		for (int i = 0; i < POP_SIZE; i++) {
			for (int j = 0; j < len; j++) {
				double d = Math.random() * 10;             // [0,10)之间的double型数值
				pop[i][j] = ((int) Math.round(d)) % 2;     // 1表示选择该物品，0表示不选择该物品	
			}
			if (getValue(pop[i],cons) > capacity) {        // 超出背包容量
				i--;
			}
		}
	}
    /**
     * 计算内积，即解乘以系数或约束得到得到内积
     * @param s 问题的某个解
     * @param d 约束或系数
     * @return 内积
     */
	private double getValue(int [] s,double [] d){
		double tmp = 0.0d;
		for (int i = 0; i < len; i++) {
			tmp += s[i]*d[i];
		}
		return tmp;
	}
	/**
	 * 计算每个个体的适应值
	 */
	public void fitness(){
        double [] tmp = new double[POP_SIZE];
		for (int i = 0; i < POP_SIZE; i++) {
			tmp[i] = getValue(pop[i],coefs);
		}
        fitness = tmp;
	}
	/*
	 * 计算适应值占总体比例，即生存概率
	 */
	public void prob(){
		double sum = 0;
		double[] tmp = new double[POP_SIZE];
		for (int i = 0; i < POP_SIZE; i++) {
			sum += fitness[i];
		}
		for (int j = 0; j < POP_SIZE; j++) {
			tmp[j] = fitness[j] / sum;
		}
        prob = tmp;
	}
	/**
	 * 计算累积概率进行选择
	 */
	public void cProb(){
		int i = 0;
		double[] tmp = new double[POP_SIZE];
		for (i = 0; i < POP_SIZE; i++) {
			tmp[i] = prob[i];
		}
		for (i = 1; i < POP_SIZE; i++) {
			tmp[i] += tmp[i - 1];
		}
        cProb = tmp; 
	}

	/**
	 * 总群进化：选择，交叉，遗传
	 */
	private void evolve() {
		select();
		crossOver();
		mutate();
	}
	/**
	 * 进化准备：计算适应值，适应概率，选择概率
	 */
	private void prepare(){
		fitness();
		prob();
		cProb();
	}
    /**
     * 选择算子，按累积概率计算
     */
	public void select() {
		double randP;
		int[][] nextPop = new int[POP_SIZE][len];
		for (int i = 0; i < POP_SIZE; i++) {
			randP = Math.random();
			if(randP <= cProb[0]){
				nextPop[i] = Arrays.copyOf(pop[0], pop[0].length);
			}
			for (int j = 1; j < POP_SIZE; j++) {
				if (randP > cProb[j-1] && randP <= cProb[j]) {
					nextPop[i] = Arrays.copyOf(pop[j], pop[j].length);
				}
			}
		}
		pop = nextPop;
	}
	/**
	 * 交叉算子：随机生存交叉位置，相等则交叉一位
	 */
	private void crossOver() {
		int i = 0;
		int rand1 = 0;
		int rand2 = 0;
		int posStart = 0;// 交换基因的开始位置
		int posEnd = 0; // 交换基因的结束位置
        int temp = 0;
		int cross_num = (int) (CROSS_RATE * POP_SIZE);// 参与交换的个体数

		for (i = 1; i < cross_num; i += 2) {
			rand1 = (int) Math.round(Math.random()*len) % len;
			rand2 = (int) Math.round(Math.random()*len) % len;
			posStart = rand1 < rand2 ? rand1 : rand2;
			posEnd = rand1 > rand2 ? rand1 : rand2;

			for(int j = posStart; j <= posEnd;j++ ){
				temp = pop[i][j];
				pop[i][j] = pop[i - 1][j];
				pop[i - 1][j] = temp;
			}
			if (getValue(pop[i],cons) > capacity || getValue(pop[i - 1],cons) > capacity) {
				for(int j = posStart; j <= posEnd;j++ ){
					temp = pop[i][j];
					pop[i][j] = pop[i - 1][j];
					pop[i - 1][j] = temp;
				}
			}
		}
	}
	/**
	 * 变异算子：随机变异
	 */
	private void mutate() {
		int i = 0;
		int rowId = 0;
		int colId = 0;
		int mutate_num = (int) (MUTA_RATE * POP_SIZE * len);// 参与变异的基因个数

		for (i = 0; i < mutate_num; i++) {
			rowId = (int) Math.round(Math.random() * 10) % POP_SIZE;
			colId = (int) Math.round(Math.random() * 10) % len;
			pop[rowId][colId] = 1 - pop[rowId][colId];
			if (getValue(pop[rowId],cons) > capacity) {
				pop[rowId][colId] = 1 - pop[rowId][colId];
			}
		}
	}

	private void saveBest() {

		int index = 0;
		double tmp = fitness[0];
		for (int j = 0; j < POP_SIZE; j++) {
			if (fitness[j] > tmp) {
				tmp = fitness[j];
				index = j;
			}
		}
		if (tmp > target) {
			target = tmp;
			int[] t = new int[len];
			t = Arrays.copyOf(pop[index], len);
			solution = t;
		}
	}
	
    public  void show(){
    	for (int i:solution) {
			System.out.print(i+"\t");
		}
    	System.out.println();
    	System.out.println("最大价值："+ getValue(solution,coefs));
    	System.out.println("最大重量："+ getValue(solution,cons));
    }

    /**
     * 调试用print
     * @param p
     */
//    public static void print(int [][] p){
//		for (int i = 0; i < p.length; i++) {
//			for (int j = 0; j < p[i].length; j++) {
//				System.out.print(p[i][j]+ " ");				
//			}
//			System.out.println();
//		}
//	}
}