package math.cluster;

import java.util.Collection;
import java.util.List;

import math.cluster.distance.DistanceMeasure;

/**
 * base class for cluster, usually the as the enter.
 */
public abstract class Clusterer<T extends Clusterable> {

    /** distance measure */
    private DistanceMeasure measure;

    /**
     * build a clusterer use {@link DistanceMeasure} distance.
     */
    protected Clusterer(final DistanceMeasure measure) {
        this.measure = measure;
    }

    /**
     * base method for build a cluster, all clusterer should implement this method.
    */
    public abstract List<? extends Cluster<T>> cluster(Collection<T> points)
            throws Exception;

    /**
     * get the distance  {@link DistanceMeasure} measure.
     * @return �������
     */
    public DistanceMeasure getDistanceMeasure() {
        return measure;
    }

    /**
     * calculate the distance between the {@link Clusterable} point use {@link DistanceMeasure}.
     */
    protected double distance(final Clusterable p1, final Clusterable p2) {
        return measure.compute(p1.getPoint(), p2.getPoint());
    }

}
