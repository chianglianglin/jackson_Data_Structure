package 動態規劃;

public class _322_Coin_Change {
	public int coinChange(int[] coins, int amount) {
		return 1;
	}

	//暴力遞歸 自頂向下
	static int coins(int n){
		if (n < 1) return Integer.MAX_VALUE;
		if (n == 25 || n == 20 || n == 5 || n == 1)return 1;
		int min1 = Math.min(coins(n - 25),coins(n - 20));
		int min2 = Math.min(coins(n - 5), coins(n - 1));
		return Math.min(min1,min2) + 1;
	}


	//記憶化 搜索
	static int coins2(int n){
		if(n < 1)return -1;
		int[] dp = new int[n + 1];
		//如果n=19;不需要下面這個
//		dp[1] = dp[5] = dp[20] = dp[25] = 1;
		int[] faces = {1,5,20,25};
		for (int face : faces){
			if (n < face)break;
			dp[face] = 1;
		}
		return coins2(n,dp);
	}
	static int coins2(int n,int[] dp){
		if (n < 1) return Integer.MAX_VALUE;
		if (dp[n] == 0) {

			int min1 = Math.min(coins2(n - 25,dp),coins2(n - 20,dp));
			int min2 = Math.min(coins2(n - 5,dp), coins2(n - 1,dp));
			dp[n] = Math.min(min1,min2) + 1;
		}
		return dp[n];
	}

	/**
	 * 遞歸(自底向上)
	 */

	static int coin3(int n){
		if (n < 1)return Integer.MAX_VALUE;
		int[] dp = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			int min = Integer.MAX_VALUE;
			if ( i >= 1)min = Math.min(dp[i - 1],min);
			if ( i >= 5)min = Math.min(dp[i - 5],min);
			if ( i >= 20)min = Math.min(dp[i - 20],min);
			if ( i >= 25)min = Math.min(dp[i - 25],min);
			dp[i] = min + 1;
		}
		return dp[n];
	}

	static int coins4(int n){
		if (n < 1) return -1;
		int[] dp = new int[n+1];
		//faces[i[ 是湊夠i分時最後選擇的那枚硬幣的面值
		int[] faces = new int[dp.length];
		for (int i = 1; i <= n; i++) {
			int min = Integer.MAX_VALUE;
			if ( i>=1 && dp[i - 1] < min){
				min = dp[i - 1];
				faces[i] = 1;
			}
			if (i >= 5 && dp[i - 5] < min){
				min = dp[i - 5];
				faces[i] = 5;
			}
			if (i >= 20 && dp[i-20] < min){
				min = dp[i - 20];
				faces[i] = 20;
			}
			if (i >= 25 && dp[i - 25] < min){
				min = dp[i - 25];
				faces[i] =25;
			}
		}
		print(faces, n);
		return dp[n];
	}
	static void print(int[] faces, int n){
		while (n > 0){
			System.out.println(faces[n]);
			n -= faces[n];
		}
	}

	static int coins4(int n,int[] faces){
		if (n < 1 || faces == null || faces.length == 0)return -1;
		int[] dp = new int[n + 1];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = -1;
		}
		for (int i = 1; i <= n; i++) {
			int min = Integer.MAX_VALUE;
			for(int face : faces){
				if (i < face || dp[i - face] < 0) continue;
				min = Math.min(dp[i - face], min);
			}
			if (min == Integer.MAX_VALUE){
				dp[i] = -1;
			}else{
				dp[i] = min + 1;
			}
			dp[i] = min + 1;
		}
		return dp[n];
	}

}
