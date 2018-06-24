package cluster;

/**
 * Meta-clusterer for enhancing a base clusterer.
 */
public abstract class SingleClustererEnhancer<T extends Clusterable> extends AbstractClusterer<T> {


	/** the clusterer */
	protected Clusterer<T> m_Clusterer = null;// new SimpleKMeans();

	/**
	 * String describing default clusterer.
	 * 
	 * @return the default clusterer classname
	 */
	protected String defaultClustererString() {
		return null;
		// return SimpleKMeans.class.getName();
	}

	/**
	 * Set the base clusterer.
	 * 
	 * @param value
	 *            the classifier to use.
	 */
	public void setClusterer(Clusterer<T> value) {
		m_Clusterer = value;
	}

	/**
	 * Get the clusterer used as the base clusterer.
	 * 
	 * @return the base clusterer
	 */
	public Clusterer<T> getClusterer() {
		return m_Clusterer;
	}

	@Override
	public int getK() {
		return m_Clusterer.getK();
	}
}
