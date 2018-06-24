package math.cluster.evaluation;
/**
 *  calculate variance by increment method.
 * @author shi
 */
public class Variance {

	double m1 = 0d;  //first moment
	double m2 = 0d;  //second moment
	int count = 0;

	double dev = 0.0d;
	double nDev = 0.0d;

	public void increment(double d) {
		if (count == 0) {
			m1 = 0.0;
		}
		if (count < 1) {
			m2 = 0.0;
		}

		count++;
		double n0 = count;
		dev = d - m1;
		nDev = dev / n0;
		m1 += nDev;

		m2 += ((double) count - 1) * dev * nDev;
	}

	public double getResult() {
		if (count == 0) {
			return Double.NaN;
		} else if (count == 1) {
			return 0d;
		} else {
			return m2 / (count - 1d);
		}
	}

//	public static void main(String[] args) {
//		double[] d = { 4.3, 2.3, 5.4, 6.4, 1.3, 4.5 };
//		Variance v = new Variance();
//		for (int i = 0; i < d.length; i++) {
//			v.increment(d[i]);
//		}
//		System.out.println(v.getResult());
//	}
}
