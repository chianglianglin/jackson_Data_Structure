package 二叉樹;

public class _114_Flatten_Binary_Tree_to_Linked_List {
	public void flatten(TreeNode root) {
		flatten(root.left);
		flatten(root.right);
		//後序遍歷
		//假設左右節點都是成為一條鏈表
		TreeNode left = root.left;
		TreeNode right = root.right;
		root.left = null;
		root.right = left;

		TreeNode p = root;
		while (p.right != null){
			p = p.right;
		}
		p.right = right;
	}
}
