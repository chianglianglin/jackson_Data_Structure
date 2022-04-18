package com.mj;

import com.mj.printer.BinaryTrees;
import com.mj.tree.BinarySearchTree;

import java.util.Comparator;

public class Main {

    private static class PersonComparator implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e1.getAge()  - e2.getAge();
        }
    }

    private static class PersonComparator2 implements Comparator<Person>{
        @Override
        public int compare(Person e1, Person e2) {
            return e2.getAge()  - e1.getAge();
        }
    }

    static void test1(){
        Integer data[] = new Integer[]{
                7,4,9,2,5,8,11,3
        };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for(int i = 0; i< data.length; i++){
            bst.add(data[i]);
        }
        BinaryTrees.print(bst);
    }

    static void test2(){
        Integer data[] = new Integer[]{
                7,4,9,2,5,8,11,3
        };
        BinarySearchTree<Object> bst1 = new BinarySearchTree<>();
        for(int i = 0; i< data.length; i++){
            bst1.add(new Person(data[i]));
        }
        BinaryTrees.print(bst1);
    }

    static void test3(){
        Integer data[] = new Integer[]{
                7,4,9,2,5,8,11,3
        };
        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new Comparator<Person>() {
            @Override
            public int compare(Person e1, Person e2) {
                return e2.getAge() - e1.getAge();
            }
        });
        for(int i = 0; i< data.length; i++){
            bst2.add(new Person(data[i]));
        }
        BinarySearchTree<Person> bst = new BinarySearchTree<>();
//        BinaryTrees.print(bst2);
        bst.add(new Person(12,"jack12"));
        bst.add(new Person(14,"jack14"));
        bst.add(new Person(16,"jack16"));
        BinaryTrees.println(bst);
    }

    static void test6(){
        Integer data[] = new Integer[]{
                7,4,9,2,5,8,11,3
        };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for(int i = 0; i< data.length; i++){
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);
        bst.preorderTraversal();
        System.out.println();
        bst.inorderTraversal();
        System.out.println();
        bst.levelOrderTraversal();
        System.out.println();
//        bst.levelOrder(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            public boolean visit(Integer element) {
//                System.out.print("_"+ element + "_");
//                return element == 2 ? true :false;
//            }
//        });
        System.out.println();
//        bst.postorder(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            boolean visit(Integer element) {
//                System.out.print("_"+element+"_");
//                return element == 2 ? true:false;
//            }
//        });
        System.out.println();
        System.out.println(bst.height());
    }
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


        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new Comparator<Person>() {
            @Override
            public int compare(Person e1, Person e2) {
                return e1.getAge() - e2.getAge();
            }
        });
        bst2.add(new Person(12,"student"));
        bst2.add(new Person(14,"jackson"));

        BinarySearchTree<Person> bst3 = new BinarySearchTree<>(new PersonComparator2());
        bst3.add(new Person(12));
        bst3.add(new Person(15));


    }
}
