package ds.refactor;
/**
 * ����������ڲ��ڵ� <br>
 * 1. �洢���� <br>
 * 2. �洢��һ���ڵ��Ӧ�� <br>
 * 
 * @author shi
 *
 */
public class SingleLink {

	public Object data;     
	public SingleLink next;
	
	public SingleLink(Object data){
		this.data = data;
	}

	@Override
	public String toString() {
		return " data=" + data ;
	}
	
}
