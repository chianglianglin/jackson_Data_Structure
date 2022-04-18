package com.mj.sort;

public class InsertionSort1<E extends Comparable<E>> extends Sort<E>{
	@Override
	protected void sort() {
		for (int begin = 1; begin < array.length; begin++) {
			int cur = begin;
			while (cur > 0 && cmp(cur,cur -1) < 0){
				swap(cur,cur -1);
				cur--;
			}
		}
	}
}
