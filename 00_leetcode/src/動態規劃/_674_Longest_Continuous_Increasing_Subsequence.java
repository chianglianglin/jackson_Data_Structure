package 動態規劃;

public class _674_Longest_Continuous_Increasing_Subsequence {

	public static void main(String[] args) {
		System.out.println(findLengthOfLCIS1(new int[]{4,12,12,3,1,45,67,2,90,7,10}));
	}

	//這題根300LIS很像 只是變了條件

	/**
	 * 動態規劃(O(n))
	 * @param nums
	 * @return
	 */
	static int findLengthOfLCIS1(int[] nums){
		if (nums == null || nums.length == 0)return 0;
		//當前i的值跟前面一個值比較就可以
		//當前面一個值比當前值小才拿來加1
		//當前的i 假設1 比較大小 取大
		//假設dp[] 長度為nums.length
		int[] dp = new int[nums.length];
		//直接把dp[]第一個設為1
		dp[0] = 1;
		//設全部最大值
		int max = dp[0];
		//從第二個開始便利
		for (int i = 1; i < nums.length; i++) {
			dp[i] = 1;
			if (nums[i - 1] < nums[i]){

				dp[i] = Math.max(dp[i],dp[i - 1] + 1);
			}
			max = Math.max(max,dp[i]);
		}
		return max;
	}

	//滑動窗口
	static int findLengthOfLCIS2(int[] nums){
		int maxlen  = 0;
		int count = 1;
		int len = nums.length;
		//为空的情况
		if (len == 0) {
			return 0;
		}
		for (int i = 0; i < len-1; ++i) {
			//递增时
			if (nums[i] < nums[i+1]) {
				count++;
				continue;
			}
			//发现递减情况，则窗口长度变为 1，并修改 maxlen
			maxlen = Math.max(count,maxlen);
			count = 1;

		}
		//返回最大值，有可能完全递增到结尾处，然后没有对 maxlen 赋值
		return Math.max(count,maxlen);
	}
}
