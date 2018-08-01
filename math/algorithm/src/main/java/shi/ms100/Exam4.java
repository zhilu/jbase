package shi.ms100;

import shi.ds.tree.BinaryTree;
import shi.ds.tree.BinaryTree.Node;

/**
 * 在二叉树中寻找和为某一值的路径
 * @author shi
 *
 */
public class Exam4 {
	
	public static void main(String[] args) {
		BinaryTree<Integer> bt = new BinaryTree<>();
		bt.insert(10);
		bt.insert(5);
		bt.insert(4);
		bt.insert(7);
		bt.insert(12);
		findSumValue("", bt.getRoot(), 22);
		
		
	}
	
	public static void findSumValue(String last,Node node,int v){
		if(node.data.equals(v)){
			System.out.println(last.substring(1)+","+v);
			return;
		}
		if(node.left != null){
			findSumValue(last+","+node.data.toString(), node.left, v-Integer.parseInt(node.data.toString()));
		}
		if(node.right != null){
			findSumValue(last+","+node.data.toString(), node.right, v-Integer.parseInt(node.data.toString()));
		}
		
	}
}
