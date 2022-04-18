package 動態規劃;

public class _62_Unique_Paths {
	public int uniquePaths1(int m, int n) {
		if (m <=0 || n <=0)return 0;
		if (m==1 &&  n==1)return 1;
		int[][] dp = new int[m][n];
		dp[0][0] = 1;
		for (int i = 1; i < n; i++) {
			dp[0][i] = 1;
		}
		for (int i = 1; i < m; i++) {
			dp[i][0] = 1;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j - 1];
			}
		}
		return dp[m -1][n -1];
	}

	public int uniquePaths2(int m, int n) {
		if (m <= 0 || n <= 0) return 0;
		if (m == 1 && n == 1) return 1;
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
		}
		for (int i = 1; i < m; i++) {
			dp[0] = 1;
			for (int j = 1; j < n; j++) {
				dp[j] = dp[j -1] + dp[j];
			}
		}
		return dp[n - 1];

	}
}
