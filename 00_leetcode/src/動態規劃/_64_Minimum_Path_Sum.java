package 動態規劃;

public class _64_Minimum_Path_Sum {
	public int minPathSum(int[][] grid) {
		int rows = grid.length;
		int cols = grid[0].length;
		int[] dp = new int [cols];
		dp[0] = grid[0][0];
		for(int i = 1; i< dp.length;i++){
			dp[i] = dp[i -1] + grid[0][i];
		}
		for(int row=1; row < rows; row++){
			dp[0] = dp[0] + grid[row][0];
			for(int col = 1; col < cols; col++){
				dp[col] = Math.min(dp[col- 1],dp[col]) +grid[row][col];
			}
		}
		return dp[cols - 1];

	}
}
