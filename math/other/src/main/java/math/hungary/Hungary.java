package math.hungary;

public class Hungary {

	int n; // 维数
	static int[][] p = null; // 归约矩阵
	static int[][] q = null; // 0:未被画线 1:画了1次 2: 画了2次(交点)
	int[] row = null, col = null; // 行列0元素个数
	static int[][] r = null; // 0:非0元素 1:非独立0元素 2:独立0元素
	int[] x = null, y = null; // 画线时是否被打勾，1是0不是

	public Hungary(DataModel dataModel) {
		p = dataModel.data; // 归约矩阵
		n = dataModel.data.length;
		q = new int[n][n]; // 0:未被画线 1:画了1次 2: 画了2次(交点)
		r = new int[n][n]; // 0:非0元素 1:非独立0元素 2:独立0元素
		row = new int[n];
		col = new int[n]; // 行列0元素个数
		x = new int[n];
		y = new int[n]; // 画线时是否被打勾，1是0不是
		
	}

	public void solve() {
		while (find() < n) {
			drawLine();

			// 最小的未被划线的数
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < n; ++i)
				for (int j = 0; j < n; ++j)
					if (q[i][j] == 0 && min > p[i][j])
						min = p[i][j];

			// 更新未被划到的和交点
			for (int i = 0; i < n; ++i)
				for (int j = 0; j < n; ++j)
					if (q[i][j] == 0)
						p[i][j] -= min;
					else if (q[i][j] == 2)
						p[i][j] += min;
		}
	}

	public void countZero() {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (p[i][j] == 0) {
					row[i]++;
					col[j]++;
				}
			}
		}
	}

	// 画最少的线覆盖所有0元素
	int drawLine() {

		for (int i = 0; i < n; ++i) {
			x[i] = 1; // 行全部打勾
			y[i] = 0; // 行全部不打勾
		}

		// row 对所有不含独立0元素的行打勾！
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (r[i][j] == 2) {
					x[i] = 0;
					break;
				}
			}
		}

		boolean is = true;
		while (is) // 循环直到没有勾可以打
		{
			is = false;
			// col 对打勾的行中含0元素的未打勾的列打勾
			for (int i = 0; i < n; ++i) {
				if (x[i] == 1) {
					for (int j = 0; j < n; ++j) {
						if (p[i][j] == 0 && y[j] == 0) {
							y[j] = 1;
							is = true;
						}
					}
				}
			}

			// row 对打勾的列中含独立0元素的未打勾的行打勾
			for (int j = 0; j < n; ++j) {
				if (y[j] == 1) {
					for (int i = 0; i < n; ++i) {
						if (p[i][j] == 0 && x[i] == 0 && r[i][j] == 2) {
							x[i] = 1;
							is = true;
						}
					}
				}
			}
		}

		// 没有打勾的行和有打勾的列画线，这就是覆盖所有0元素的最少直线数
		int line = 0;
		for (int i = 0; i < n; ++i) {
			if (x[i] == 0) {
				for (int j = 0; j < n; ++j) {
					q[i][j]++;
				}
				line++;
			}

			if (y[i] == 1) {
				for (int j = 0; j < n; ++j) {
					q[j][i]++;
				}
				line++;
			}
		}

		return line;
	}

	// 找独立0元素个数
	/*
	 * 1.找含0最少的那一行/列 2.划掉，更新该行/列0元素所在位置的row[],col[]
	 * 3.直到所有0被划线，这里定义为row[]col[]全为Integer.MAX_VALUE,表示该行/列无0元素
	 */
	int find() {
		countZero();
		int zero = 0; // 独立0元素的个数
		while (true) {
			// row[i] = Integer.MAX_VALUE表示该行无0元素，防止与*min_element()冲突
			for (int i = 0; i < n; ++i) {
				if (row[i] == 0)
					row[i] = Integer.MAX_VALUE;
				if (col[i] == 0)
					col[i] = Integer.MAX_VALUE;
			}

			boolean stop = true;

			if (minElement(row) <= minElement(col)) // 行最少0元素
			{
				// 找含0最少的那一行
				int tmp = Integer.MAX_VALUE, index = -1;
				for (int i = 0; i < n; ++i) {
					if (tmp > row[i]) {
						tmp = row[i];
						index = i;
					}

				}

				/* 找该行任意一个没被划掉的0元素(独立0元素)，找到一个就行 */
				int index2 = -1; // 该行独立0元素的列值
				for (int i = 0; i < n; ++i)
					if (p[index][i] == 0 && col[i] != Integer.MAX_VALUE) {
						index2 = i;
						stop = false; // 找到独立0元素则继续循环
						zero++; // 独立0元素的个数
						break;
					}

				// 找不到独立0元素了
				if (stop)
					break;

				// 标记
				row[index] = col[index2] = Integer.MAX_VALUE;
				r[index][index2] = 1; // 独立0元素，等等会++.

				// 更新其所在行列的col,row
				for (int i = 0; i < n; ++i)
					if (p[index][i] == 0 && col[i] != Integer.MAX_VALUE) // 若该行还有0且没被划掉才更新
						col[i]--;
				for (int i = 0; i < n; ++i)
					if (p[i][index2] == 0 && row[i] != Integer.MAX_VALUE)
						row[i]--;
			} else // 列最少0元素
			{
				int tmp = Integer.MAX_VALUE, index = -1;
				for (int i = 0; i < n; ++i) {
					if (tmp > col[i]) {
						tmp = col[i];
						index = i;
					}

				}

				int index2 = -1;
				for (int i = 0; i < n; ++i)
					if (p[i][index] == 0 && row[i] != Integer.MAX_VALUE) {
						index2 = i;
						stop = false;
						zero++;
						break;
					}

				if (stop)
					break;

				row[index2] = col[index] = Integer.MAX_VALUE;
				r[index2][index] = 1;

				for (int i = 0; i < n; ++i)
					if (p[index2][i] == 0 && col[i] != Integer.MAX_VALUE)
						col[i]--;
				for (int i = 0; i < n; ++i)
					if (p[i][index] == 0 && row[i] != Integer.MAX_VALUE)
						row[i]--;
			}
		}
		// r[i][j] 0:非0元素 1:非独立0元素 2:独立0元素
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j)
				if (p[i][j] == 0)
					r[i][j]++;

		return zero;
	}

	public static void main(String[] args) {

		int[][] s = { { 12, 7, 9, 7, 9 }, { 8, 9, 6, 6, 6 },
				{ 7, 17, 12, 14, 9 }, { 15, 14, 6, 6, 10 }, { 4, 10, 7, 10, 9 } };
		DataModel dataModel = new DataModel(s);
		Hungary hungary = new Hungary(dataModel.preProcess());
		hungary.solve();
		//
		// //求和
		// int ans = 0;
		// for (int i = 0; i < n; ++i)
		// for (int j = 0; j < n; ++j)
		// if (r[i][j] == 2)
		// ans += s[i][j];
		//
		// System.out.println(ans);
	}

	public static int minElement(int[] v) {
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < v.length; i++) {
			if (v[i] < res) {
				res = v[i];
			}
		}
		return res;
	}
}
