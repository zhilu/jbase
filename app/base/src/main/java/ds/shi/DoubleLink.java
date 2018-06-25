package ds.shi;
/**
 * 双向链表的内部节点 <br>
 * 1. 数据<br>
 * 2. 下一个节点的引用<br>
 * 3. 上一个节点的引用<br>
 * 
 * @author shi
 *
 */
public class DoubleLink {
	public Object data; // data item
	public DoubleLink next; // next link in list
	public DoubleLink previous; // previous link in list

	public DoubleLink(Object d) 
	{
		data = d;
	}

	
	@Override
	public String toString() {
		return " data=" + data;
	}
	
} 