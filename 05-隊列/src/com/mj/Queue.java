package com.mj;

import com.mj.list.LinkList;
import com.mj.list.List;

public class Queue<E> {
    private List<E> list = new LinkList<>();

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void enQueue(E element){
        list.add(element);
    }

    public E deQueue(){
        return list.remove(0);
    }

    public E front(){
        return list.get(0);
    }
}
