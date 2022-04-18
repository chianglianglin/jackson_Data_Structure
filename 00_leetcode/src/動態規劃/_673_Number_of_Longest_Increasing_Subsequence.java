package 動態規劃;

public class _673_Number_of_Longest_Increasing_Subsequence {

	//二分搜索我沒有去做 我只做了O(n^2)
	public int findNumberOfLIS1(int[] nums) {
		if (nums == null || nums.length == 0)return 0;
		int len = nums.length,maxLen = 0,ans = 0;

		int[] dp = new int[len];
		int[] count = new int[len];
		for (int i = 0; i < len; i++) {
			dp[i] = 1;
			count[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]){
					if (dp[j] + 1 > dp[i]){
						dp[i] = dp[j] + 1;
						count[i] = count[j];//重新計數
					}else if (dp[j] + 1 == dp[i]){
						count[i] += count[j];//疊加上去
					}
				}
			}
			if (dp[i] > maxLen){
				maxLen = dp[i];
				ans = count[i];
			}else if(dp[i] == maxLen){
				ans += count[i];
			}

		}
		return ans;
	}

	static int findNumberOfLIS2(int[] nums) {
		if (nums == null || nums.length == 0)return 0;
		//牌堆的數量
		int len = 0;
		int maxCount = 0;
		//牌頂數組
		int[] top = new int[nums.length];
		int[] count = new int[nums.length];
		//遍歷所有的牌
		for (int num:nums) {
			int j = 0;
			while (j < len){
				//找到一個>=num的牌頂
				if (top[j] > num){
					top[j] = num;
					count[j]++;
					break;
				}
				j++;
			}
			if (j == len) {
				len++;
				top[j] = num;
				count[j]++;
			}
		}

		return count[len];
	}
}
