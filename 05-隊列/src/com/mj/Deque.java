package com.mj;

import com.mj.list.LinkList;
import com.mj.list.List;
import org.w3c.dom.stylesheets.LinkStyle;

public class Deque<E> {
    private List<E> list = new LinkList<>();

    public int size(){
        return list.size();
    }

    public  boolean isEmpty(){
        return list.isEmpty();
    }

    public void enQueueRear(E element){
        list.add(element);
    }

    public E deQueueFront(){
        return list.remove(0);
    }

    public void enQueueFront(E element){
        list.add(0,element);
    }

    public E deQueueRear(){
        return list.remove(list.size() - 1);
    }

    public E front(){
        return list.get(0);
    }

    public E rear(){
        return list.get(list.size() - 1);
    }
}
