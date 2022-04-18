package 隊列;

import java.util.Deque;
import java.util.LinkedList;

public class _239_Sliding_Window_Maximum {
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || nums.length == 0 || k < 1)return new int[0];
		if (k == 1)return nums;
		int[] maxes = new int[nums.length - k + 1];
		Deque<Integer> deque = new LinkedList<>();
		//遍歷整個array
		for (int i = 0; i < nums.length; i++) {
			//只要nums[隊列尾] <= nums[i] 刪除隊列尾
			while(!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]){
				deque.pollLast();
			}
			//把i加入隊尾
			deque.offerLast(i);
			//檢查窗口的索引是否合法
			int w = i - (k - 1);
			if (w < 0)continue;
			if (deque.peekFirst() < w){
				deque.pollFirst();
			}
			//設置窗口的最大值
			maxes[w] = nums[deque.peekFirst()];
		}
		return maxes;
	}
}
