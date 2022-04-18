package 二叉樹;

public class _98_Validate_Binary_Search_Tree {

	public boolean isValidBST(TreeNode root) {
			return isValid(root,null,null);
	}

	/**
	 *
	 * @param root
	 * @param max 最大的節點
	 * @param min 最小的節點
	 * @return
	 */
	public boolean isValid(TreeNode root,TreeNode max,TreeNode min){
		if (root == null) return true;
		//如果root.val 小於最小值 min false
		if (min != null && root.val <= min.val)return false;
		//如果root.val 大於最大值 max false
		if (max != null && root.val >= max.val)return false;

		return (isValid(root.left,root,min)&&isValid(root.right,max,root));
	}
}
