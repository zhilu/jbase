package ds.refactor;
/**
 * 单向链表的内部节点 <br>
 * 1. 存储数据 <br>
 * 2. 存储下一个节点的应用 <br>
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
