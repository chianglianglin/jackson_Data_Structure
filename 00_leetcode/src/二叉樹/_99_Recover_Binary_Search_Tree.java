package 二叉樹;

import apple.laf.JRSUIUtils;

public class _99_Recover_Binary_Search_Tree {
	//使用中序遍歷
	//上一次中序遍歷過的節點
	private TreeNode prev;
	//第一個錯誤節點
	private TreeNode first;
	//第二個錯誤節點
	private TreeNode second;

	/**
	 * root是一顆錯誤的二叉搜索樹(有兩個節點被錯誤交換)
	 * @param root
	 */
	public void recoverTree1(TreeNode root) {
			findWrongNodes(root);
			int tmp = first.val;
			first.val = second.val;
			second.val = tmp;
	}
	private void findWrongNodes(TreeNode root){
		if (root == null)return;
		findWrongNodes(root.left);
		//中序遍歷
		find(root);

		findWrongNodes(root.right);
	}
	private void find(TreeNode node){
		//出現了逆序對
		if (prev != null && prev.val > node.val){
			//第2個錯誤節點: 最後一個逆序段中較小的那個節點
			second = node;
			//第1個錯誤節點:第一個逆序段中較大的哪個節點
			if (first != null)return;
			first = prev;
		}
		prev = node;
	}

	//Morris
	public void recoverTree2(TreeNode root) {
		TreeNode node = root;
		while (node != null) {
			if (node.left != null) {
				//找前驅節點
				TreeNode pred = node.left;
				while (pred.right != null && pred.right != node) {
					pred = pred.right;
				}
				//第一次找到前驅節點後把right復植node
				if (pred.right == null) {
					pred.right = node;
					node = node.left;
				} else {
					//第二次發現pred.right != null
					//打印後復值為null 為了恢復整個架構
//					System.out.print(node.val + " ");
					find(node);
					pred.right = null;
					node = node.right;
				}
			} else {
//				System.out.print(node.val + " ");
				find(node);
				node = node.right;
			}
		}
		int tmp = first.val;
		first.val = second.val;
		second.val = tmp;
	}
}
