package math.simplex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

public class Simplex {

	public void solve(Model model) {
		//构造单纯性矩阵s，第一行为检验数，最后为目标，最后一列为约束边界，行数和列数各+1.
		Matrix A = model.getA();
		Matrix s = new Matrix(A.getRow() + 1, A.getCol() + 1);
		int m = s.getRow();
		int n = s.getCol();
		for (int i = 0; i < m; i++) {
			if (i == 0) {
				for (int j = 0; j < n - 1; j++) {
					s.setElement(i, j, model.getF()[j]);
				}
				s.setElement(i, n - 1, 0);
			} else {
				for (int j = 0; j < n - 1; j++) {
					s.setElement(i, j, A.getElement(i - 1, j));
				}
				s.setElement(i, n - 1, model.getB()[i - 1]);
			}
		}
		
		//开始单纯性转换，确定检验数大于0的位置定位
		HashMap<Double,Integer> checks = new HashMap<Double,Integer>();
		double [] checkVec = s.getRowVec(0);
		for (int i = 0; i < checkVec.length-1; i++) {
			if(checkVec[i]>0){
				checks.put(checkVec[i], i);
			}
		}
		TreeMap<Double,Integer> treeMap = new TreeMap<Double,Integer>(checks);  //自动按检验数排序，最大检验数为最后一个
		if(treeMap.isEmpty()){//检验数都小于0，无法使用单纯性
			System.out.println("无解");
			System.exit(1);
		}
		
		while (treeMap.size()>0){
			//转轴列
			int col = treeMap.lastEntry().getValue(); 
			double [] colVec = s.getColVec(col);
			
			boolean isStop = true;
			for (int i = 1; i < colVec.length; i++) {  //第一个为检验数，不比较
				isStop &= colVec[i]<=0;
			}
			if(isStop){
				System.out.println("问题无界");
				System.exit(1);
			}
			
			double []  last= s.getColVec(s.getCol()-1);
			double [] temp = new double[s.getRow()];
			for (int i = 1; i < temp.length; i++) {
				temp[i]=last[i]/colVec[i];
			}
			
			for (int i = 1; i < temp.length; i++) {
				if(temp[i]<0){
					temp[i]=Double.MAX_VALUE;
				}
			}
			int row = 1;
			double value = temp[1];
			for (int i = 1; i < temp.length; i++) {
				if(temp[i]< value){
					value = temp[i];
					row = i;
				}
			}
			
			//换基
			double base  = s.getElement(row, col);
			for (int i = 0; i < s.getCol(); i++) {
				double tmp = s.getElement(row, i)/base;
				s.setElement(row, i, tmp);
			}
			
			for (int i = 0; i < s.getRow(); i++) {
				if(i != row){
					double Base = s.getElement(i,col);
					for (int j = 0; j < s.getCol(); j++) {
						double tmp = s.getElement(row,j)*Base;
						s.setElement(i, j, s.getElement(i, j)-tmp);
					}
				}				
			}
			checks = new HashMap<Double,Integer>();
			checkVec = s.getRowVec(0);
			for (int i = 0; i < checkVec.length-1; i++) {
				if(checkVec[i]>0){
					checks.put(checkVec[i], i);
				}
			}
			treeMap = new TreeMap<Double,Integer>(checks);
		}
		
		double[] ans = new double[s.getCol()-1];
		double[] stat = null;
		for (int i = 0; i < ans.length; i++) {
			stat = s.getColVec(i);
			int one = 0,zero=0,index=-1;
			for (int j = 0; j < stat.length; j++) {
				if(stat[j]==0){
					zero++;
				}else if(stat[j]==1){
					one++;
					index = j;
				}				
			}
			if(one + zero == s.getRow()){
				ans[i] = s.getElement(index, s.getCol()-1);
			}
		}
		
		model.setSolution(ans);
		model.setValue(s.getElement(0, s.getCol()-1));		
		
	}

	public static void main(String[] args) {
		//约束矩阵为A,右侧为向量b,检验数为向量f
//		double[] A = { 1, 1, 1, 0, 0, 1, 2, 0, 1, 0, 3, 2, 0, 0, 1 };
//		double[] b = { 50, 80, 140 };
//		double[] f = { 4, 3, 0, 0, 0 };
		double[] A = { -2, 1, 1, 0, 0, 1, -3, 0, 1, 0, 1, -1, 0, 0, 1 };
		double[] b = { 2, 1, 2 };
		double[] f = { 1, -2, 0, 0, 0 };
		
		//将数据放入模型
		Matrix cons = new Matrix(3, 5, A);
		Model m = new Model(cons, b, f);
		
		//单纯行解线性规划
		Simplex sol = new Simplex();
		sol.solve(m);
		
		System.out.println(m.getValue());
		System.out.println(Arrays.toString(m.getSolution()));
	}
}
