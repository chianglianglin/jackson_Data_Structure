package com.mj.tree;

import com.mj.printer.BinaryTrees;

public class Main {


    static void test7(){
        Integer data[] = new Integer[]{
                85,19,69,3,7,99,95
//                ,2,1,70,44,58,11,21,14,93,57,4,56
        };
        AVLTree<Integer> AVL = new AVLTree<>();
        for(int i = 0; i< data.length; i++){
//            System.out.println("{"+data[i]+"}");
            AVL.add(data[i]);
//            BinaryTrees.println(AVL);
//            System.out.println("---------------");
        }
        AVL.remove(99);
        AVL.remove(85);
        AVL.remove(95);
        BinaryTrees.println(AVL);


//        BinaryTrees.println(AVL);
    }

    public static void main(String[] args) {
//        test2();
//        System.out.println();
//        System.out.println("------------------------------------------------------------------------------------------------");
        test7();
        


    }
}
