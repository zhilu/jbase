package cluster;

/**
 * Abstract clustering model that produces (for each test instance) an estimate
 * of the membership in each cluster (ie. a probability distribution).
 */
public abstract class AbstractDensityBasedClusterer<T extends Clusterable> extends AbstractClusterer<T>implements DensityBased<T> {

	// ===============
	// Public methods.
	// ===============

	/**
	 * Returns the prior probability of each cluster.
	 *
	 * @return the prior probability for each cluster
	 * 
	 */
	public abstract double[] clusterPriors();

	/**
	 * Computes the log of the conditional density (per cluster) for a given
	 * instance.
	 * 
	 * @param instance
	 *            the instance to compute the density for
	 * @return an array containing the estimated densities
	 */
	public abstract double[] logDensityPerClusterForInstance(T instance);

	/**
	 * Computes the density for a given instance.
	 * 
	 * @param instance
	 *            the instance to compute the density for
	 * @return the density.
	 */
	public double logDensityForInstance(T instance) {

		double[] a = logJointDensitiesForInstance(instance);
		// double max = a[Utils.maxIndex(a)];
		double max = 0;
		double sum = 0.0;

		for (int i = 0; i < a.length; i++) {
			sum += Math.exp(a[i] - max);
		}

		return max + Math.log(sum);
	}

	/**
	 * Returns the cluster probability distribution for an instance.
	 *
	 * @param instance
	 *            the instance to be clustered
	 * @return the probability distribution
	 */
	public double[] distributionForInstance(T instance) {
		return null;

		// return Utils.logs2probs(logJointDensitiesForInstance(instance));
	}

	/**
	 * Returns the logs of the joint densities for a given instance.
	 *
	 * @param inst
	 *            the instance
	 * @return the array of values
	 * @exception Exception
	 *                if values could not be computed
	 */
	public double[] logJointDensitiesForInstance(T inst) {

		double[] weights = logDensityPerClusterForInstance(inst);
		double[] priors = clusterPriors();

		for (int i = 0; i < weights.length; i++) {
			if (priors[i] > 0) {
				weights[i] += Math.log(priors[i]);
			} else {
				throw new IllegalArgumentException("Cluster empty!");
			}
		}
		return weights;
	}

}
