package com.mj;

public class _53_Maximum_Subarray {
	public int maxSubArray(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
		return maxSubArray2(nums,0,nums.length);
	}
	static int maxSubArray2(int[]nums,int begin,int end){
		if (end - begin < 2) return nums[begin];
		int mid = (begin + end) >> 1;
		int leftSum = 0;
		int leftMax = Integer.MIN_VALUE;
		for (int i = mid - 1; i >=begin; i--) {
			leftSum += nums[i];
			leftMax = Math.max(leftMax,leftSum);
		}
		int rightSum = 0;
		int rightMax = Integer.MIN_VALUE;
		for (int i = mid; i < end ; i++) {
			rightSum += nums[i];
			rightMax = Math.max(rightMax,rightSum);
		}

		return Math.max(rightMax + leftMax,Math.max(maxSubArray2(nums,begin,mid),maxSubArray2(nums,mid,end)));
	}

	static int maxSubArray0(int[] nums){
					if (nums == null || nums.length == 0)return 0;
			int max = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i; j < nums.length; j++) {
				//[i,j]
				int sum = 0;
				for (int k = i; k <= j; k++) {
					sum += nums[k];
				}
				max = Math.max(max,sum);
			}
		}
		return max;
	}

	static int maxSubArray1(int[]nums){
				if (nums == null || nums.length == 0) return 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			int sum = 0;
			for (int j = i; j < nums.length; j++) {
				//[i,j]
				sum +=nums[j];
				max = Math.max(max, sum);
			}
		}
		return max;
	}
}
