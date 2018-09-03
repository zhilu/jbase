package shi.kohonen;

public class KohonenNet implements Net {

	private static final double MIN_LEARNING_RATE=0.3;
	private static final double MAX_LEARNING_RATE=0.7;
	private static final int MAX_EPOCH=300;
	private int num;
	private Neuron[] neurons=null;
	private int dim;
	private int winner=0;
	
	public KohonenNet(int num){
		this.num=num;
		neurons = new Neuron[num];
	}
	
	public void init() {
		for (int i = 0; i < neurons.length; i++) {
			neurons[i]=new KohonenNeuron(dim);
			neurons[i].reset();
		}

	}

	public void train(DataSet ds) {
		dim=ds.getSigalDim();
		init();
		
		train0(ds);
		
	}

	
	private void train0(DataSet ds){
		double minCalc=0;
		int ep=0;
		do {
			for (int i = 0; i < ds.data.length; i++) {
				DataPoint data=ds.data[i];
				for (int j = 0; j < neurons.length; j++) {
					if(j==0){
						minCalc=neurons[j].calc(data);
						winner=j;
					}else{
						double dist=neurons[j].calc(data);
						if(dist<minCalc){
							minCalc=neurons[j].calc(data);
							winner=j;
						}
					}
				}
				update(data,ep,MAX_EPOCH);
				ep++;
			}
		} while (ep<MAX_EPOCH);
	}

	private void update(DataPoint dp,int ep, int maxEpoch) {
		
		double adjRate = MAX_LEARNING_RATE
				- (((MAX_LEARNING_RATE - MIN_LEARNING_RATE) / maxEpoch) * ep);
		neurons[winner].update(dp,adjRate);
	}
	
	
	public int[] predict(DataSet ds){
		double minCalc=0;
		int [] res = new int[ds.data.length];
		for (int i = 0; i < ds.data.length; i++) {
			DataPoint data=ds.data[i];
			for (int j = 0; j < neurons.length; j++) {
				if(j==0){
					minCalc=neurons[j].calc(data);
					winner=j;
				}else{
					double dist=neurons[j].calc(data);
					if(dist<minCalc){
						minCalc=neurons[j].calc(data);
						winner=j;
					}
				}
			}
			res[i]=winner;
		}
		
		return res;
		
	}
}
