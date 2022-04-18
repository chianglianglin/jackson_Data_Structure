package 二叉樹;

import java.util.ArrayList;
import java.util.List;

public class _94_Binary_Tree_Inorder_Traversal {
	public List<Integer> inorderTraversal(TreeNode root) {

		ArrayList<Integer> list = new ArrayList<>();
		inorder(root,list);
		return list;

	}
	private void inorder(TreeNode node,ArrayList<Integer> list){
		if (node == null)return;
		inorder(node.left,list);
		list.add(node.val);
		inorder(node.right,list);
	}
}
