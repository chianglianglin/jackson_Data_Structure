package 動態規劃;

public class _213_House_Robber_II {

	//這題最精彩的地方是採用198也就是直接把第一個版本當成方法
	//改變邊界條件
	public int rob(int[] nums) {
		if (nums == null || nums.length == 0)return 0;
		if (nums.length == 1){
			return nums[0];
		}
		//只能寫成[start,end]左閉右閉 所以要判斷length == 2
		if (nums.length == 2){
			return Math.max(nums[0],nums[1]);
		}
		//1.第一個選最後一個不選 2.第一個不選最後一個選
		return Math.max(robRange(nums,0,nums.length - 2),robRange(nums,1,nums.length - 1));
	}
	private int robRange(int[]nums,int start,int end){
		int first = nums[start];
		int second = Math.max(nums[start],nums[start + 1]);
		for (int i = start + 2; i <= end; i++) {
			int temp = second;
			second = Math.max(nums[i] + first,second);
			first = temp;
		}
		return second;
	}

	//方法一：动态规划 可以直接去leetcode-cn看解說很詳細
	//首先考虑最简单的情况。如果只有一间房屋，则偷窃该房屋，可以偷窃到最高总金额。如果只有两间房屋，则由于两间房屋相邻，不能同时偷窃，只能偷窃其中的一间房屋，因此选择其中金额较高的房屋进行偷窃，可以偷窃到最高总金额。
	//
	//注意到当房屋数量不超过两间时，最多只能偷窃一间房屋，因此不需要考虑首尾相连的问题。如果房屋数量大于两间，就必须考虑首尾相连的问题，第一间房屋和最后一间房屋不能同时偷窃。
	//
	//如何才能保证第一间房屋和最后一间房屋不同时偷窃呢？如果偷窃了第一间房屋，则不能偷窃最后一间房屋，因此偷窃房屋的范围是第一间房屋到最后第二间房屋；如果偷窃了最后一间房屋，则不能偷窃第一间房屋，因此偷窃房屋的范围是第二间房屋到最后一间房屋。
	//
	//假设数组 nums 的长度为 nn。如果不偷窃最后一间房屋，则偷窃房屋的下标范围是 [0, n-2][0,n−2]；如果不偷窃第一间房屋，则偷窃房屋的下标范围是 [1, n-1][1,n−1]。在确定偷窃房屋的下标范围之后，即可用第 198 题的方法解决。对于两段下标范围分别计算可以偷窃到的最高总金额，其中的最大值即为在 nn 间房屋中可以偷窃到的最高总金额。
	//
	//假设偷窃房屋的下标范围是 [\textit{start},\textit{end}][start,end]，用 \textit{dp}[i]dp[i] 表示在下标范围 [\textit{start},i][start,i] 内可以偷窃到的最高总金额，那么就有如下的状态转移方程：
	//
	//\textit{dp}[i] = \max(\textit{dp}[i-2]+\textit{nums}[i], \textit{dp}[i-1])
	//dp[i]=max(dp[i−2]+nums[i],dp[i−1])
	//
	//边界条件为：
	//
	//\begin{cases} \textit{dp}[\textit{start}] = \textit{nums}[\textit{start}] & 只有一间房屋，则偷窃该房屋 \\ \textit{dp}[\textit{start}+1] = \max(\textit{nums}[\textit{start}], \textit{nums}[\textit{start}+1]) & 只有两间房屋，偷窃其中金额较高的房屋 \end{cases}
	//{
	//dp[start]=nums[start]
	//dp[start+1]=max(nums[start],nums[start+1])
	//​
	//
	//只有一间房屋，则偷窃该房屋
	//只有两间房屋，偷窃其中金额较高的房屋
	//​
	//
	//
	//计算得到 \textit{dp}[\textit{end}]dp[end] 即为下标范围 [\textit{start},\textit{end}][start,end] 内可以偷窃到的最高总金额。
	//
	//分别取 (\textit{start},\textit{end})=(0,n-2)(start,end)=(0,n−2) 和 (\textit{start},\textit{end})=(1,n-1)(start,end)=(1,n−1) 进行计算，取两个 \textit{dp}[\textit{end}]dp[end] 中的最大值，即可得到最终结果。
	//
	//根据上述思路，可以得到时间复杂度 O(n)O(n) 和空间复杂度 O(n)O(n) 的实现。考虑到每间房屋的最高总金额只和该房屋的前两间房屋的最高总金额相关，因此可以使用滚动数组，在每个时刻只需要存储前两间房屋的最高总金额，将空间复杂度降到 O(1)O(1)。
	//
}
