package com.mj.sort;

public class HeapSort<E extends Comparable<E>> extends Sort<E>{

	private  int heapSize;
	@Override
	protected void sort() {
		//原地建堆
		heapSize = array.length;
		for (int i = (heapSize >> 1) -1; i >=0 ; i--) {
			siftDown(i);
		}

		while (heapSize > 1){
			//交換堆頂元素和尾部元素
			swap(0,--heapSize);
			//對0位置進行siftDown(恢復堆的性質)
			siftDown(0);
		}
	}

	private void siftDown(int index){
		E element = array[index];
		int half = heapSize >> 1;
		while(index < half){//index必須是非葉子節點
			//默認是左邊根富節點比
			int childIndex = (index << 1) + 1;
			E child = array[childIndex];
			//比較左右子節點
			int rightIndex = childIndex + 1;
			if(rightIndex < heapSize && cmp(array[rightIndex],child) > 0){
				child = array[childIndex = rightIndex];
			}
			//大於等於子節點
			if (cmp(element,child) >= 0)break;
			array[index] = child;
			index = childIndex;
		}
		array[index] = element;
	}

}
