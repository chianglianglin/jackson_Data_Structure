package com.js;

class ArrayList<E> {
    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;
    

    public ArrayList(int capaticy){
        capaticy = (capaticy < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capaticy;
        elements = (E[]) new Object[capaticy];
    }
    public ArrayList(){
        this(DEFAULT_CAPACITY);


    }

    /**
     * 元素的数量
     *
     * @return
     */


    public int size() {

        return size;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * 是否包含某个元素
     * @param element
     * @return
     */
    public boolean contains(E element){
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }
    /**
     * 在index位置插入一个元素
     * @param index
     * @param element
     */
    public void add(int index, E element){
//        if(index < 0 || index > size){
//            throw new IndexOutOfBoundsException("index :"+ index + ", Size :"+ size);
//        }
        rangeCheckForAdd(index);
        ensureCapacity(index + 1);
        for (int i = size-1; i >= index ; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;
    }
    /**
     * 添加元素到数组最后
     */
    public void add(E element){
        add(size,element);
    }
    /**
     * 获取index位置的元素
     * @param index
     * @return 原来的元素ֵ
     */
    public E get(int index){
//        if(index < 0 || index >= size){
//            throw new IndexOutOfBoundsException("index :"+ index + ", Size :"+ size);
//        }
        rangeCheck(index);
        return  elements[index];
    }
    /**
     * 设置index位置的元素
     * @param index
     * @param element
     * @return 原来的元素ֵ
     */
    public E  set(int index, E element){
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }
    /**
     * 删除index位置的元素
     * @param index
     * @return
     */
    public E remove(int index){
//        if (index < 0 || index >= size){
//            throw new IndexOutOfBoundsException("index :" + index + ",Size :"+ size);
//        }
        rangeCheck(index);
        E old = elements[index];
        for (int i = index + 1; i <size; i++) {
            elements[i - 1] = elements[i];
        }
        size--;
        elements[size] = null;
//        elements[--size] = null;
        return old;
    }
    /**
     * 查看元素的索引
     * @param element
     * @return
     */
    public int indexOf(E element){

        if (element == null){
            for (int i = 0; i < size; i++) {
                if (elements[i]==null){
                    return i;
                }
            }
        }else{
            for (int i = 0 ; i<size ; i++){
//            if(elements[i] == element){
//                return i;
//            }
                if(element.equals(elements[i])){
                    return i;
                }
            }
        }

        return ELEMENT_NOT_FOUND;
    }
    /**
     * 清除所有元素
     */
    public void clear(){
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        // 使用泛型数组后要注意内存管理(将元素置null)

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
    /****************封装好的功能函数**************************/
    // 下标越界抛出的异常
    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
    }
    // 检查下标越界(不可访问或删除size位置)
    private void rangeCheck(int index){
        if (index < 0 || index >= size){
            outOfBounds(index);
        }
    }
    // 检查add()的下标越界(可以在size位置添加元素)
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size){
            outOfBounds(index);
        }
    }
    /****************封装好的功能函数***************************/
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size = ").append(size).append(", [");
        for (int i = 0; i < size; i++) {
//            stringBuilder.append(elements[i]).append(", ");
            if (i != 0){
                stringBuilder.append(", ");
            }
            stringBuilder.append(elements[i]);
//            if (i != size-1){
//                stringBuilder.append(", ");
//            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}