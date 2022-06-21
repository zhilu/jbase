package math.cluster.kmeans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import math.cluster.CentroidCluster;
import math.cluster.Clusterable;
import math.cluster.Clusterer;
import math.cluster.DoublePoint;
import math.cluster.distance.DistanceMeasure;
import math.cluster.distance.EuclideanDistance;

/**
 * kmeans method
 * @author shi
 */
public class SimpleKMeansClusterer<T extends Clusterable> extends Clusterer<T> {

	private final int k;
	private final int iterations;
	private Random random;

	public SimpleKMeansClusterer(int k) {
		this(k,1000,new EuclideanDistance());
	}

	public SimpleKMeansClusterer(int k, int iter,DistanceMeasure measure) {
		super(measure);
		this.iterations =iter;
		this.k = k;
	}

	@Override
	public List<CentroidCluster<T>> cluster(Collection<T> points) {
    	if(null == points){
    		System.out.println("data set should not null");
    		System.exit(-1);
    	}
        if (points.size() < k) {         
            System.out.println("points is too small");
    		System.exit(-1);
        }
        List<CentroidCluster<T>> clusters = chooseInitialCenters(points);
        int[] assignments = new int[points.size()];
        assignPointsToClusters(clusters, points, assignments);
        final int max = (iterations < 0) ? Integer.MAX_VALUE : iterations;
        
        for (int count = 0; count < max; count++) {
        	List<CentroidCluster<T>> newClusters = new ArrayList<CentroidCluster<T>>();
        	for (final CentroidCluster<T> cluster : clusters) {
        		 final Clusterable newCenter = centroidOf(cluster.getPoints(), cluster.getCenter().getPoint().length);
        		 newClusters.add(new CentroidCluster<T>(newCenter));
        	}
        	int changes = assignPointsToClusters(newClusters, points, assignments);
        	if (changes == 0) {
                return clusters;
            }
            clusters = newClusters;
        }        
		return clusters;
	}

	private List<CentroidCluster<T>> chooseInitialCenters(
			final Collection<T> points) {
		final List<T> pointList = Collections.unmodifiableList(new ArrayList<T> (points));
		final int numPoints = pointList.size();
		final List<CentroidCluster<T>> resultSet = new ArrayList<CentroidCluster<T>>();
		
		Set<Integer> seeds = new HashSet<Integer>();
		while(seeds.size() <= k){
			int num = random.nextInt(numPoints);
			seeds.add(num);
		}
		
		for(Integer i:seeds){
			resultSet.add(new CentroidCluster<T>(pointList.get(i)));
		}
		return resultSet;
	}

	private int assignPointsToClusters(final List<CentroidCluster<T>> clusters,
			final Collection<T> points, final int[] assignments) {
		int assignedDifferently = 0;
		int pointIndex = 0;
		for (final T p : points) {
			int clusterIndex = getNearestCluster(clusters, p);
			if (clusterIndex != assignments[pointIndex]) {
				assignedDifferently++;
			}

			CentroidCluster<T> cluster = clusters.get(clusterIndex);
			cluster.addPoint(p);
			assignments[pointIndex++] = clusterIndex;
		}

		return assignedDifferently;
	}

	private int getNearestCluster(
			final Collection<CentroidCluster<T>> clusters, final T point) {
		double minDistance = Double.MAX_VALUE;
		int clusterIndex = 0;
		int minCluster = 0;
		for (final CentroidCluster<T> c : clusters) {
			final double distance = distance(point, c.getCenter());
			if (distance < minDistance) {
				minDistance = distance;
				minCluster = clusterIndex;
			}
			clusterIndex++;
		}
		return minCluster;
	}

	private Clusterable centroidOf(final Collection<T> points,
			final int dimension) {
		final double[] centroid = new double[dimension];
		for (final T p : points) {
			final double[] point = p.getPoint();
			for (int i = 0; i < centroid.length; i++) {
				centroid[i] += point[i];
			}
		}
		for (int i = 0; i < centroid.length; i++) {
			centroid[i] /= points.size();
		}
		return new DoublePoint(centroid);
	}
}