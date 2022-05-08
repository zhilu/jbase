package shi.kohonen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Util {
	
	static DataSet getFromFile() throws Exception{
		DoublePoint[] datas= new DoublePoint[150];
		
		BufferedReader br =new BufferedReader(new FileReader("iris.csv"));
		String line =null;
		int j=0;
		while((line=br.readLine()) != null){
			String [] strs=line.split(",");
			double[] data= new double[4];
			for (int i = 0; i < data.length; i++) {
				data[i]=Double.parseDouble(strs[i]);
			}
			DoublePoint dp= new DoublePoint();
			dp.set(data);
			datas[j++]=dp;
		}
		
		return new DataSet(datas);
		
	}
	
	
	public static void main(String[] args) throws Exception {
		DataSet ds = getFromFile();
		ds.normalize();
		KohonenNet kn = new KohonenNet(3);
		kn.train(ds);
		int [] res =kn.predict(ds);
		System.out.println(Arrays.toString(res));
	}
}
