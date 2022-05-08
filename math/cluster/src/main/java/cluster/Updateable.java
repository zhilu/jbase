package cluster;

/**
 * Interface to incremental cluster models that can learn using one instance at
 * a time.
 */
public interface Updateable<T> {

	/**
	 * Adds an instance to the clusterer.
	 *
	 * @param data
	 *            the instance to be added
	 */
	public void update(T data);

	/**
	 * Signals the end of the updating.
	 */
	public void Finished();
}
