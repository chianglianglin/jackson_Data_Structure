package 動態規劃;

import java.util.Collections;

public class _53_Maximum_Subarray {


	public static void main(String[] args) {
		System.out.println(maxSubArray2(new int[] {-2,1,
		-3,4,-1,2,1,-5,4}));
	}

	static int maxSubArray1(int[] nums){
		if (nums == null || nums.length == 0)return 0;
		int[]dp = new int[nums.length];
		dp[0] = nums[0];
		int max= dp[0];
		for (int i = 1; i < nums.length; i++) {
			if (dp[i - 1] <= 0){
				dp[i] = nums[i];
			}else{
				dp[i] = nums[i] + dp[i - 1];
			}
			max = Math.max(dp[i], max);
			System.out.println("dp[" + i + "] = "+dp[i]);
		}
		return max;
	}
	static int maxSubArray2(int[]nums){
		 if (nums == null || nums.length == 0)return 0;
		 int dp = nums[0];
		 int max = dp;
		for (int i = 1; i < nums.length ; i++) {
			if (dp <= 0){
				dp = nums[i];
			}else{
				dp = nums[i] + dp;
			}
			max = Math.max(dp,max);
		}
		return max;
	}
}
