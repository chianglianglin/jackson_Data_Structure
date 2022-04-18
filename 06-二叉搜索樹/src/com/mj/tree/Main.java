package com.mj.tree;

import com.mj.Person;
import com.mj.printer.BinaryTrees;

import java.util.Comparator;

public class Main {


    static void test7(){
        Integer data[] = new Integer[]{
                7,4,9,2,5,8,11,3
        };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for(int i = 0; i< data.length; i++){
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
        bst.remove(7);
        BinaryTrees.println(bst);

    }

    public static void main(String[] args) {
//        test2();
//        System.out.println();
//        System.out.println("------------------------------------------------------------------------------------------------");
        test7();



    }
}
