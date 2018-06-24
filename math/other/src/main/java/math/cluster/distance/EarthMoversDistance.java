package math.cluster.distance;

import math.cluster.util.FastMath;

/**
 * Calculates the Earh Mover's distance (also known as Wasserstein metric) between two distributions.
 */
public class EarthMoversDistance implements DistanceMeasure {

    public double compute(double[] a, double[] b) {
        double lastDistance = 0;
        double totalDistance = 0;
        for (int i = 0; i < a.length; i++) {
            final double currentDistance = (a[i] + lastDistance) - b[i];
            totalDistance += FastMath.abs(currentDistance);
            lastDistance = currentDistance;
        }
        return totalDistance;
    }
}
