package shi.kohonen;

public class DoublePoint implements DataPoint {

	double [] point=null; 
	
	public double[] getPoint() {
		
		return point;
	}

	public void set(double [] data){
		this.point=data;
	}
}
