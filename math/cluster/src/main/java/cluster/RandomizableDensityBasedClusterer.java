package cluster;


/**
 * Abstract utility class for handling settings common to randomizable
 * clusterers.
 * 
 */
public abstract class RandomizableDensityBasedClusterer<T extends Clusterable> extends AbstractDensityBasedClusterer<T>
		implements Randomizable {

	/** the default seed value */
	protected int defaultSeed = 1;

	/** The random number seed. */
	protected int seed = defaultSeed;

	@Override
	public void setSeed(int value) {
		seed = value;
	}

	@Override
	public int getSeed() {
		return seed;
	}
}