package com.mj.tree;

import java.util.Comparator;

public class AVLTree<E> extends BinarySearchTree<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {

        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)){
                //更新高度
                updateHeight(node);
            }else {
                //恢復平衡
                reBalance(node);
                break;
            }
        }
    }
    @Override
    protected void afterRemove(Node<E> node) {

        while ((node = node.parent) != null) {
            if (isBalanced(node)){
                //更新高度
                updateHeight(node);
            }else {
                //恢復平衡
                reBalance(node);
            }
        }
    }
    private boolean isBalanced(Node<E> node){
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }
    private void updateHeight(Node<E> node){
        ((AVLNode<E>)node).updateHeight();
    }

    /**恢復平衡*/
    private void reBalance2(Node<E> grand){
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()){//L
            if (node.isLeftChild()){//LL
                rotateRight(grand);
            }else {//LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        }else{//R
            if (node.isRightChild()){//RR
                rotateLeft(grand);
            }else{//RL
                rotateRight(parent);
                rotateLeft(grand);
            }
        }

    }
    /**統一處理恢復平衡*/
    private void reBalance(Node<E> grand){
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()){//L
            if (node.isLeftChild()){//LL
                rotate(grand,node.left,node,node.right,parent,parent.right,grand,grand.right);
            }else {//LR
                rotate(grand,parent.left,parent,node.left,node,node.right,grand,grand.right);
            }
        }else{//R
            if (node.isRightChild()){//RR
                    rotate(grand,grand.left,grand,parent.left,parent,node.left,node,node.right);
            }else{//RL
                rotate(grand,grand.left,grand,node.left,node,node.right,parent,parent.right);
            }
        }

    }
    private void rotate(Node<E> r,//子樹的跟節點
                        Node<E> a,Node<E> b,Node<E>c,
                        Node<E> d,
                        Node<E> e, Node<E> f, Node<E> g){
        //讓d成為這顆子樹的跟節點
        d.parent = r.parent;
        if(r.isLeftChild()){
            r.parent.left = d;
        }else if (r.isRightChild()){
            r.parent.right = d;
        }else{
            root = d;
        }

        //a-b-c
        b.left = a;
        if (a != null)a.parent = b;
        b.right = c;
        if (c != null)c.parent = b;
        updateHeight(b);
        //e-f-g
        f.left = e;
        if (e != null)e.parent = f;
        f.right = g;
        if (g != null)g.parent = f;
        updateHeight(f);
        //b-d-f
        d.left = b;
        if (b != null)b.parent = d;
        d.right = f;
        if (f != null)f.parent = d;
        updateHeight(d);

    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element,parent);
    }

    private static class AVLNode<E> extends Node<E> {
        int height = 1;
        public AVLNode(E element,Node<E> parent){
            super(element,parent);
        }
        public int balanceFactor(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null? 0 : ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }
        public void  updateHeight(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null? 0 : ((AVLNode<E>)right).height;
            height = 1 + Math.max(leftHeight,rightHeight);
        }
        public Node<E> tallerChild(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null? 0 : ((AVLNode<E>)right).height;
            if (leftHeight > rightHeight)return left;
            if (rightHeight > leftHeight)return right;
            return isLeftChild() ? left:right;

        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return element + "_p(" + parentString + ")_h(" + height + ")";
        }

//        @Override
//        public String toString() {
//            return this.element+"_p("+this.parent.element+")_h("+this.height+")";
//        }
    }



    private void rotateRight(Node<E> grand){
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand,parent,child);
    }
    private void rotateLeft(Node<E> grand){
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand,parent,child);

    }
    protected void afterRotate(Node<E> grand,Node<E> parent,Node<E> child){
        if (grand.isLeftChild()){
            grand.parent.left = parent;
        }else if (grand.isRightChild()){
            grand.parent.right = parent;
        }else{
            //跟節點
            root = parent;
        }
        //讓parent成為根節點
        parent.parent = grand.parent;

        //更新child的父節點
        if (child != null){
            child.parent = grand;
        }
        //更新grand的parent
        grand.parent = parent;
        updateHeight(grand);
        updateHeight(parent);
    }

}
