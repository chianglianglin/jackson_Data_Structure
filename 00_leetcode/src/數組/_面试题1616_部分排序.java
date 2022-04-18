package 數組;

public class _面试题1616_部分排序 {

	public int[] subSort(int[] array) {
		if (array.length == 0) return new int[]{-1, -1};

		//從左往右找逆序對(逐漸變大)
		int max = array[0];
		//來記錄最右的那個逆序對位置
		int r = -1;
		for (int i = 0; i < array.length; i++) {
			int v = array[i];
			if (v >= max) {
				max = v;
			} else {
				r = i;
			}
		}

		//從右往左找逆序對(逐漸變小)
		int min= array[array.length -1];
		//來記錄最左的那個逆序對位置
		int l = -1;
		for (int i = array.length -2; i >= 0; i--) {
			int v = array[i];
			if (v <= min) {
				min = v;
			} else {
				l = i;
			}
		}
		return new int[]{l,r};
	}
}
