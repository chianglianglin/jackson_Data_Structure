package com.mj.Circle;

@SuppressWarnings("unchecked")
public class CircleDeque<E> {
    private int front;
    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public CircleDeque(){
        elements  = (E[])new Object[DEFAULT_CAPACITY];
    }

    public int size(){
        return size();
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void enQueueRear(E element){
        ensureCapacity(size+1);
        elements[(front + size) % elements.length] = element;
        size++;
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
            newElements[i] = elements[(front + i) % elements.length];
        }
        front = 0;
        elements = newElements;

    }

    public E deQueueFront(){
        E frontElement = elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;
        return frontElement;
    }
    public void enQueueFront(E element){
        ensureCapacity(size+1);
        //front 指向新增的座標
        front = index(-1);
        elements[front] = element;
        size++;
    }

    public E deQueueRear(){
        int rear = rearIndex();
        E rearEle = elements[rear];
        elements[rear] = null;
        size--;
        return rearEle;
    }
    private int index(int index){
        index += front;
        if (index < 0){
            return index + elements.length;
        }
        return index % elements.length;
    }


    public E front(){
        return elements[front];
    }

    public E rear(){
        return elements[rearIndex()];
    }
    private int rearIndex(){
        return index(size - 1);
    }
    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        string.append("capcacity=").append(elements.length)
                .append("size=").append(size)
                .append("front= ").append(front)
                .append(",[");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0){
                string.append(", ");
            }
            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }
}
