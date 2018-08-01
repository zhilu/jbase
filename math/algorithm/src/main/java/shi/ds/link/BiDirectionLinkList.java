package shi.ds.link;

public class BiDirectionLinkList<T> {

	private Node head;

	public void insert(T value) {
		Node node = new Node(value);
		if (null == head) {
			head = node;
		} else {
			Node parent = head;
			Node current = head.getNext();

			while (null != current) {
				parent = current;
				current = current.getNext();
			}

			parent.setNext(node);
			node.setPrevious(parent);
		}
	}

	public Node delete(T value) {
		if (null == head) {
			return null;
		}
		
		Node current = head;
		while (null != current && !current.getValue().equals(value)) {
			current = current.getNext();
		}
		
		if(null ==current){
			return null;
		}
		if(current.equals(head)){
			head=current.getNext();
			head.setPrevious(null);
			
			current.setNext(null);
			return current;
		}else if (null == current.getNext()) {
			current.getPrevious().setNext(null);
			
			current.setPrevious(null);
			return current;
		}else{
			current.getNext().setPrevious(current.getPrevious());
			current.getPrevious().setNext(current.getNext());
			
			current.setNext(null);
			current.setPrevious(null);
			return current;
		}
	}

	/**
	 * 链表长度
	 * 
	 * @return
	 */
	public int length() {
		int len = 0;
		Node current = head;
		while (null != current) {
			len++;
			current = current.getNext();
		}
		return len;
	}

	public String print() {
		StringBuilder sb = new StringBuilder();
		Node current = head;
		while (null != current) {
			sb.append(",").append(current.getValue().toString());
			current = current.getNext();
		}
		return sb.substring(1);

	}

	class Node {
		private T value;
		private Node next;
		private Node previous;

		Node(T value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node getPrevious() {
			return previous;
		}

		public void setPrevious(Node previous) {
			this.previous = previous;
		}

		public T getValue() {
			return value;
		}

	}

	public static void main(String[] args) {
		BiDirectionLinkList<Integer> ll = new BiDirectionLinkList<Integer>();
		for (int i = 1; i < 5; i++) {
			ll.insert(i);
		}

		System.out.println(ll.print());
		System.out.println(ll.length());
		BiDirectionLinkList<Integer>.Node r = ll.delete(5);
	//	System.out.println(r.getValue()+",P:"+r.getPrevious()+",N:"+r.getNext());
		System.out.println(r);
		System.out.println(ll.print());
	}
}
