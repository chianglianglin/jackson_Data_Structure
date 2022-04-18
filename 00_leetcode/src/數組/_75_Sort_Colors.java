package 數組;

public class _75_Sort_Colors {
	public void sortColors(int[] nums) {
		int red = 0;
		int green = 0;
		int purple = nums.length - 1;
		while (red <= purple){
			int v = nums[red];
			if (v == 0){
				swap(nums,red++,green++);
			}else if (v == 1){
				red++;
			}else{
				swap(nums,red,purple--);
			}
		}
	}
	private void swap(int[] nums, int i, int j){
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}
