package com.mj;

import com.mj.sort.*;
import com.mj.tools.Asserts;
import com.mj.tools.Integers;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
//		int[] array = {3,6,23,56,88,90,134};
//		Asserts.test(BinarySearch.indexOf(array,6) == 1);
//		Asserts.test(BinarySearch.indexOf(array,56) == 3);
//		Asserts.test(BinarySearch.indexOf(array,0) == -1);
//		Asserts.test(BinarySearch.indexOf(array,134) == 6);
//		Asserts.test(BinarySearch.indexOf(array,3) == 0);
//		System.out.println(BinarySearch.indexOf(array,56));
		Integer[] array = Integers.random(10000, 1, 20000);
		testSorts(array,
				new HeapSort(),
				new BubbleSort3(),
				new SelectionSort(),
				new InsertionSort1(),
				new InsertionSort2(),
				new InsertionSort3(),
				new QuickSort());

	}
	static void testSorts(Integer[] array, Sort... sorts){
		for (Sort sort: sorts) {
			Integer[] newArray = Integers.copy(array);
			sort.sort(newArray);
			Asserts.test(Integers.isAscOrder(newArray));
		}
		Arrays.sort(sorts);
		for(Sort sort: sorts){
			System.out.println(sort);
		}
	}

	static void selectionSort(Integer[] array){

		for (int end = array.length - 1; end > 0 ; end--) {
			int maxIndex = 0;
			for (int begin = 1; begin <= end ; begin++) {
				//等號是為了穩定排序
				if (array[maxIndex] <= array[begin]){
					maxIndex = begin;
				}
			}
			int temp = array[maxIndex];
			array[maxIndex] = array[end];
			array[end] = temp;
		}
	}


	static void bubbleSort1(Integer[] array) {
		for (int end = array.length - 1; end > 0; end--) {
			for (int begin = 1; begin <= end; begin++) {
				if (array[begin] < array[begin - 1]) {
					int temp = array[begin];
					array[begin] = array[begin - 1];
					array[begin - 1] = temp;
				}
			}
		}
	}


	static void bubbleSort2(Integer[] array) {
		//提前有序
		for (int end = array.length - 1; end > 0; end--) {
			boolean sorted = true;
			for (int begin = 1; begin <= end; begin++) {
				if (array[begin] < array[begin - 1]) {
					int temp = array[begin];
					array[begin] = array[begin - 1];
					array[begin - 1] = temp;
					sorted = false;
				}
			}
			if (sorted) break;
		}
	}
	static void bubbleSort3(Integer[] array) {
		//提前有序
		for (int end = array.length - 1; end > 0; end--) {
			//為完全有序準備
			int sortIndex = 0;
			for (int begin = 1; begin <= end; begin++) {
				if (array[begin] < array[begin - 1]) {
					int temp = array[begin];
					array[begin] = array[begin - 1];
					array[begin - 1] = temp;
					sortIndex = begin;
				}
			}
			end = sortIndex;
		}
	}

}
