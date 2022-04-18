package com.mj.sort;

public class MergeSort<E extends Comparable<E>> extends Sort<E> {
	private E[] leftArray;
	@Override
	protected void sort() {
		leftArray = (E[])new Comparable[array.length >> 1];
		sort(0,array.length);
	}

	private void sort(int begin,int end){
		if (end - begin < 2)return ;
		int mid = (end + begin) >> 1;
		sort(begin, mid);
		sort(mid , end);
		merge(begin,mid,end);
	}


	/**
	 * 將[begin , mid) 和[mid,end)範圍的序列合併成一個有序序列
	 * @param begin
	 * @param mid
	 * @param end
	 */
	private void merge(int begin,int mid,int end){
		int li = 0,le = mid - begin;
		int ri = mid, re = end;
		int ai = begin;

		//備份左邊數組
		for (int i = li; i < le; i++) {
			leftArray[i] = array[begin + i];
		}
		//左邊還沒有結束
		while(li < le){

			if (ri < re && cmp(array[ri],leftArray[li]) < 0){
				array[ai++] = array[ri++];
			}else {//右邊結束
				array[ai++] = leftArray[li++];

			}
		}
	}
}
