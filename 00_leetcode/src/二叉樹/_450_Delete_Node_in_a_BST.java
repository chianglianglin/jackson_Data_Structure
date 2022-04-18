package 二叉樹;

public class _450_Delete_Node_in_a_BST {
	//funtion主要功能是變動參數是root(傳入的跟節點) ,不變參數key,回傳值是經過刪除後的root
	//跟節點
	public TreeNode deleteNode(TreeNode root, int key) {
			//框架
//		if (root.val == key){
//			//進行刪除
//		}else if(key < root.val){
//			//繼續往左邊找
//			root.left = deleteNode(root.left,key);
//		}else if (key > root.val){
//			root.right = deleteNode(root.right,key);
//		}
		//可以去看參考資料   https://labuladong.gitee.io/algo/2/19/27/
		if (root == null)return null;
		if (root.val == key){
			//進行刪除
			//兩種情況 1.兩邊都沒有 2.其中一邊有
			if (root.right == null) return root.left;
			if (root.left == null) return root.right;
			//第三種情況 3.兩邊都有both side
			//找出右邊子樹最小的值(後繼節點)
			TreeNode minNode = getMin(root.right);
			//刪除右子樹最小值
			root.right = deleteNode(root.right,minNode.val);
			//替換工作
			minNode.right = root.right;
			minNode.left = root.left;
			root = minNode;
		}else if(key < root.val){
			//繼續往左邊找
			root.left = deleteNode(root.left,key);
		}else if (key > root.val){
			root.right = deleteNode(root.right,key);
		}

		return root;

	}

	private TreeNode getMin(TreeNode node){
		while (node.left != null){
			node = node.left;
		}
		return node;
	}
}
