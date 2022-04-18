package 二叉樹;

public class _538_Convert_BST_to_Greater_Tree {
	int sum = 0;
	public TreeNode convertBST(TreeNode root) {
		if (root == null){
			return root;
		}
			//倒敘中序遍歷
		convertBST(root.right);
		sum += root.val;
		root.val = sum;

		convertBST(root.left);
		return root;
	}
}
