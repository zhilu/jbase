package cluster;

/**
 * cluster center
 */
public class CentroidCluster<T extends Clusterable> extends Cluster<T> {

    /** cluster center */
    private final Clusterable center;

    /**
     * build a cluster center 
     */
    public CentroidCluster(final Clusterable center) {
        super();
        this.center = center;
    }

    /**
     */
    public Clusterable getCenter() {
        return center;
    }

}
