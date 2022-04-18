package com.mj.sort;

public class InsertionSort3<E extends Comparable<E>> extends Sort<E>{
	@Override
	protected void sort() {
		//使用二叉搜索
		for (int begin = 1; begin < array.length; begin++) {
			E v = array[begin];
			int insertIndex = search(begin);
			for (int i = begin - 1; i>= insertIndex;i--){
				array[i + 1]  = array[i];
			}
			array[insertIndex] = v;
		}
	}

	private int search(int index){
		E v = array[index];
		int begin = 0;
		int end = index;
		while (begin < end){
			int mid = (begin + end) >> 1;
			if (cmp(v, array[mid])<0){
				end = mid;
			}else {
				begin = mid + 1;
			}
		}
		return begin;
	}
}
