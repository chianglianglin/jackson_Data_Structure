package com.mj.tree;

import com.mj.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTreeOrigin<E> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;

    public BinarySearchTreeOrigin(){
        this(null);
    }

    public BinarySearchTreeOrigin(Comparator<E> comparator){

        this.comparator = comparator;
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>)node).element;
    }

    private static class Node<E>{
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;
        public Node(E element,Node<E> parent){
            this.element = element;
            this.parent = parent;
        }
        public boolean isLeaf(){
            return left == null && right ==null;
        }

        public boolean hasTwoChildren(){
            return left != null && right != null;
        }
    }
    public static abstract class Visitor<E> {
        boolean stop;

        abstract boolean visit(E element);
    }


    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E element){
        elementNotNullCheck(element);
        if(root == null){
            root = new Node<>(element, null);
            size++;
            return;
        }

        Node<E> parent = null;
        Node<E> node = root;
        int cmp = 0;
        while (node != null){
            cmp = compare(element, node.element);
            parent = node;
            if(cmp > 0){
                node = node.right;
            }else if(cmp < 0){
                node = node.left;
            }else{
                return;
            }
        }

        Node<E> newNode = new Node<>(element,parent);
        if(cmp > 0){
            parent.right = newNode;
        }else{
            parent.left = newNode;
        }
        size++;


    }
    private int compare(E e1, E e2){
        if(comparator != null){

            return comparator.compare(e1,e2);
        }
        return ((Comparable<E>)e1).compareTo(e2);
    }
    public void remove(E element){
            remove(node(element));
    }

    private void remove(Node<E> node){

    }
    private Node<E> node(E element){
            Node<E> node = root;
            while (node != null){
                int cmp = compare(element,node.element);
                if (cmp == 0)return node;
                if (cmp < 0)return node = node.left;
                if (cmp > 0) return node = node.right;
            }
            return null;
    }
    public boolean contains(E element){
        return false;
    }
    private void elementNotNullCheck(E element){
        if(element == null){
            throw new IllegalArgumentException("element must not be null");
        }
    }

    public void preorderTraversal(){
        preorderTraversal(root);
    }

    public void preorderTraversal(Node<E> node){
        if(node == null)return;
        System.out.print(node.element+",");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    public void inorderTraversal(){
        inorderTraversal(root);
    }

    public void inorderTraversal(Node<E> node){
        if(node == null)return;
        inorderTraversal(node.left);
        System.out.print(node.element+",");
        inorderTraversal(node.right);
    }

    public void postorderTraversal(){
        postorderTraversal(root);
    }

    public void postorderTraversal(Node<E> node){
        if(node == null)return;
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.element+",");
    }

    public void postorder(Visitor<E> visitor){
        if (visitor == null)return;
        postorder(root,visitor);
    }
    public void postorder(Node<E>node,Visitor<E> visitor){
        if(node == null||visitor.stop)return;
        postorder(node.left,visitor);
        postorder(node.right,visitor);
        if (visitor.stop)return;
         visitor.stop= visitor.visit(node.element);
    }

    public void levelOrderTraversal(){
        if(root == null)return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            System.out.print(node.element+",");
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
    }
    public void levelOrder(Visitor<E> visitor){
        if(root == null || visitor == null)return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            if(visitor.visit(node.element))return;
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
    }

    //使用層序遍歷
    public int height(){
        if (root == null)return 0;
        //樹的高度
        int height = 0;
        //儲存每一層的元素數量
        int levelNum = 0;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        levelNum++;
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            levelNum--;
//            System.out.print(node.element+",");
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
            if(levelNum == 0){
                levelNum = queue.size();
                height++;
            }
        }
        return height;
    }

    //遞歸
    public int height2(){
        return height2(root);
    }

    private int height2(Node<E> node){
        if (node == null)return 0;
        return 1 + Math.max(height2(node.right),height2(node.left));
    }

    //層序遍歷來判斷是否為完全二叉樹
    public boolean isComplete(){
        if (root == null)return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();

            if(leaf && !node.isLeaf())return false;
            if (node.left != null){
                queue.offer(node.left);
            }else if (node.right != null){
                return false;
            }

            if (node.right != null){
                queue.offer(node.right);
            }else {
//                node.left != null && node.right ==null
//                node.left == null && node.right == null
                //之後都是葉子節點
                leaf = true;
            }

        }
        return true;
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
