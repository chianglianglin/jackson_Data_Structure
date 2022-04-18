package 數組;

public class _123_Best_Time_to_Buy_and_Sell_Stock_III {
	public int maxProfit(int[] prices) {

		//這題需要動態規劃裡的滾動數組來解決才行 不能沿用第二題方法

		if (prices == null || prices.length == 0)return 0;
		//前一個可能可以買的最小值
		int preMinBuy = prices[0];
		//第一,二大的交易
		int firstProfit = 0;
		int secondProfit = 0;
		//總利潤
//		int totalProfit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < preMinBuy){
				preMinBuy = prices[i];
			}else{
				int profit = prices[i] - preMinBuy;
				if (profit > firstProfit){
					firstProfit = profit;
				}else if (profit > secondProfit){
					secondProfit = profit;
				}
				preMinBuy = prices[i];
			}
		}
//		totalProfit = firstProfit + secondProfit;
		return firstProfit + secondProfit;
	}
}
