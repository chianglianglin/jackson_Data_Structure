package 數組;

public class _121_Best_Time_to_Buy_and_Sell_Stock {

	public int maxProfit1(int[] prices) {
		if (prices == null || prices.length == 0) return 0;
		//前面掃描過的最小價格
		int minBuy = prices[0];
		int maxProfit = 0;
		//掃描所有價格
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < minBuy) {
				//不用賣出股票 更新最小價格
				minBuy = prices[i];
			} else {
				//賣出股票 算出利潤
//				int profit = prices[i] - minBuy;
//				if (profit > maxProfit)maxProfit = profit;
				maxProfit = Math.max(prices[i] - minBuy, maxProfit);
			}
		}
		return maxProfit;

	}

	public int maxProfit2(int[] prices) {
		if (prices == null || prices.length == 0) return 0;
		int[] newPrices = new int[prices.length - 1];
		for (int i = 1; i < prices.length; i++) {
			newPrices[i - 1] = prices[i] - prices[i - 1];
		}
		if (newPrices == null || newPrices.length == 0) return 0;
		int[] dp = new int[newPrices.length];
		int max = Math.max(0,dp[0] = newPrices[0]);

		for (int i = 1; i < newPrices.length; i++) {
			if (dp[i - 1] < 0) {
				dp[i] = newPrices[i];
			} else {
				dp[i] = dp[i - 1] + newPrices[i];
			}
			max = Math.max(max, dp[i]);
		}

		return max;

	}
}
