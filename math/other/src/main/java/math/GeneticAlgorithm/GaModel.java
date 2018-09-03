package math.GeneticAlgorithm;

import java.util.Random;

public class GaModel {

	private int[] users = {16, 9, 3, 2, 21, 12, 9, 4, 4, 60, 2, 6, 1, 184, 1, 1, 45, 7, 2, 39, 1, 16, 1, 1, 1, 1, 50, 1, 65, 1, 1, 1, 55, 7, 2, 184, 6, 43, 45, 5, 2, 8, 1, 17, 92, 30, 56, 7, 12};// 物品价值
	private double[] loads = {16755, 5399, 5105, 0, 10068, 9710, 20119, 20280, 2080, 7910, 1435, 6335, 85400, 98020.9, 48000, 0, 75008, 71484, 13700, 64397.6, 55900, 49116, 0, 55900, 55900, 19500, 72546, 0, 71278, 48000,13700, 19500, 5292.6, 10377, 3, 434, 29690, 7873, 2951.6, 329, 6522, 40, 5400, 11393, 0, 8159.6, 11781, 12921, 947};// 物品体积
	private int loadGap = 200000;// 背包容积
    private int UPPER_BOUND = 999;
    
	private int len; // 染色体长度
	private int size;// 种群规模
	private int MAX_GEN; // 运行代数

	private int bestT;// 最佳出现代数
	private int bestLength; // 最佳编码价值
	private int[] bestTour; // 最佳编码

	// 初始种群，父代种群，行数表示种群规模，一行代表一个个体，即染色体，列表示染色体基因片段
	private int[][] oldPopulation;
	private int[][] newPopulation;// 新的种群，子代种群
	private int[] fitness;// 种群适应度，表示种群中各个个体的适应度

	private float[] Pi;// 种群中各个个体的累计概率
	private float Pc;// 交叉概率
	private float Pm;// 变异概率
	private int t;// 当前代数
	private Random random;

	// 种群规模，染色体长度,最大代数，交叉率，变异率
	public GaModel(int s,  int g, float c, float m) {
		size = s;
		len = loads.length;
		MAX_GEN = g;
		Pc = c;
		Pm = m;
	}

	private void init(){
		bestLength = 0;
		bestTour = new int[len];
		bestT = 0;
		t = 0;

		newPopulation = new int[size][len];
		oldPopulation = new int[size][len];
		fitness = new int[size];
		Pi = new float[size];

		random = new Random(System.currentTimeMillis());
	}

	// 初始化种群
	void initGroup() {
		int k, i;
		for (k = 0; k < size; k++)// 种群数
		{
			if(k < 10){
				for (i = 0; i < len; i++) {
					oldPopulation[k][i] = 1;
				}
			}else {
				for (i = 0; i < len; i++) {
					oldPopulation[k][i] = random.nextInt(65535) % 2;
				}
			}

		}
	}

	public int evaluate(int[] chromosome) {

		int vv = 0;
		double bb = 0;
		for (int i = 0; i < len; i++) {
			if (chromosome[i] == 1) {
				vv += users[i];
				bb += loads[i];
			}
		}
		if (bb < loadGap) {
			// 超出背包体积
			return 0;
		} else {
			return UPPER_BOUND - vv;
		}
	}

	// 计算种群中各个个体的累积概率，前提是已经计算出各个个体的适应度fitness[max]，作为赌轮选择策略一部分，Pi[max]
	void countRate() {
		int k;
		double sumFitness = 0;// 适应度总和

		int[] tempf = new int[size];

		for (k = 0; k < size; k++) {
			tempf[k] = fitness[k];
			sumFitness += tempf[k];
		}

		Pi[0] = (float) (tempf[0] / sumFitness);
		for (k = 1; k < size; k++) {
			Pi[k] = (float) (tempf[k] / sumFitness + Pi[k - 1]);
		}
	}

	// 挑选某代种群中适应度最高的个体，直接复制到子代中
	// 前提是已经计算出各个个体的适应度Fitness[max]
	public void selectBestGh() {
		int k, i, maxid;
		int maxevaluation;

		maxid = 0;
		maxevaluation = fitness[0];
		for (k = 1; k < size; k++) {
			if (maxevaluation < fitness[k]) {
				maxevaluation = fitness[k];
				maxid = k;
			}
		}

		if (bestLength < maxevaluation) {
			bestLength = maxevaluation;
			bestT = t;// 最好的染色体出现的代数;
			for (i = 0; i < len; i++) {
				bestTour[i] = oldPopulation[maxid][i];
			}
		}

		// 复制染色体，k表示新染色体在种群中的位置，kk表示旧的染色体在种群中的位置
		copyGh(0, maxid);// 将当代种群中适应度最高的染色体k复制到新种群中，排在第一位0
	}

	// 复制染色体，k表示新染色体在种群中的位置，kk表示旧的染色体在种群中的位置
	public void copyGh(int k, int kk) {
		int i;
		for (i = 0; i < len; i++) {
			newPopulation[k][i] = oldPopulation[kk][i];
		}
	}

