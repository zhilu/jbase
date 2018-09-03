package cluster.clusterer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cluster.CentroidCluster;
import cluster.Clusterable;
import cluster.RandomizableClusterer;
import cluster.distance.DistanceMeasure;

/**
 * Cluster data using the FarthestFirst algorithm.
 * 
 * @author shi
 *
 */
public class FarthestFirst<T extends Clusterable> extends RandomizableClusterer<T> {

	private int k = 2;

	private double[] m_Min;
	private double[] m_Max;

	private int nrow;
	private int ncol;

	
	List<CentroidCluster<T>> m_ClusterCentroids = null;
	public void setMeasure(DistanceMeasure measure) {
		super.setMeasure(measure);
	}
	
	@Override
	public void buildClusterer(Collection<T> points) {
		List<T> data = Collections.unmodifiableList(new ArrayList<T>(points));
		nrow = data.size();
		ncol = data.get(0).getPoint().length;

		initMinMax(data);

		m_ClusterCentroids = new ArrayList<CentroidCluster<T>>(k);

		Random r = new Random(getSeed());
		boolean[] selected = new boolean[nrow];
		double[] minDistance = new double[nrow];

		for (int i = 0; i < nrow; i++) {
			minDistance[i] = Double.MAX_VALUE;
		}

		int firstI = r.nextInt(nrow);
		m_ClusterCentroids.add(new CentroidCluster<T>(data.get(firstI)));
		selected[firstI] = true;

		updateMinDistance(minDistance, selected, data, data.get(firstI));

		if (k > nrow) {
			k = nrow;
		}

		for (int i = 1; i < k; i++) {
			int nextI = farthestAway(minDistance, selected);
			m_ClusterCentroids.add(new CentroidCluster<T>(data.get(nextI)));
			selected[nextI] = true;
			updateMinDistance(minDistance, selected, data, data.get(nextI));
		}

		data = null;
	}

	private int farthestAway(double[] minDistance, boolean[] selected) {
		double maxDistance = -1.0;
		int maxI = -1;
		for (int i = 0; i < selected.length; i++) {
			if (!selected[i]) {
				if (maxDistance < minDistance[i]) {
					maxDistance = minDistance[i];
					maxI = i;
				}
			}
		}
		return maxI;
	}

	private void updateMinDistance(double[] minDistance, boolean[] selected, List<T> data, T t) {

		for (int i = 0; i < selected.length; i++)
			if (!selected[i]) {
				double d = distance(t, data.get(i));
				if (d < minDistance[i])
					minDistance[i] = d;
			}

	}

	private void initMinMax(List<T> data) {
		m_Min = new double[ncol];
		m_Max = new double[ncol];
		for (int i = 0; i < ncol; i++) {
			m_Min[i] = m_Max[i] = Double.NaN;
		}

		for (int i = 0; i < nrow; i++) {
			updateMinMax(data.get(i).getPoint());
		}

	}

	private void updateMinMax(double[] point) {
		for (int j = 0; j < ncol; j++) {
			if (Double.isNaN(m_Min[j])) {
				m_Min[j] = point[j];
				m_Max[j] = point[j];
			} else {
				if (point[j] < m_Min[j]) {
					m_Min[j] = point[j];
				} else {
					if (point[j] > m_Max[j]) {
						m_Max[j] = point[j];
					}
				}
			}
		}
	}

	@Override
	public int getK() {
		return 0;
	}
	
	
	@Override
	  public String toString() {
	    StringBuffer temp = new StringBuffer();

	    temp.append("\nFarthestFirst\n==============\n");

	    temp.append("\nCluster centroids:\n");
	    for (int i = 0; i < k; i++) {
	      temp.append("\nCluster " + i + "\n\t");
	      for (int j = 0; j < ncol; j++) {

	          temp.append(" " + m_ClusterCentroids.get(i).getPoints().size());
	        }
	    }
	    temp.append("\n\n");
	    return temp.toString();
	  }

}
