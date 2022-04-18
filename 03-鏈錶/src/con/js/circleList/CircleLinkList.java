package con.js.circleList;

import con.js.AbstractList;

public class CircleLinkList<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;
    private Node<E> current;

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }


    @Override
    public void add(int index, E element) {
        if (index == size) {
            Node<E> oldLast = last;
            last = new Node<>(oldLast, element, first);
            if (oldLast == null) {
                first = last;
                first.next = first;
                first.prev = first;
            } else {
                oldLast.next = last;
                first.prev = last;
            }
        } else {
            rangeCheckForAdd(index);
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(prev, element, next);
            next.prev = node;
            prev.next = node;
            if (index == 0) {
                first = node;
            }
        }


        size++;

    }

    private Node<E> node(int index) {
        rangeCheck(index);
        if (index < (size >> 1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }

    }

    @Override
    public E get(int index) {

        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        //我的作法
        Node<E> old = node(index);
        node(index).element = element;
        return old.element;
        //老師做法
//        Node<E> node = node(index);
//        E old = node.element;
//        node.element = element;
//        return  old;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        return remove(node(index));
    }
    public E remove(){
        if (current == null) {
            return null;
        }
        Node<E> next = current.next;
        E element = remove(current);
        if (size == 0){
            current = null;
        }else{
             current = next;
        }
        return element;
    }
    private E remove(Node<E> node){
        if (size == 1){
            first = null;
            last = null;
        }else{
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;
            if (node == first) //node == first
            {
                first = next;
            }
            if (node == last) //node == last
            {
                last = prev;
            }
        }
        size--;
        //要返回原來的結點值
        return node.element;
    }

    @Override
    public int indexOf(E element) {

        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;
                node = node.next;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    public  void  reset(){
        current = first;
    }

    public E next(){
        if(current == null)return null;
        current = current.next;
        return current.element;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size = ").append(size).append(", [");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
//            stringBuilder.append(elements[i]).append(", ");
            if (i != 0) {
                stringBuilder.append(", ");
            }

            stringBuilder.append(node.element);
//            if (i != size-1){
//                stringBuilder.append(", ");
//            }
            node = node.next;
        }
        stringBuilder.append("]");
//        Node<E> node1 = first;
//        while (node1 != null){
//            node1 = node1.next;
//        }
        return stringBuilder.toString();
    }


}
