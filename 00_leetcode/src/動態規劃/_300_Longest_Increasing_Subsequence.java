package 動態規劃;

public class _300_Longest_Increasing_Subsequence {
	public static void main(String[] args) {
		System.out.println(lengthOfLIS1(new int[]{4,12,12,3,1,45,67,2,90,7,10}));
	}

	/**
	 * 動態規劃(O(n^2))
	 * @param nums
	 * @return
	 */
	static int lengthOfLIS1(int[] nums){
		if (nums == null || nums.length == 0)return 0;
		//i 當前元素下標 , j 當前元素之前的元素的下標
		//nums[i] <= nums[j] continue ,
		// nums[i] > nums[j]  j的最大increasing subsequence + 1 和
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
			for(int j = 0; j < i; j++){
				if (nums[i] <= nums[j])continue;
				dp[i] = Math.max(dp[i],dp[j] + 1);
			}
			max = Math.max(max,dp[i]);
		}
		return max;
	}

	/**
	 * 牌頂方式(正常版:O(n^2))
	 * @param nums
	 * @return
	 */
	static int lengthOfLIS2(int[] nums) {
		if (nums == null || nums.length == 0)return 0;
		//牌堆的數量
		int len = 0;
		//牌頂數組
		int[] top = new int[nums.length];
		//遍歷所有的牌
		for (int num:nums) {
			int j = 0;
			while (j < len){
				//找到一個>=num的牌頂
				if (top[j] >= num){
					top[j] = num;
					break;
				}
				j++;
			}
			if (j == len) {
				len++;
				top[j] = num;
			}
		}
		return len;
	}

	/**
	 * 牌頂方法(二分搜索O(nlogn))
	 * @param nums
	 * @return
	 */
	static int lengthOfLIS3(int[] nums){
		if (nums==null || nums.length == 0)return 0;
		//牌堆數量
		int len =0;
		int[] top = new int[nums.length];
		//二分搜索
		for (int num : nums){
			int begin = 0;
			int end = len;
			while (begin < end){
				int mid = (begin + end) >>1;
				if (num <= top[mid]){
					end = mid;
				}else{
					begin = mid +1;
				}
			}
			//覆蓋牌頂
			top[begin] = num;
			//檢查是否要新建一個牌堆
			if (begin == len)len++;
		}
		return len;
	}
}
