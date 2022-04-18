package 數組;

public class _122_Best_Time_to_Buy_and_Sell_Stock_II {
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0)return 0;

		//前一個最小值
		int preMinBuy = prices[0];
		int totalProfit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < preMinBuy){
				preMinBuy = prices[i];
			}else{
				int profit = prices[i] - preMinBuy;
				totalProfit += profit;
				preMinBuy = prices[i];
			}
		}
		return totalProfit;
	}
}
