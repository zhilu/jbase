package shi.kohonen;

public interface Neuron {
	
	/**
	 * 进行初始化和重置
	 */
	public void reset();
	
	/**
	 * 更新
	 */
	public void update(DataPoint dat,double rate);
	
	
	public double calc(DataPoint dat);
}
