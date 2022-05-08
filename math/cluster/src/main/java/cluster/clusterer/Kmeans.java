package cluster.clusterer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cluster.AbstractClusterer;
import cluster.CentroidCluster;
import cluster.Clusterable;
import cluster.DoublePoint;
import cluster.KRequestable;
import cluster.Randomizable;


public class Kmeans<T extends Clusterable> extends AbstractClusterer<T>implements KRequestable, Randomizable {

	private int k = 2;
	private int seed = 123432;

	private Random random = new Random(getSeed());
	private int iterations;
	
	List<CentroidCluster<T>> clusters = null;

	@Override
	public void buildClusterer(Collection<T> points) {
		clusters = chooseInitialCenters(points);
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
				return;
			}
			clusters = newClusters;
		}
	}

	private List<CentroidCluster<T>> chooseInitialCenters(final Collection<T> points) {
		final List<T> pointList = Collections.unmodifiableList(new ArrayList<T>(points));
		final int numPoints = pointList.size();
		final List<CentroidCluster<T>> resultSet = new ArrayList<CentroidCluster<T>>();

		Set<Integer> seeds = new HashSet<Integer>();
		while (seeds.size() <= k) {
			int num = random.nextInt(numPoints);
			seeds.add(num);
		}

		for (Integer i : seeds) {
			resultSet.add(new CentroidCluster<T>(pointList.get(i)));
		}
		return resultSet;
	}

	private int assignPointsToClusters(final List<CentroidCluster<T>> clusters, final Collection<T> points,
			final int[] assignments) {
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

	private int getNearestCluster(final Collection<CentroidCluster<T>> clusters, final T point) {
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
	////////////////////////
	// getter and setter //
	////////////////////////

	@Override
	public void setSeed(int seed) {
		this.seed = seed;
	}

	@Override
	public int getSeed() {
		return seed;
	}

	@Override
	public void setK(int numClusters) {
		this.k = numClusters;
	}

	public int getK() {
		return k;
	}

	
	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public List<CentroidCluster<T>> getClusters() {
		return clusters;
	}
}
