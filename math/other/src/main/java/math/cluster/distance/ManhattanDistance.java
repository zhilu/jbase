package math.cluster.distance;

import math.cluster.util.MathArrays;

/**
 * Calculates the L<sub>1</sub> (sum of abs) distance between two points.
 */
public class ManhattanDistance implements DistanceMeasure {

    public double compute(double[] a, double[] b) {
        return MathArrays.distance1(a, b);
    }

}
