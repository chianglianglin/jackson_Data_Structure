package con.js.singeList;

import con.js.AbstractList;

public class SingleLinkList<E> extends AbstractList<E> {
    private Node<E> first;
    private static class Node<E>{
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }


    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if(index == 0){
            first = new Node<>(element, first);
        }else{
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(element, prev.next);
        }
        size++;

    }
    private Node<E> node(int index){
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
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
        Node<E> node = first;
        if (index == 0){
            first = first.next;
        }else{
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next =node.next;
        }
        size--;
        //要返回原來的結點值
        return node.element;
    }

    @Override
    public int indexOf(E element) {

        if (element == null){
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == null)return i;
                node = node.next;
            }
        }else{
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element))return i;
                node = node.next;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {
        size = 0;
        first =null;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size = ").append(size).append(", [");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
//            stringBuilder.append(elements[i]).append(", ");
            if (i != 0){
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
