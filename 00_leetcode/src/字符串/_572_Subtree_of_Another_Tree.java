package 字符串;

public class _572_Subtree_of_Another_Tree {
	public boolean isSubtree(TreeNode root, TreeNode subRoot) {
		if (root == null || subRoot == null)return false;
		String rootString = postSerialize(root);
		String subRootString = postSerialize(subRoot);
		return rootString.contains(subRootString);
	}
	private String postSerialize(TreeNode root){
		StringBuilder sb = new StringBuilder();
		postSerialize(root,sb);
		return sb.toString();
	}

	//遞歸使用序列化
	private void postSerialize(TreeNode node,StringBuilder sb){
		if (node.left == null){
			sb.append("#!");
		}else{
			postSerialize(node.left,sb);
		}

		if (node.right == null){
			sb.append("#!");
		}else{
			postSerialize(node.right,sb);
		}

		sb.append(node.val).append("!");
	}
}
