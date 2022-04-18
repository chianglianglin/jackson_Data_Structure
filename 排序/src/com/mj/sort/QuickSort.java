package com.mj.sort;

public class QuickSort<T extends Comparable<T>> extends Sort<T>{
	@Override
	protected void sort() {
		sort(0,array.length);
	}

	/**
	 * 對[begin, end) 範圍的元素進行快速排序
	 * @param begin
	 * @param end
	 */
	private void sort(int begin, int end){
		//end - begin =元素數量 所以元素數量只有一個return 不用計算
		if (end - begin < 2)return;
		//確定軸點位置
		int mid = pivotIndex(begin,end);
		//對子序列進行快速排序
		sort(begin,mid);
		sort(mid + 1,end);

	}

	/**
	 * 構造出[begin,end)範圍的軸點元素
	 * @param begin
	 * @param end
	 * @return 軸點元素的最終位置
	 */
	private int pivotIndex(int begin,int end){
		//隨機選擇一個元素跟begin位置進行交換
		swap(begin,begin + (int)(Math.random() * (end - begin)));
		//備份
		T pivot = array[begin];
		//end指向最後一個節點
		end--;
		//begin == end不用繼續
		while (begin < end){
			while (begin < end) {
				if (cmp(pivot, array[end]) < 0) {
					end--;
				} else {
					//等於時
					array[begin++] = array[end];
					//要掉頭
					break;
				}
			}
			while (begin < end) {
				if (cmp(pivot, array[begin]) > 0) {
					begin++;
				} else {
					array[end--] = array[begin];
					//要掉頭
					break;
				}
			}
		}
		//當begin == end
		array[begin] = pivot;
		return begin;
	}
}
