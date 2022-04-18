package 數組;

public class _4_Median_of_Two_Sorted_Arrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int size = nums1.length + nums2.length;
		int[] totalArray = new int[size];
		int index_1 = 0;
		int index_2 = 0;
		int t = 0;
		int med = 0;
		while (index_1 < nums1.length || index_2 < nums2.length) {
			if (index_1 < nums1.length && nums1[index_1] < nums2[index_2]) {
				totalArray[t++] = nums1[index_1++];
			} else {
				totalArray[t++] = nums2[index_2++];
			}

		}

		return 1.0;

	}


	//時間複雜度O(m+n)
	public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
		int[] nums3 = new int[nums1.length + nums2.length];
		double ans = 0.0;
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] < nums2[j]) {
				nums3[k++] = nums1[i++];
				continue;
			} else {
				nums3[k++] = nums2[j++];
				continue;
			}
		}
		while (i < nums1.length) {
			nums3[k++] = nums1[i++];
		}
		while (j < nums2.length) {
			nums3[k++] = nums2[j++];
		}
		int index = nums3.length / 2;
		if (nums3.length % 2 == 0) {
			ans = ((double) nums3[index - 1] + (double) nums3[index]) / 2;
		} else {
			ans = nums3[index];
		}
		return ans;
	}
}
