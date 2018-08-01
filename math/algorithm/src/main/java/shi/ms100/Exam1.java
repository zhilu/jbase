package shi.ms100;

import shi.ds.tree.BinaryTree;
import shi.ds.tree.BinaryTree.Node;

/**
 * 输入一棵二元查找树，将该转换成个排序的双向链表。
 * 要求不能创建任何新的结点，只调整指针的指向
 * @author shi
 *
 */
public class Exam1 {
	public static void main(String[] args) {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.insert(10);
		bt.insert(6);
		bt.insert(4);
		bt.insert(8);
		bt.insert(14);
		bt.insert(12);
		bt.insert(16);

		Node node = reform(bt.getRoot(), true);

		while (null != node) {
			System.out.println(node.data);
			node = node.right;
		}

	}

	public static Node reform(Node root, boolean isLeft) {
		if (null != root.left) {
			Node right = getRightNode(root.left);
			root.left = right;
			right.right = root;
		}
		if (null != root.right) {
			Node left = getLeftNode(root.right);
			root.right = left;
			left.left = root;
		}

		Node node = root;
		if (isLeft) {
			while (null != node && null != node.left) {
				node = node.left;
			}
		} else {
			while (null != node && null != node.right) {
				node = node.right;
			}
		}
		return node;
	}

	public static Node getLeftNode(Node node) {
		if (isLeaf(node)) {
			return node;
		}
		Node res = null;
		res = reform(node, true);
		return res;
	}

	public static Node getRightNode(Node node) {
		if (isLeaf(node)) {
			return node;
		}
		Node res = null;
		res = reform(node, false);
		return res;
	}

	public static boolean isLeaf(Node node) {
		return null == node.left && null == node.right;
	}
}
