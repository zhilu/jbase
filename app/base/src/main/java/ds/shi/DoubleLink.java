package ds.shi;
/**
 * ˫��������ڲ��ڵ� <br>
 * 1. ����<br>
 * 2. ��һ���ڵ������<br>
 * 3. ��һ���ڵ������<br>
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