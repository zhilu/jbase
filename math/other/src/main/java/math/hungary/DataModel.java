package math.hungary;

public class DataModel {
	int[][] data = null;
	int n;
	
	public DataModel(int[][] data) {
		this.data = data;
		n = data.length;
	}

	public DataModel preProcess() {
		int [][] newData= data;
		for (int i = 0; i < n; i++) {
			int rowMin = Integer.MAX_VALUE;
			for (int j = 0; j < n; j++) {
				if (newData[i][j] < rowMin) {
					rowMin = newData[i][j];
				}
			}
			for (int j = 0; j < n; j++) {
				newData[i][j] -= rowMin;
			}
		}
		for (int i = 0; i < n; i++) {
			int colMin = Integer.MAX_VALUE;
			for (int j = 0; j < n; j++) {
				if (newData[j][i] < colMin) {
					colMin = newData[j][i];
				}
			}
			for (int j = 0; j < n; j++) {
				newData[j][i] -= colMin;
			}
		}
		return new DataModel(newData);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(data[i][j]).append("\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
