package 二叉樹;

public class _333_Largest_BST_Subtree {

	public int largestBSTSubtree(TreeNode root) {
		return (root == null) ? 0 : getInfo(root).size;
	}

	public info getInfo(TreeNode root){
		//自底向上統計bst信息：故使用後序遍歷
		info li = getInfo(root.left);

		info ri = getInfo(root.right);

		//兩大情況
		//第一種:以root為跟節點的二叉樹就是一顆BST,最大的BST就是其本身
		//這一種有四種情況
		/*
		① li != null && ri != null
				&& li.root == root.left && root.val > li.max
				&& ri.root == root.right && root.val < ri.min

        ② li != null && ri == null
				&& li.root == root.left && root.val > li.max

        ③ li == null && ri != null
				&& ri.root == root.right && root.val < ri.min

        ④ li == null && ri == null
				*/

		int leftBstSize = -1, rightBstSize = -1, max = root.val, min = root.val;
		if (li == null) {
			leftBstSize = 0;
		} else if (li.root == root.left && root.val > li.max) {
			leftBstSize = li.size;
			min = li.min;
		}

		if (ri == null) {
			rightBstSize = 0;
		} else if (ri.root == root.right && root.val < ri.min) {
			rightBstSize = ri.size;
			max = ri.max;
		}

		if (leftBstSize >= 0 && rightBstSize >= 0) {
			return new info(root, 1 + leftBstSize + rightBstSize, max, min);
		}

		//第二種:以root為跟節點的二叉樹並不是BST
		if (li != null && ri != null)return (li.size > ri.size)? li:ri;

		return (li != null)?li:ri;

	}



	private static class info {
		//跟節點
		public TreeNode root;
		//BST大小
		public int size;
		//BST最大值
		public int max;
		//BST最小值
		public int min;

		public info(TreeNode root,int size,int max,int min){
			this.root = root;
			this.size = size;
			this.max = max;
			this.min = min;
		}

	}
}