	// 赌轮选择策略挑选
	public void select() {
		int k, i, selectId;
		float ran1;
		for (k = 1; k < size; k++) {
			ran1 = (float) (random.nextInt(65535) % 1000 / 1000.0);
			// System.out.println("概率"+ran1);
			// 产生方式
			for (i = 0; i < size; i++) {
				if (ran1 <= Pi[i]) {
					break;
				}
			}
			selectId = i;
			copyGh(k, selectId);
		}
	}

	public void evolution() {
		int k;
		// 挑选某代种群中适应度最高的个体
		selectBestGh();
		// 赌轮选择策略挑选scale-1个下一代个体
		select();
		float r;

		// 交叉方法
		for (k = 0; k < size; k = k + 2) {
			r = random.nextFloat();// /产生概率
			// System.out.println("交叉率..." + r);
			if (r < Pc) {
				// System.out.println(k + "与" + k + 1 + "进行交叉...");
				OXCross(k, k + 1);// 进行交叉
			} else {
				r = random.nextFloat();// /产生概率
				// System.out.println("变异率1..." + r);
				// 变异
				if (r < Pm) {
					// System.out.println(k + "变异...");
					OnCVariation(k);
				}
				r = random.nextFloat();// /产生概率
				// System.out.println("变异率2..." + r);
				// 变异
				if (r < Pm) {
					// System.out.println(k + 1 + "变异...");
					OnCVariation(k + 1);
				}
			}

		}

	}
	

	// 两点交叉算子
	void OXCross(int k1, int k2) {
		int i, j, flag;
		int ran1, ran2, temp = 0;

		ran1 = random.nextInt(65535) % len;
		ran2 = random.nextInt(65535) % len;

		while (ran1 == ran2) {
			ran2 = random.nextInt(65535) % len;
		}
		if (ran1 > ran2)// 确保ran1<ran2
		{
			temp = ran1;
			ran1 = ran2;
			ran2 = temp;
		}
		flag = ran2 - ran1 + 1;// 个数
		for (i = 0, j = ran1; i < flag; i++, j++) {
			temp = newPopulation[k1][j];
			newPopulation[k1][j] = newPopulation[k2][j];
			newPopulation[k2][j] = temp;
		}

	}

	// 多次对换变异算子
	public void OnCVariation(int k) {
		int ran1, ran2, temp;
		int count;// 对换次数
		count = random.nextInt(65535) % len;

		for (int i = 0; i < count; i++) {

			ran1 = random.nextInt(65535) % len;
			ran2 = random.nextInt(65535) % len;
			while (ran1 == ran2) {
				ran2 = random.nextInt(65535) % len;
			}
			temp = newPopulation[k][ran1];
			newPopulation[k][ran1] = newPopulation[k][ran2];
			newPopulation[k][ran2] = temp;
		}
	}

	public void solve() {
		int i;
		int k;

		// 初始化种群
		initGroup();
		// 计算初始化种群适应度，Fitness[max]
		for (k = 0; k < size; k++) {
			fitness[k] = evaluate(oldPopulation[k]);
			// System.out.println(fitness[k]);
		}

		// 计算初始化种群中各个个体的累积概率，Pi[max]
		countRate();
		System.out.println("初始种群...");
		for (k = 0; k < size; k++) {
			for (i = 0; i < len; i++) {
				System.out.print(oldPopulation[k][i] + ",");
			}
			System.out.println();
			System.out.println("----" + fitness[k] + " " + Pi[k]);
		}
		//evolution();

		for (t = 0; t < MAX_GEN; t++) {
			evolution();
			// 将新种群newGroup复制到旧种群oldGroup中，准备下一代进化
			for (k = 0; k < size; k++) {
				for (i = 0; i < len; i++) {
					oldPopulation[k][i] = newPopulation[k][i];
				}
			}
			// 计算种群适应度
			for (k = 0; k < size; k++) {
				fitness[k] = evaluate(oldPopulation[k]);
			}
			// 计算种群中各个个体的累积概率
			countRate();
		}

		System.out.println("最后种群...");
		for (k = 0; k < size; k++) {
			for (i = 0; i < len; i++) {
				System.out.print(oldPopulation[k][i] + ",");
			}
			System.out.println();
			System.out.println("---" + fitness[k] + " " + Pi[k]);
		}

		System.out.println("最佳编码出现代数：");
		System.out.println(bestT);
		System.out.println("最佳编码价值");
		System.out.println(UPPER_BOUND - bestLength);
		System.out.println("最佳数");
		double totalP = 0.0d;
		for (int j = 0; j < bestTour.length; j++) {
			totalP += bestTour[j]*loads[j];
		}
		System.out.println(totalP);
		System.out.println("最佳编码：");
		for (i = 0; i < len; i++) {
			System.out.print(bestTour[i] + ",");
		}

	}

	public static void main(String[] args) {
		GaModel ga = new GaModel(80, 1000, 0.8f, 0.1f);
		ga.init();
		ga.solve();
	}
}