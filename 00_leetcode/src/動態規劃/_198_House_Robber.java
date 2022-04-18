package 動態規劃;

public class _198_House_Robber {


	//使用遞歸方法
	public int rob1(int[] nums) {
		return rob(nums,0);
	}
	private int rob(int[] array,int begin){
		if (begin == (array.length-1)){
			return array[begin];
		}
		if (begin == (array.length - 2)){
			return Math.max(array[begin],array[begin + 1]);
		}
		return Math.max(array[begin] + rob(array,begin + 2),rob(array,begin + 1));
	}

	//使用一維數組
	public int rob2(int[] nums) {
		if (nums == null || nums.length == 0)return 0;
		//至少要有兩個房屋
		if (nums.length == 1)return nums[0];
		int[]rob = new int[nums.length];
		rob[0] = nums[0];
		rob[1] = Math.max(nums[0],nums[1]);
		for (int i = 2; i < rob.length; i++) {
			rob[i] = Math.max(nums[i] + rob[i - 2],rob[i - 1]);
		}

		return rob[nums.length - 1];
	}

	//只使用兩個指針
	public int rob3(int[] nums) {
		if (nums == null || nums.length == 0)return 0;
		//至少要有兩個房屋
		if (nums.length == 1)return nums[0];
		int first = nums[0];
		int second = Math.max(nums[0],nums[1]);
		for (int i = 2; i < nums.length; i++) {
			int temp = second;
			second = Math.max(nums[i] + first,second);
			first = temp;
		}
		return second;
	}

}
