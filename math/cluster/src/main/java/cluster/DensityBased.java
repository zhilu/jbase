package cluster;


/**
 * Interface for clusterers that can estimate the density for a given instance.
 * Implementations will typically extend AbstractDensityBasedClusterer.
 * @param <T>
 * 
 */
public interface DensityBased<T extends Clusterable> extends Clusterer<T> {

  /**
   * Returns the prior probability of each cluster.
   * 
   * @return the prior probability for each cluster
   */
  double[] clusterPriors() throws Exception;

  /**
   * Computes the log of the conditional density (per cluster) for a given
   * instance.
   * 
   * @param instance the instance to compute the density for
   * @return an array containing the estimated densities
  
   */
  double[] logDensityPerClusterForInstance(T instance);

  /**
   * Computes the density for a given instance.
   * 
   * @param instance the instance to compute the density for
   * @return the density.
   * @exception Exception if the density could not be computed successfully
   */
  double logDensityForInstance(T instance);

  /**
   * Returns the logs of the joint densities for a given instance.
   * 
   * @param inst the instance
   * @return the array of values
   * @exception Exception if values could not be computed
   */
  double[] logJointDensitiesForInstance(T inst);

  @Override
  double[] distributionForInstance(T instance);
}
