package 棧;

import java.util.Stack;

public class _739_Daily_Temperatures {
	public int[] dailyTemperatures1(int[] temperatures) {
		if (temperatures == null || temperatures.length == 0) return null;
		int[] result = new int[temperatures.length];
		for (int i = temperatures.length - 2; i >=0; i--) {
			int j = i + 1;
			while (true){
				//第一個版本
//				if (temperatures[i] < temperatures[j]){
//					result[i] = j - i;
//					break;
//				}else if ( result[j] == 0){
//					result[i] = 0;
//					break;
//				}else if ( temperatures[i] == temperatures[j]){
//					result[i] = result[j] + j - i;
//					break;
//				}else{
//					j = j + result[j];
//				}
				//第二個版本(效率較高)
				if (temperatures[i] < temperatures[j]){
					result[i] = j - i;
					break;
				}else if ( result[j] == 0){
					result[i] = 0;
					break;
				}
				j = j + result[j];

			}
		}
		return result;
	}

		public int[] dailyTemperatures2(int[] temperatures) {
		if(temperatures == null || temperatures.length == 0) return null;
		int[] result = new int[temperatures.length];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < temperatures.length; i++) {
			while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
				int index = i - stack.peek();
				result[stack.pop()] = index;
			}
			stack.push(i);
		}

		return result;


	}
}
