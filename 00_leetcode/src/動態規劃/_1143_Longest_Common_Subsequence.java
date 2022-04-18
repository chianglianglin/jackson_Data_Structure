package 動態規劃;

public class _1143_Longest_Common_Subsequence{
	public static void main(String[] args) {
		int len = lcs3(new int[]{1,4,5,9,10}, new int[]{1,6,7,4,9,10});
		System.out.println(len);
	}

	static int longestCommonSubsequence(String text1, String text2) {
		if (text1 == null || text2 == null)return 0;
		char[] chars1 = text1.toCharArray();
		char[] chars2 = text2.toCharArray();

		if (chars1.length == 0)return 0;
		if (chars2.length == 0)return 0;
		//可以優化: dp數組長度我目前沒有 老師有優化
		int[] dp = new int[chars2.length + 1];
		for (int i = 1; i <= chars1.length; i++) {
//			int leftTop=0; 不能這樣 這是給left值 17和27行有關
			int cur = 0;//注意去看圖
			for (int j = 1; j <= chars2.length; j++) {
				int leftTop = cur;//注意去看圖
				cur = dp[j];//注意去看圖給予下次的leftTop使用
				if (chars1[i - 1] == chars2[j -1]){
					dp[j] = leftTop + 1;
				}else{
					dp[j] = Math.max(dp[j],dp[j-1]);
				}
//				leftTop = dp[j]; 不能這樣 這是給left值
			}
		}
		return dp[chars2.length];
	}


	static int lcs3(int[]nums1, int[]nums2){
//		String text1 ="test";
//		char[] nums1 = text1.toCharArray();
//		char[] nums2 = text2.toCharArray();
//		chars[1] == chars[2]
		if (nums1 == null || nums1.length == 0)return 0;
		if (nums2 == null || nums2.length == 0)return 0;
		int[] dp = new int[nums2.length + 1];
		for (int i = 1; i <= nums1.length; i++) {
			int leftTop=0;
			for (int j = 1; j <= nums2.length; j++) {
				if (nums1[i - 1] == nums2[j -1]){
					dp[j] = leftTop + 1;
				}else{
					dp[j] = Math.max(dp[j],dp[j-1]);
				}
				leftTop = dp[j];
			}
		}
		return dp[nums2.length];
	}
	/**
	 * 滾動數組
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	static int lcs1(int[]nums1, int[]nums2){
		if (nums1 == null || nums1.length == 0)return 0;
		if (nums2 == null || nums2.length == 0)return 0;
		int[][] dp = new int[2][nums2.length + 1];
		for (int i = 1; i <= nums1.length; i++) {
			int row = i % 2;
			int prevRow = (i - 1) % 2;
			for (int j = 1; j <= nums1.length; j++) {
				if (nums1[i - 1] == nums2[j -1]){
					dp[row][j] = dp[prevRow][j - 1] + 1;
				}else{
					dp[row][j] = Math.max(dp[prevRow][j],dp[row][j-1]);
				}
			}
		}
		return dp[nums1.length % 2][nums2.length];
	}

	/**
	 * 動態規劃二維數組
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	static int lcs2(int[]nums1, int[]nums2){
		if (nums1 == null || nums1.length == 0)return 0;
		if (nums2 == null || nums2.length == 0)return 0;
		int[][] dp = new int[nums1.length + 1][nums2.length + 1];
		for (int i = 1; i <= nums1.length; i++) {
			for (int j = 1; j <= nums1.length; j++) {
				if (nums1[i - 1] == nums2[j -1]){
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}else{
					dp[i][j] = Math.max(dp[i -1][j],dp[i][j-1]);
				}
			}
		}
		return dp[nums1.length][nums2.length];
	}
}
