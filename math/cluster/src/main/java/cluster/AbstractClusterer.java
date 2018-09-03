package cluster;

import java.util.Collection;

import cluster.distance.DistanceMeasure;



public abstract class AbstractClusterer<T extends Clusterable> implements Clusterer<T> {

	protected boolean isDebug = false;

	// ===============
	// Public methods.
	// ===============

	@Override
	public abstract void buildClusterer(Collection<T> data);

	@Override
	public int clusterInstance(T instance) {

		double[] dist = distributionForInstance(instance);

		if (dist == null) {
			// throw new Exception("Null distribution predicted");
		}

		// if (Utils.sum(dist) <= 0) {
		// throw new Exception("Unable to cluster instance");
		// }
		// return Utils.maxIndex(dist);
		return 0;
	}

	@Override
	public double[] distributionForInstance(T instance) {

		double[] d = new double[getK()];

		d[clusterInstance(instance)] = 1.0;

		return d;
	}

	@Override
	public abstract int getK();

	/**
	 * Set debugging mode.
	 * 
	 * @param debug
	 *            true if debug output should be printed
	 */
	public void setDebug(boolean debug) {
		isDebug = debug;
	}

	/**
	 * Get whether debugging is turned on.
	 * 
	 * @return true if debugging output is on
	 */
	public boolean getDebug() {

		return isDebug;
	}
	
    /** distance measure */
    private DistanceMeasure measure;

    /**
     * build a clusterer use {@link DistanceMeasure} distance.
     */

    public void setMeasure(DistanceMeasure measure) {
		this.measure = measure;
	}

	/**
     * get the distance  {@link DistanceMeasure} measure.
     * @return
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
