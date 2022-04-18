package 二叉樹;

public class _105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
			return build(preorder,0,preorder.length - 1,
					inorder,0,inorder.length - 1);
	}

	private TreeNode build(int[]preorder,int preStart,int preEnd,
						   int[]inorder,int inStart,int inEnd){
		if (preStart > preEnd){
			return null;
		}
		int rootVal = preorder[preStart];
		int index = 0;
		for (int i=inStart;i <= inEnd;i++){
			if (inorder[i] == rootVal){
				index = i;
				break;
			}
		}
		int leftSize = index - inStart;
		TreeNode root = new TreeNode(rootVal);
		root.left = build(preorder,preStart + 1,preStart + leftSize,
				inorder,inStart,index - 1);
		root.right = build(preorder,preStart + leftSize + 1,preEnd,
				inorder,index + 1,inEnd);
		return root;
	}
}
