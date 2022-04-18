package 二叉樹;

public class _96_Unique_Binary_Search_Trees {
//	public int numTrees(int n) {
//		return count(1,n);
//	}
//
//	private int count(int lo, int hi){
//		//base case
//		if (lo > hi){
//			return 1;
//		}
//		int res= 0;
//		for (int i = lo; i <= hi; i++) {
//			int left = count(lo, i - 1);
//			int right = count(i + 1,hi);
//			res += left * right;
//		}
//		return res;
//	}
int[][] memo;

	int numTrees(int n) {
		// 备忘录的值初始化为 0
		memo = new int[n + 1][n + 1];
		return count(1, n);
	}

	int count(int lo, int hi) {
		if (lo > hi) return 1;
		// 查备忘录
		if (memo[lo][hi] != 0) {
			return memo[lo][hi];
		}

		int res = 0;
		for (int mid = lo; mid <= hi; mid++) {
			int left = count(lo, mid - 1);
			int right = count(mid + 1, hi);
			res += left * right;
		}
		// 将结果存入备忘录
		memo[lo][hi] = res;

		return res;
	}
}
