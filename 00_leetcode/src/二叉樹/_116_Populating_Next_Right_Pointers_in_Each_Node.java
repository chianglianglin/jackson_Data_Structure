package 二叉樹;

public class _116_Populating_Next_Right_Pointers_in_Each_Node {

	public Node connect(Node root) {
		if (root == null) return null;
		connectRightSide(root.left, root.right);
		return root;
	}

	void connectRightSide(Node left, Node right) {
		if (left == null || right == null) {
			return;
		}
		//後序遍歷
		left.next = right;
		connectRightSide(left.left, left.right);
		connectRightSide(right.left, right.right);
		connectRightSide(left.right, right.left);
	}
}
