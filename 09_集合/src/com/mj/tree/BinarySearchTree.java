package com.mj.tree;

import java.util.Comparator;

public class BinarySearchTree<E>  extends BinaryTree<E>{

    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {

        this.comparator = comparator;
    }

    public void add(E element) {
        elementNotNullCheck(element);
        if (root == null) {
            root = createNode(element, null);
            size++;
            afterAdd(root);
            return;
        }

        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                return;
            }
        }

        Node<E> newNode = createNode(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        //新增節點後處理
        afterAdd(newNode);


    }
    /**添加node之後的調整*/
    protected void afterAdd(Node<E> node){}

    /**刪除node之後的調整*/
    protected  void afterRemove(Node<E> node){}

    private int compare(E e1, E e2) {
        if (comparator != null) {

            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null) return;
        if (node.hasTwoChildren()) {
            //度唯二的節點
            Node<E> successor = successor(node);
            node.element = successor.element;
            node = successor;
        }
        //刪除node節點必為是1或0
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null){
            //更改parent
            replacement.parent= node.parent;
            //更改parent的left或right指向
            if (node.parent == null){
                root = replacement;
            }else if(node == node.parent.right){
                node.parent.right = replacement;
            }else {
                node.parent.left = replacement;
            }
            //刪除節點之後的處理
            afterRemove(replacement);
        }else if (node.parent == null){
            //node是葉子節點也是跟節點
            root =null;

            //刪除節點之後的處理
            afterRemove(node);
        }else{
            //node是葉子節點但不是跟節點
            if (node == node.parent.left){
                node.parent.left = null;
            }else{
                node.parent.right = null;
            }

            //刪除節點之後的處理
            afterRemove(node);
        }
        size--;
    }

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp < 0) {
                node = node.left;
            }else if(cmp > 0){

                node = node.right;
            }
        }
        return null;
    }

    public boolean contains(E element) {
        return false;
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }


    //層序遍歷來判斷是否為完全二叉樹(會有低級錯誤)
//    public boolean isComplete(){
//        if (root == null)return false;
//
//        Queue<Node<E>> queue = new LinkedList<>();
//        queue.offer(root);
//
//        boolean leaf = false;
//        while (!queue.isEmpty()){
//            Node<E> node = queue.poll();
//            if (leaf && !node.isLeaf())return false;
//            if (node.hasTwoChildren()){
//                queue.offer(node.left);
//                queue.offer(node.right);
//            }else if (node.left == null && node.right != null){
//                return false;
//            }else{
//                //後面遍歷的節點必須是葉子節點
//                leaf = true;
//                if(node.left != null){
//                    queue.offer(node.left);
//                }
//            }
//        }
//        return false;
//    }
}
