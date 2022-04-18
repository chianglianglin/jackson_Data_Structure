package com.mj.tree;

import java.util.Comparator;

public class RBTree <E> extends BBST<E> {

    private static final boolean RED = false;

    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {

        super(comparator);
    }

    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) return node;
        ((RBNode<E>) node).color = color;
        return node;
    }
    private Node<E> red(Node<E> node){
        return color(node,RED);
    }
    private Node<E> black(Node<E> node){
        return color(node,BLACK);
    }
    private boolean colorOf(Node<E> node){
        return node == null?BLACK: ((RBNode<E>) node).color;
    }
    private boolean isBlack(Node<E> node){
        return colorOf(node)==BLACK;
    }
    private boolean isRed(Node<E> node){
        return colorOf(node)==RED;
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        //添加的是跟節點
        if(parent == null){
            black(node);
            return;
        }
        //如果父節點是黑色的直接返回
        if(isBlack(parent))return;
        //uncle node
        Node<E> uncle = parent.sibling();
        //grand node
        Node<E> grand = parent.parent;
        if (isRed(uncle)){
            black(parent);
            black(uncle);
            //把祖父節點當作新添加的節點
            afterAdd(red(grand));
            return;
        }
        //叔父節點不是紅色
        if (parent.isLeftChild()){//L
            if (node.isLeftChild()){//LL
                black(parent);
                red(grand);
                rotateRight(grand);
            }else {//LR
                black(node);
                red(grand);
                rotateLeft(parent);
                rotateRight(grand);
            }
        }else {
            if (node.isLeftChild()){//RL
                black(node);
                red(grand);
                rotateRight(parent);
                rotateLeft(grand);
            }else {//RR
                black(parent);
                red(grand);
                rotateLeft(grand);
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node ) {
        //如果刪除的節點是紅色
//        if (isRed(node))return;
        //用以取代node的子節點是紅色
        if (isRed(node)){
            black(node);
            return;
        }
        Node<E> parent = node.parent;
        //刪除是跟節點
        if (parent == null) return;
        //刪除是黑色葉子節點
        //判斷被刪除的node是左還是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left ;
        if (left){//刪除的節點在左邊,兄弟節點在右邊
            if (isRed(sibling)){
                //兄弟節點是紅色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                //更換兄弟
                sibling = parent.right;
            }
            //兄弟節點必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)){
                //兄弟節點沒有1個紅色子節點,父節點要向下跟兄弟節點合併
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack){
                    afterRemove(parent);
                }
            }else{
                //兄弟節點至少有1個紅色子節點,向兄弟結點借節點
                //兄弟節點的左邊是黑色,兄弟要先旋轉
                if(isBlack(sibling.right)){
                    rotateRight(sibling);
                    sibling = parent.right;
                }
                color(sibling,colorOf(parent));
                black(parent);
                black(sibling.right);
                rotateLeft(parent);
            }
        }else{//刪除的節點在右邊,兄弟節點在左邊
            if (isRed(sibling)){
                //兄弟節點是紅色
                black(sibling);
                red(parent);
                rotateRight(parent);
                //更換兄弟
                sibling = parent.left;
            }
            //兄弟節點必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)){
                //兄弟節點沒有1個紅色子節點,父節點要向下跟兄弟節點合併
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack){
                    afterRemove(parent);
                }
            }else{
                //兄弟節點至少有1個紅色子節點,向兄弟結點借節點
                //兄弟節點的左邊是黑色,兄弟要先旋轉
                if(isBlack(sibling.left)){
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                color(sibling,colorOf(parent));
                black(parent);
                black(sibling.left);
                rotateRight(parent);
            }

        }
    }
//    protected void afterRemove(Node<E> node,Node<E> replacement) {
//                //如果刪除的節點是紅色
//        if (isRed(node))return;
//        //用以取代node的子節點是紅色
//        if (isRed(replacement)){
//            black(replacement);
//            return;
//        }
//        Node<E> parent = node.parent;
//        //刪除是跟節點
//        if (parent == null) return;
//        //刪除是黑色葉子節點
//        //判斷被刪除的node是左還是右
//        boolean left = parent.left == null || node.isLeftChild();
//        Node<E> sibling = left ? parent.right : parent.left ;
//        if (left){//刪除的節點在左邊,兄弟節點在右邊
//            if (isRed(sibling)){
//                //兄弟節點是紅色
//                black(sibling);
//                red(parent);
//                rotateLeft(parent);
//                //更換兄弟
//                sibling = parent.right;
//            }
//            //兄弟節點必然是黑色
//            if (isBlack(sibling.left) && isBlack(sibling.right)){
//                //兄弟節點沒有1個紅色子節點,父節點要向下跟兄弟節點合併
//                boolean parentBlack = isBlack(parent);
//                black(parent);
//                red(sibling);
//                if (parentBlack){
//                    afterRemove(parent,null);
//                }
//            }else{
//                //兄弟節點至少有1個紅色子節點,向兄弟結點借節點
//                //兄弟節點的左邊是黑色,兄弟要先旋轉
//                if(isBlack(sibling.right)){
//                    rotateRight(sibling);
//                    sibling = parent.right;
//                }
//                color(sibling,colorOf(parent));
//                black(parent);
//                black(sibling.right);
//                rotateLeft(parent);
//            }
//        }else{//刪除的節點在右邊,兄弟節點在左邊
//            if (isRed(sibling)){
//                //兄弟節點是紅色
//                black(sibling);
//                red(parent);
//                rotateRight(parent);
//                //更換兄弟
//                sibling = parent.left;
//            }
//            //兄弟節點必然是黑色
//            if (isBlack(sibling.left) && isBlack(sibling.right)){
//                //兄弟節點沒有1個紅色子節點,父節點要向下跟兄弟節點合併
//                boolean parentBlack = isBlack(parent);
//                black(parent);
//                red(sibling);
//                if (parentBlack){
//                    afterRemove(parent,null);
//                }
//            }else{
//                //兄弟節點至少有1個紅色子節點,向兄弟結點借節點
//                //兄弟節點的左邊是黑色,兄弟要先旋轉
//                if(isBlack(sibling.left)){
//                    rotateLeft(sibling);
//                    sibling = parent.left;
//                }
//                color(sibling,colorOf(parent));
//                black(parent);
//                black(sibling.left);
//                rotateRight(parent);
//            }
//
//        }
//    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element,parent);
    }

    private static class RBNode<E> extends Node<E> {
        boolean color=RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            if (color == RED){
                str.append("R_");
            }
            return str.append(element).toString();
        }

    }
}