package com.mj.sort;

public class BubbleSort3 <E extends Comparable<E>> extends Sort<E>{
	@Override
	protected void sort() {
		//提前有序
		for (int end = array.length - 1; end > 0; end--) {
			//為完全有序準備
			int sortIndex = 0;
			for (int begin = 1; begin <= end; begin++) {
				if (cmp(begin,begin-1) < 0) {
					swap(begin,begin-1);
					sortIndex = begin;
				}
			}
			end = sortIndex;
		}
	}

}
