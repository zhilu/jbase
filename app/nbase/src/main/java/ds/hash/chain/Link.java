package ds.hash.chain;
/**
 * ´æ´¢¶ÔÏó
 * @author shi
 *
 */
public class Link {
	private int iData;
	public Link next;

	public Link(int it) {
		iData = it;
	}

	public int getKey() {
		return iData;
	}

	public void displayLink() {
		System.out.print(iData + " ");
	}
}