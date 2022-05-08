package math.cluster.distance;

/**
 * Interface for distance measures of n-dimensional vectors.
 */
public interface DistanceMeasure{

    /**
     * Compute the distance between two n-dimensional vectors.
     */
    double compute(double[] a, double[] b);
}
