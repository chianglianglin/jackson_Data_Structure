package com.mj.tree;

import java.util.Comparator;

public class BBST<E> extends BinarySearchTree<E>{
    public BBST() {
        this(null);
    }

    public BBST(Comparator<E> comparator) {

        super(comparator);
    }
    protected void rotateRight(Node<E> grand){
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand,parent,child);
    }
    protected void rotateLeft(Node<E> grand){
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand,parent,child);

    }
    protected void afterRotate(Node<E> grand,Node<E> parent,Node<E> child){
        //讓parent成為根節點
        parent.parent = grand.parent;
        if (grand.isLeftChild()){
            grand.parent.left = parent;
        }else if (grand.isRightChild()){
            grand.parent.right = parent;
        }else{
            //跟節點
            root = parent;
        }

        //更新child的父節點
        if (child != null){
            child.parent = grand;
        }
        //更新grand的parent
        grand.parent = parent;

    }
    protected void rotate(Node<E> r,//子樹的跟節點
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
        //e-f-g
        f.left = e;
        if (e != null)e.parent = f;
        f.right = g;
        if (g != null)g.parent = f;
        //b-d-f
        d.left = b;
        if (b != null)b.parent = d;
        d.right = f;
        if (f != null)f.parent = d;


    }

}
