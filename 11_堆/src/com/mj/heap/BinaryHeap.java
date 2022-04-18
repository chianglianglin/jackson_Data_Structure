package com.mj.heap;

import com.mj.printer.BinaryTreeInfo;

import java.util.Comparator;

public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {

	private E[] elements;

	private static final int DEFAULT_CAPACITY = 10;

	public BinaryHeap(E[] elements,Comparator<E> comparator){
		super(comparator);
		if (elements == null|| elements.length ==0){
			this.elements = (E[]) new Object[DEFAULT_CAPACITY];
		}else{
			size = elements.length;
			int capacity = Math.max(elements.length,DEFAULT_CAPACITY);
			this.elements = (E[]) new Object[capacity];
			for (int i = 0; i < elements.length; i++) {
				this.elements[i] = elements[i];
			}
			heapify();
		}
	}

	public BinaryHeap(E[] elements){
		this(elements,null);
	}

	public BinaryHeap(Comparator<E> comparator){
		this(null,comparator);
	}

	public BinaryHeap(){
		this(null,null);
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	@Override
	public void add(E element) {
		elementNotNullCheck(element);
		ensureCapacity(size + 1);
		elements[size++] = element;
		siftUp(size - 1);
	}


	/**
	 * 讓index位置的元素上濾
	 * @param index
	 */
	private void siftUp(int index){
		E e = elements[index];
		while (index > 0){
			int pIndex = (index - 1)>>1;
			E p = elements[pIndex];
//			if (compare(e,p)<= 0)return;
			if (compare(e,p)<= 0)break;
			//交換index ,pIndex 位置的內容
//			E tmp = elements[index];
//			elements[index] = elements[pIndex];
//			elements[pIndex] = tmp;

			//將赴元素存儲在index位置
			elements[index] = p;
			//重新附值
			index = pIndex;
		}
		elements[index] = e;
	}

	@Override
	public E get() {
		emptyCheck();
		return elements[0];
	}

	@Override
	public E remove() {
		emptyCheck();
//		E root = elements[0];
//		elements[0] = elements[size - 1];
//		elements[size - 1] = null;
		int lastIndex = --size;
		E root = elements[0];
		elements[0] = elements[lastIndex];
		elements[lastIndex] = null;
//		size--;
		siftDown(0);
		return root;
	}

	@Override
	public E replace(E element) {
		elementNotNullCheck(element);
		E root = null;
		if (size == 0){
			elements[0] = element;
			size++;
		}else {
			root = element;
			elements[0] = element;
			siftDown(0);
		}
		return root;
	}


	/**
	 * 自下而上的下濾
	 */
	private void heapify(){

		for (int i = (size >> 1) - 1; i>=0; i--){
			siftDown(i);
		}
	}

	private void siftDown(int index){
		int firstLeafNode = size >> 1;
		E element = elements[index];
		//第一個葉子節點的索引 == 非葉子節點的數量
//		while (index < 第一個葉子節點的索引){//必須保證index位置是非葉子節點
		while(index < firstLeafNode){
			//index有兩種情況
			//1.只有左子節點
			//2.同時有左右子節點
			//默認為左子節點跟他進行比較
			int childIndex = (index << 1) + 1;
			E child = elements[childIndex];
			//右子節點
			int rightIndex = childIndex + 1;
			//選出左右子節點最大的節點
			if(rightIndex < size && compare(elements[rightIndex],child) > 0){
				childIndex = rightIndex;
				child = elements[rightIndex];
			}
			if (compare(element,child) >= 0)break;
			//將子節點存放到index位置
			elements[index] = child;
			//重新設置index
			index = childIndex;
		}
		elements[index] = element;
	}


	/**
	 * 扩容操作
	 */
	private void ensureCapacity(int capacity){
		int oldCapacity = elements.length;
		if(oldCapacity >= capacity)return;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;

	}

	private void emptyCheck(){
		if (size == 0){
			throw new IndexOutOfBoundsException("heap is empty");
		}
	}
	private void elementNotNullCheck(E element){
		if (element == null){
			throw new IllegalArgumentException("element must not null");
		}
	}

	@Override
	public Object root() {
		return 0;
	}

	@Override
	public Object left(Object node) {
//		Integer index = (Integer) node;
//		index = (index << 1) + 1;
		int index = ((int) node << 1) + 1;
		return index >= size? null : index;
	}

	@Override
	public Object right(Object node) {
//		Integer index = (Integer) node;
		int index = ((int) node<< 1) + 2;
		return index >= size? null : index;
	}

	@Override
	public Object string(Object node) {
//		Integer index = (Integer) node;
		return elements[(int) node];
	}
}
