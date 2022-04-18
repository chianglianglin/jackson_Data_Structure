package 二叉樹;

public class _230_Kth_Smallest_Element_in_a_BST {

	int res = 0;
	int rank = 0;
	public int kthSmallest(TreeNode root, int k) {
		traverse(root,k);
		return res;
	}

	void traverse(TreeNode root,int k){
		if (root == null){
			return;
		}
		traverse(root.left,k);

		rank++;
		if (rank == k){
			res = root.val;
			return;
		}

		traverse(root.right,k);

	}
}
