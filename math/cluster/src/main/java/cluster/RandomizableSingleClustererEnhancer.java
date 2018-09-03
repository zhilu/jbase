package cluster;

/**
 * Abstract utility class for handling settings common to randomizable
 * clusterers.
 * 
 * @param <T>
 */
public abstract class RandomizableSingleClustererEnhancer<T extends Clusterable> extends AbstractClusterer<T>implements Randomizable {

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
