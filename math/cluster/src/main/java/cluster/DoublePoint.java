package cluster;

import java.util.Arrays;

/**
 * an impelment of clusterable point.
 */
public class DoublePoint implements Clusterable{

    /** the point */
    private final double[] point;

    /**
     *  double constructor
     */
    public DoublePoint(final double[] point) {
        this.point = point;
    }
    /**
     * int constructor
     * @param point
     */
    public DoublePoint(final int[] point) {
        this.point = new double[point.length];
        for ( int i = 0; i < point.length; i++) {
            this.point[i] = point[i];
        }
    }

 
    public double[] getPoint() {
        return point;
    }


    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof DoublePoint)) {
            return false;
        }
        return Arrays.equals(point, ((DoublePoint) other).point);
    }


    @Override
    public int hashCode() {
        return Arrays.hashCode(point);
    }


    @Override
    public String toString() {
        return Arrays.toString(point);
    }

}
