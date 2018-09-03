package cluster;

/**
 * Interface to a clusterer that can generate a requested number of clusters
 */
public interface KRequestable {

	/**
	 * Set the number of clusters to generate
	 *
	 * @param numClusters
	 *            the number of clusters to generate
	 */
	void setK(int numClusters);

}
