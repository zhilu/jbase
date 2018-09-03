package cluster.distance;

import cluster.util.MathArrays;

/**
 * Calculates the L<sub>2</sub> (Euclidean) distance between two points.
 */
public class EuclideanDistance implements DistanceMeasure {

    public double compute(double[] a, double[] b) {
        return MathArrays.distance(a, b);
    }

}
