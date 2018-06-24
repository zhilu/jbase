
package cluster;

import java.util.Collection;

/**
 * Interface for clusterers. Clients will typically extend either
 * AbstractClusterer or AbstractDensityBasedClusterer.
 * @param <Clusterable>
 */
public interface Clusterer<T extends Clusterable> {

  /**
   * Generates a clusterer. Has to initialize all fields of the clusterer
   * that are not being set via options.
   *
   * @param data set of instances serving as training data 
   */
  void buildClusterer(Collection<T> data) throws Exception;

  /**
   * Classifies a given instance. Either this or distributionForInstance()
   * needs to be implemented by subclasses.
   *
   * @param instance the instance to be assigned to a cluster
   * @return the number of the assigned cluster as an integer
   * @exception Exception if instance could not be clustered
   * successfully
   */
  int clusterInstance(T instance);

  /**
   * Predicts the cluster memberships for a given instance.  Either
   * this or clusterInstance() needs to be implemented by subclasses.
   *
   * @param instance the instance to be assigned a cluster.
   * @return an array containing the estimated membership 
   * probabilities of the test instance in each cluster (this 
   * should sum to at most 1)
   */
  public double[] distributionForInstance(T instance);

  /**
   * Returns the number of clusters.
   *
   * @return the number of clusters generated for a training dataset.
   */
  int getK();
  
}
