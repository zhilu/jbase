package math.cluster.distance;

import math.cluster.util.FastMath;

/**
 * Calculates the Canberra distance between two points.
 */
public class CanberraDistance implements DistanceMeasure {

    public double compute(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            final double num = FastMath.abs(a[i] - b[i]);
            final double denom = FastMath.abs(a[i]) + FastMath.abs(b[i]);
            sum += num == 0.0 && denom == 0.0 ? 0.0 : num / denom;
        }
        return sum;
    }

}
