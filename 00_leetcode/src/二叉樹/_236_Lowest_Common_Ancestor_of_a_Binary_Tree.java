package 二叉樹;


//這題是二叉樹 跟235平衡二叉樹不一樣
public class _236_Lowest_Common_Ancestor_of_a_Binary_Tree {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q)return root;

		TreeNode left = lowestCommonAncestor(root.left,p,q);
		TreeNode right = lowestCommonAncestor(root.right,p,q);

		if (left != null && right != null)return root;
		//if(left != null && right == null)
		//if(left == null && right != null)
		//if(left == null && right == null)
		return left != null?left:right;
	}
}
