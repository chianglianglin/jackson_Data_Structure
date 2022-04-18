package 數組;

import java.util.Arrays;

public class _977SquaresofaSortedArray {

	public static int[] sortedSquares(int[] nums) {
		for (int i = 0; i < nums.length; i++) {

				nums[i] = nums[i] * nums[i];
		}
		for (int i = nums.length -1; i >=0 ; i--) {
			for (int j = 1; j <= i; j++) {
				if (nums[j] < nums[j-1]){
					int temp = nums[j];
					nums[j] = nums[j-1];
					nums[j-1] = temp;
				}
			}
		}
		return nums;
	}

	public static void main(String[] args) {
		int[] intss = {3, 1, 6,23,9,0};
		int[] ints = sortedSquares(intss);
		System.out.println(Arrays.toString(ints));
	}
}
