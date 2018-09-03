package cluster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import cluster.clusterer.FarthestFirst;
import cluster.distance.EuclideanDistance;

public class Main {
	public static void main(String[] args) throws Exception {
		File iris = new File("./iris.csv");
		BufferedReader br = new BufferedReader(new FileReader(iris));
		String str = br.readLine();
		List<Clusterable> list = new ArrayList<Clusterable>();
		while(( str= br.readLine()) !=null){
			double[] piont = new double [4];
			String [] strs = str.split(",");
			for (int i = 0; i < strs.length-1; i++) {
				piont[i] = Double.parseDouble(strs[i]);
			}
			list.add(new DoublePoint(piont));
		}
		
		
		FarthestFirst<Clusterable> ff = new FarthestFirst<Clusterable>();
	
		ff.setMeasure(new EuclideanDistance());
		ff.buildClusterer(list);
		
		
		System.out.println(ff.toString());
		
	}
}
