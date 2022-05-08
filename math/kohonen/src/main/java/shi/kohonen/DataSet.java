package shi.kohonen;

public class DataSet {

	private int sigalDim;
	private double[] maxValue;
	private double[] minValue;

	DataPoint[] data = null;

	DataSet(DataPoint[] data) {
		this.data = data;
		sigalDim = data[0].getPoint().length;
	}

	public void normalize() {
		int i, j, imax, imin;
		int trigger;
		double min = 0, max = 0;
		maxValue = new double[sigalDim];
		minValue = new double[sigalDim];

		for (j = 0; j < sigalDim; j++) {
			for (i = 0; i < data.length; i++) {
				if (i == 0) {
					max = data[i].getPoint()[j];
					min = data[i].getPoint()[j];
				} else {
					if (data[i].getPoint()[j] < min) {
						min = data[i].getPoint()[j];
					}

					if (data[i].getPoint()[j] > max) {
						max = data[i].getPoint()[j];
					}
				}
			}
			trigger = 1;
			// normalize the values in each dimension of the signal
			maxValue[j] = max;
			minValue[j] = min;

			imax = (int) (max);
			imin = (int) (min);

			if ((imax == 1) && (imin == 0) && (max <= 1.0) && (min <= 0.0)) {
				trigger = 0;
			}

			if ((imax == 1) && (imin == 1) && (max <= 1.0) && (min <= 1.0)) {
				trigger = 0;
			}

			if ((imax == 0) && (imin == 0) && (max <= 0.0) && (min <= 0.0)) {
				trigger = 0;
			}

			if (trigger != 0) // do not normalize binary signals
			{
				for (i = 0; i < data.length; i++) {
					data[i].getPoint()[j] = (data[i].getPoint()[j] - min) / (max - min);
				}
			}
		}

	}

	public int getSigalDim() {
		return sigalDim;
	}

	
}
