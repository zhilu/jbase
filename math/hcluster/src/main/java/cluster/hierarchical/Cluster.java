package cluster.hierarchical;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cluster {
	private ClusterablePoint centriod;
	List<Cluster> childClusters = new ArrayList<Cluster>(2);
	Set<ClusterablePoint> clusterPoints = new HashSet<ClusterablePoint>();

	private Cluster() {
	}

	public Cluster(ClusterablePoint point) {
		clusterPoints.add(point);
		calculateCentriod();
	}

	public ClusterablePoint getCentriod() {
		return centriod;
	}

	private void calculateCentriod() {
		if (clusterPoints.size() == 1) {
			this.centriod = clusterPoints.iterator().next();
		}
		/**
		 * Common choices include selecting as the clustroid the point that
		 * minimizes: 1. The sum of the distances to the other points in the
		 * cluster. 2. The maximum distance to another point in the cluster. 3.
		 * The sum of the squares of the distances to the other points in the
		 * cluster.
		 * 
		 * We do #1 here
		 */
		ClusterablePoint currentCentriod = null;
		int minDistanceSofar = Integer.MAX_VALUE;
		for (ClusterablePoint point1 : clusterPoints) {
			double distance = 0;
			for (ClusterablePoint point2 : clusterPoints) {
				if (!point1.equals(point2)) {
					distance = point1.getDistance(point2);
				}
				if (distance > minDistanceSofar) {
					break;
				}
			}
			if (distance < minDistanceSofar) {
				currentCentriod = point1;
			}
		}
		this.centriod = currentCentriod;
		if (currentCentriod == null) {
			throw new RuntimeException("centriod must not be null");
		}
	}

	public Cluster merge(Cluster other) {
		Cluster mergedClusters = new Cluster();
		mergedClusters.clusterPoints.addAll(this.clusterPoints);
		mergedClusters.clusterPoints.addAll(other.clusterPoints);

		mergedClusters.childClusters.add(this);
		mergedClusters.childClusters.add(other);
		mergedClusters.calculateCentriod();
		return mergedClusters;
	}

	public Set<ClusterablePoint> getItemsInCluster() throws Exception {
		// List<ClusterablePoint> list = new ArrayList<ClusterablePoint>();
		//
		// LinkedBlockingQueue<Cluster> clusters2Process = new
		// LinkedBlockingQueue<Cluster>();
		// clusters2Process.add(this);
		// while(!clusters2Process.isEmpty()){
		// Cluster take = clusters2Process.take();
		// list.addAll(take.clusterPoints);
		// clusters2Process.addAll(take.childClusters);
		// }
		// return list;
		return clusterPoints;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("[");
		for (ClusterablePoint point : clusterPoints) {
			buffer.append(point.print()).append(" ");
		}
		buffer.append("]");
		return buffer.toString();
	}

}