package math.cluster.distance;

import math.cluster.util.MathArrays;

/**
 * Calculates the L<sub>&infin;</sub> (max of abs) distance between two points.
 */
public class ChebyshevDistance implements DistanceMeasure {


    public double compute(double[] a, double[] b) {
        return MathArrays.distanceInf(a, b);
    }

}
