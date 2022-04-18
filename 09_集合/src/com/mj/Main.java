package com.mj;

import com.mj.set.ListSet;
import com.mj.set.Set;
import com.mj.set.TreeSet;

public class Main {
    public static void main(String[] args) {
//        Set<Integer> listset = new ListSet<>();
//        listset.add(10);
//        listset.add(11);
//        listset.add(12);
//        listset.add(12);
//        listset.add(13);
//        listset.traversal(new Set.Visitor<Integer>() {
//
//            @Override
//            public boolean visit(Integer element) {
//                System.out.println(element);
//                return false;
//            }
//        });

        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(12);
        treeSet.add(10);
        treeSet.add(11);
        treeSet.add(12);
        treeSet.add(13);
        treeSet.traversal(new Set.Visitor<Integer>() {

            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }
}
