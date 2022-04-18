package 二叉樹;

public class _700_Search_in_a_Binary_Search_Tree {
	public TreeNode searchBST(TreeNode root, int val) {
			if (root == null)return null;
			if (val < root.val){
				return searchBST(root.left,val);
			}
			if (val > root.val){
				return searchBST(root.right,val);
			}
			return root;
	}
}
