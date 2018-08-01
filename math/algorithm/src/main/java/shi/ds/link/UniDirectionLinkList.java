package shi.ds.link;

public class UniDirectionLinkList<T> {

	Node head;

	public boolean insert(T Value,int loc){
		Node node= new Node(Value);
		if(0==loc){
			node.setNext(head);
			head=node;
		}else{
			int n=1;
			Node parent=head;
			Node next=head.getNext();
			while(null != next){
				if(loc==n){
					node.setNext(next);
					parent.setNext(node);
					break;
				}else{
					parent=next;
					next=next.getNext();
					n++;
				}
			}
			if(loc>=n){
				parent.setNext(node);
			}
		}
		return false;
	}
	public boolean addTail(T value) {
		Node node = new Node(value);

		if (null != head) {
			Node previous = head;
			Node current = head.getNext();
			while (null != current) {
				previous = current;
				current = current.getNext();
			}
			previous.setNext(node);
		} else {
			head = node;
		}
		return true;
	}

	public boolean addHead(T value) {
		Node node = new Node(value);
		if (null == head) {
			head = node;
		} else {
			node.setNext(head);
			head = node;
		}
		return true;
	}
	
	public boolean deleteOne(T value){
		if(null ==head){
			return false;
		}else if(head.getValue().equals(value)){
			head=head.getNext();
			return true;
		}
		Node parent=head;
		Node current=head.getNext();
		while(null != current){
			if(current.getValue().equals(value)){
				parent.setNext(current.getNext());
				return true;
			}
			parent=current;
			current=current.getNext();
		}
		return false;
	}

	public int length() {
		int n = 0;
		Node next = head;
		while (null != next) {
			n++;
			next = next.getNext();
		}
		return n;
	}
	/**
	 * 实现单向链表的反向
	 */
	public void reverse(){
		if(null==head || null == head.getNext()){
			return;
		}
		Node ahead=null;
		Node behind = head;
		Node current=head.getNext();
		head.setNext(null);
		while(null != current){
			ahead=current.getNext();
			current.setNext(behind);
			behind=current;
			current=ahead;
		}
		head=behind;
	}
	

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (null != head) {
			sb.append(head.getValue());
			Node next = head.getNext();
			while (null != next) {
				sb.append("," + next.getValue().toString());
				next = next.getNext();
			}
		}
		return sb.toString();
	}

	class Node {
		private T value;
		private Node next;

		Node(T value) {
			this.value = value;
		}

		public T getValue() {
			return value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}

	}

	public static void main(String[] args) {

		UniDirectionLinkList<Integer> ll = new UniDirectionLinkList<Integer>();
		for (int i = 1; i < 4; i++) {
			ll.addTail(i);
		}
		System.out.println(ll);
		ll.reverse();
		System.out.println(ll);
	}
}
