package 數組;

public class _88MergeSortedArray {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
			int cur = nums1.length-1;
			int cur1 = m-1;
			int cur2 = n-1;
			while(cur2 >=0){
				if (cur1 >=0 && nums2[cur2] < nums1[cur1]){
					nums1[cur--] = nums1[cur1--];
				}else{ //cur1 < 0 || nums2[i2] >= nums1[i1]
					nums1[cur--] = nums2[cur2--];
				}

			}
	}
}
