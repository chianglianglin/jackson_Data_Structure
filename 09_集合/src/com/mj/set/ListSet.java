package com.mj.set;

import com.mj.list.LinkList;
import com.mj.list.List;

public class ListSet<E> implements Set<E>{
    private List<E> list =new LinkList<E>();

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        if (list.contains(element))return;
        list.add(element);
    }

    @Override
    public void remove(E element) {
        if (list.contains(element)){
            int index = list.indexOf(element);
            list.remove(index);
        }
    }

    @Override
    public void traversal(Visitor<E> visitor) {
            if (visitor == null)return;

            int size = list.size();
        for (int i = 0; i < size; i++) {
            if (visitor.visit(list.get(i)))return;
        }
    }
}
