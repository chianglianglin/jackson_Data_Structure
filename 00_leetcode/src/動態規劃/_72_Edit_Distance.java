package 動態規劃;

public class _72_Edit_Distance {
	public int minDistance1(String word1, String word2) {
		if (word1 == null || word1 == null)return 0;
		char[] c1 = word1.toCharArray();
		char[] c2 = word2.toCharArray();
		int[][] dp = new int[c1.length + 1][c2.length +1];
		dp[0][0] = 0;
		for (int i = 1; i <= c1.length; i++) {
			dp[0][i] = i;
		}
		for (int i = 1; i <= c2.length; i++) {
			dp[i][0] = i;
		}
		for (int i = 1; i < c1.length; i++) {
			for (int j = 1; j < c2.length; j++) {
				int top = dp[i - 1][j] + 1;
				int left = dp[i][j - 1] + 1;
				int leftTop = dp[i - 1][j - 1];
				if (c1[i - 1] != c2[j - 1]){
					leftTop++;
				}

				dp[i][j] = Math.min(Math.min(top,left),leftTop);
			}
		}

		return dp[c1.length][c2.length];
	}


	//一維數組解法 這個跟_1143_Longest_Common_Subsequence思路相同但是細節差很多
	//要注意
	public int minDistance2(String word1, String word2) {
		if (word1 == null || word2 == null)return 0;

		char[] c1 = word1.toCharArray();
		char[] c2 = word2.toCharArray();
		if(c1.length < c2.length){
			char[] temp = c1;
			c1 = c2;
			c2  = temp;
		}
		int[] dp = new int[c1.length + 1];
		dp[0] = 0;
		for (int i = 1; i <= c1.length; i++) {
			dp[i] = i;
		}
		for (int i = 1; i <=c2.length; i++) {
			int c=i - 1;
			dp[0] = c;//這一步我想了一個小時很累喔要注意
			// 要把j == 0那column填滿 不能填零
			for (int j = 1; j <= c1.length; j++) {
				int leftTop = c;
				c = dp[j];
				int top = dp[j] + 1;
				int left = dp[j - 1] + 1;
				if (c1[j - 1] != c2[i - 1]){
					leftTop++;
				}

				dp[j] = Math.min(Math.min(top,left),leftTop);
			}
		}

		return dp[c1.length];
	}


}
