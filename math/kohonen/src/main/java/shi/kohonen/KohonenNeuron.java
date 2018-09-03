package shi.kohonen;

import java.util.Random;

public class KohonenNeuron implements Neuron{

	private static final Random R= new Random();
	private double [] weight=null;
	private int len=0;
	private double value=0d;
	
	public KohonenNeuron(int len){
		this.len=len;
	}
	
	public void reset() {
		weight= new double[len];
		for (int i = 0; i < weight.length; i++) {
			weight[i]=R.nextDouble();
		}
	}

	public double calc(DataPoint dat) {
		double ssq=0;
		double[] input=dat.getPoint();
		for (int i = 0; i < input.length; i++) {
			ssq=Math.pow(weight[i]-input[i], 2);
			value+=ssq;
		}
		value=Math.sqrt(value);
		return value;
	}

	public void update(DataPoint dat,double rate) {
		double[] input=dat.getPoint();
		for (int i = 0; i < input.length; i++) {
			weight[i] = weight[i] + (rate * (input[i] - weight[i]));
		}
	}



}
