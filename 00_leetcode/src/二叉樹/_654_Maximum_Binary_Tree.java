package 二叉樹;

import java.util.Stack;

public class _654_Maximum_Binary_Tree {
	public TreeNode constructMaximumBinaryTree(int[] nums) {
		if (nums == null) return null;
 		return findRoot(nums, 0 , nums.length);
	}


	/**
	 * 找出[L,R)範圍的根節點
	 * @param nums
	 * @param L
	 * @param R
	 * @return
	 */
	private TreeNode findRoot(int[] nums, int L, int R){
		if (L == R) return null;
		int maxIdx = L;
		for (int i = L + 1; i < R; i++) {
			if (nums[i] > nums[maxIdx]) maxIdx =i;
		}
		TreeNode root = new TreeNode(nums[maxIdx]);
		root.left = findRoot(nums,L,maxIdx);
		root.right = findRoot(nums,maxIdx + 1,R);
		return root;
	}

	public int[] parentIndexes(int[] nums){

		int[] lis = new int[nums.length];
		int[] ris = new int[nums.length];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < nums.length; i++) {
			ris[i] = -1;
			while (!stack.isEmpty() && nums[i] > nums[stack.peek()]){
				ris[stack.pop()] = i;
			}
			lis[i] = stack.empty() ? -1: stack.peek();
			stack.push(i);
		}
		int[] pis = new int[nums.length];

		for (int i = 0; i < pis.length; i++) {
			if (lis[i] == -1 && ris[i] == -1){
				pis[i] = -1;
				continue;
			}
			if (lis[i] == -1){
				pis[i] = ris[i];
			}else if (ris[i] == -1){
				pis[i] = lis[i];
			}else if (nums[lis[i]] < nums[pis[i]]){
				pis[i] = lis[i];
			}else {
				pis[i] =ris[i];
			}

		}
		return pis;
	}
}
