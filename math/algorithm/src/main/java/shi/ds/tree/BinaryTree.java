package shi.ds.tree;

public class BinaryTree<T extends Comparable<T>> {

	enum Order {
		preOrder, inOrder, postOrder
	}

	Node root;// 树根

	public Node getRoot(){
		return root;
	}
	// 方法
	public void insert(T data) {
		Node newNode = new Node(data);

		if (root == null) {
			root = newNode;
		} else {
			Node current = root;
			Node parent;
			while (true) {// 寻找插入的位置
				parent = current;
				if (data.compareTo(current.data) < 0) {
					current = current.left;
					if (current == null) {
						parent.left = newNode;
						return;
					}
				} else {
					current = current.right;
					if (current == null) {
						parent.right = newNode;
						return;
					}
				}
			}
		}
	}

	public Node find(T data) {
		Node current = root;
		while (!current.data.equals(data)) {
			if (current.data.compareTo(data) < 0) {
				current = current.right;
			} else {
				current = current.left;
			}
			if (current == null)
				return null;
		}
		return current;
	}

	// 删除某个节点
	public boolean delete(T key) {
		// 先找到需要删除的节点
		Node current = root;
		Node parent = root;
		boolean isLeftChild = false;

		while (!current.data.equals(key))// 显然，当current.iData == key 时，current
											// 就是要找的节点
		{
			parent = current;
			if (key.compareTo(current.data) < 0) {
				isLeftChild = true;
				current = current.left;
			} else {
				isLeftChild = false;
				current = current.right;
			}
			if (current == null)// 找不到key时返回false
				return false;
		}

		// 分情况考虑删除的节点
		// 删除的节点为叶节点时
		if (current.left == null && current.right == null) {
			if (current == root)
				root = null;
			else if (isLeftChild)
				parent.left = null;
			else
				parent.right = null;
		}
		// 删除的节点有一个子节点
		else if (current.right == null)// 删除的节点只有一个左子节点时
		{
			if (current == root)// 要删除的节点为根节点
				root = current.left;
			else if (isLeftChild)// 要删除的节点是一个左子节点
				parent.left = current.left;
			else
				parent.right = current.left;// 要删除的节点是一个右子节点
		} else if (current.left == null)// 删除的节点只有一个右子节点时
		{
			if (current == root)// 要删除的节点为根节点
				root = current.right;
			else if (isLeftChild)// 要删除的节点是一个左子节点
				parent.left = current.right;
			else
				parent.right = current.right;// 要删除的节点是一个右子节点
		}
		// 删除的节点有两个子节点
		else {
			Node successor = getSuccessor(current);// 找到后继节点
			if (current == root)
				root = successor;
			else if (isLeftChild)
				parent.left = successor;
			else
				parent.right = successor;
			successor.left = current.left;
		}
		return true;
	}

	// 返回后继节点
	private Node getSuccessor(Node delNode) {
		Node successorParent = delNode;// 后继节点的父节点
		Node successor = delNode;// 后继节点
		Node current = delNode.right;// 移动到位置节点位置
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.left;
		}
		if (successor != delNode.right) {
			successorParent.left = successor.right;
			successor.right = delNode.right;
		}
		return successor;
	}

	public void printTree(Node root, Order order) {
		if (root == null) {
			return;
		}
		if (order.equals(Order.preOrder)) {
			System.out.println(root.data.toString());
		}
		if (root.left != null)
			printTree(root.left, order);
		if (order.equals(Order.inOrder)) {
			System.out.println(root.data.toString());
		}
		if (root.right != null)
			printTree(root.right, order);
		if (order.equals(Order.postOrder)) {
			System.out.println(root.data.toString());
		}
	}

	public class Node {
		public T data;
		public Node left;
		public Node right;

		public Node(T data) {
			this.data = data;
		}
	}
}
