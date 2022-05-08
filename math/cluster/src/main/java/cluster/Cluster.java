package cluster;

import java.util.ArrayList;
import java.util.List;

/**
 * Cluster holding a set of {@link Clusterable} points.
 */
public class Cluster<T extends Clusterable> {

    /** The points contained in this cluster. */
    private final List<T> points;

    /**
     * Build a cluster
     */
	public Cluster() {
        points = new ArrayList<T>();
    } 

    /**
     * add a point to cluster
     */
    public void addPoint(final T point) {
        points.add(point);
    }

    /**
     * get the all points
     */
    public List<T> getPoints() {
        return points;
    }

}
