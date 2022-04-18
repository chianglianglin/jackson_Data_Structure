package con.js;

public abstract class AbstractList<E> implements List<E>{
    //記得要放在這裡不然add方法會取不到size
    protected int size;

    /**
     * 元素的数量
     * @return
     */
    public int size(){
        return size;
    }
    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty(){
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
     * 添加元素到数组最后
     */
    public void add(E element){
        add(size,element);
    }
    // 下标越界抛出的异常
    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
    }
    // 检查下标越界(不可访问或删除size位置)
    protected void rangeCheck(int index){
        if (index < 0 || index >= size){
            outOfBounds(index);
        }
    }
    // 检查add()的下标越界(可以在size位置添加元素)
    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size){
            outOfBounds(index);
        }
    }
}
