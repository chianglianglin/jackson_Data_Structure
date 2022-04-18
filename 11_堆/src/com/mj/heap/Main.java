package com.mj.heap;

import com.mj.printer.BinaryTrees;

import java.util.Comparator;

public class Main {

	static void test1(){
		BinaryHeap<Object> heap = new BinaryHeap<>();
		heap.add(68);
		heap.add(72);
		heap.add(43);
		heap.add(50);
		heap.add(38);
		heap.add(10);
		heap.add(90);
		heap.add(65);
		BinaryTrees.println(heap);
//		heap.remove();
//		BinaryTrees.println(heap);
		heap.replace(70);
		BinaryTrees.println(heap);
	}

	static void test2(){
		Integer[] data = {59, 41, 85, 61, 66, 45, 44, 56, 11, 99, 37, 8, 68, 90, 6, 18, 42, 24};
		BinaryHeap<Integer> heap = new BinaryHeap<>(data);
		BinaryTrees.println(heap);
		data[0] = 10;
		data[1] = 20;
		BinaryTrees.println(heap);
	}

	static void test3(){
		Integer[] data = {59, 41, 85, 61, 66, 45, 44, 56, 11, 99, 37, 8, 68, 90, 6, 18, 42, 24};
		BinaryHeap<Integer> heap = new BinaryHeap<>(data, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		BinaryTrees.println(heap);

	}

	static void test4(){
		BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		//找出最大的前k個數
		int k = 5;
		Integer[] data = {59, 41, 85, 61, 66, 45, 44,
				56, 11, 99, 37, 8, 68, 90, 6, 18, 42, 24};
		for (int i = 0; i < data.length; i++) {
			if (heap.size < k){
				heap.add(data[i]);
			}else{
				//第k+1個數大於堆頂元素加入堆中
				if (data[i] > heap.get()){
					heap.replace(data[i]);
				}
			}
		}
		BinaryTrees.println(heap);
	}
	public static void main(String[] args) {
		test4();
	}
}
