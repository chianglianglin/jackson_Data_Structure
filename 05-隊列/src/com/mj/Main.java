package com.mj;

import com.mj.Circle.CircleDeque;
import com.mj.Circle.CircleQueue;

public class Main {

    public static void main(String[] args) {
//        Queue<Integer> queue = new Queue<>();
//
//        queue.enQueue(11);
//        queue.enQueue(22);
//        queue.enQueue(33);
//        queue.enQueue(44);
//        queue.enQueue(55);
//        queue.enQueue(66);
//        while (!queue.isEmpty()){
//            System.out.println(queue.deQueue());
//        }

//        CircleQueue<Integer> queue = new CircleQueue<>();
//
//        for (int i = 0; i < 10; i++) {
//            queue.enQueue(i);
//        }
//        for (int j = 0; j < 5; j++) {
//            queue.deQueue();
//        }
//        for (int i = 15; i < 23 ; i++) {
//            queue.enQueue(i);
//        }
//        System.out.println(queue);
//
//        while (!queue.isEmpty()){
//            System.out.print(queue.deQueue()+" ");
//        }
        CircleDeque<Integer> queue = new CircleDeque<>();

        for (int i = 0; i < 10; i++) {
            queue.enQueueFront(i + 1);
            queue.enQueueRear(i + 100);
        }
//        for (int j = 0; j < 5; j++) {
//            queue.deQueue();
//        }
//        for (int i = 15; i < 23 ; i++) {
//            queue.enQueue(i);
//        }
        System.out.println(queue);

        while (!queue.isEmpty()){
            System.out.print(queue.deQueueFront()+" ");
        }

    }
}
