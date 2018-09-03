package ds.hash;

/**
 * hash中每个节点存储的对象类型
 * 
 * @author shi
 *
 */
public class DataItem { 
	
	private int iData; 
	
	public DataItem(int i) {
		iData = i;
	}

	public int getKey() {
		return iData;
	}
} 
