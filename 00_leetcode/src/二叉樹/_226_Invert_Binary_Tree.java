package 二叉樹;

public class _226_Invert_Binary_Tree {
	public TreeNode invertTree(TreeNode root) {
		// base case
		if (root == null) {
			return null;
		}

		/**** 前序遍历位置 ****/
		// root 节点需要交换它的左右子节点
		TreeNode tmp = root.left;
		root.left = root.right;
		root.right = tmp;

		// 让左右子节点继续翻转它们的子节点
		invertTree(root.left);
		invertTree(root.right);

		return root;
	}
}
