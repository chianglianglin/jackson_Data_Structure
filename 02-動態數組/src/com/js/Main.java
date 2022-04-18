package com.js;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


public class Main{
    public static void main(String[] args) {
//        ArrayList<Object> Persons = new ArrayList<>();
//      Persons.add(new Person(10,"jack"));
//      Persons.add(new Person(11,"jackson"));
//      Persons.add(new Person(12,"rose"));
//      Persons.clear();
//      //提醒jvm
//        System.gc();
//
//        System.out.println(Persons);
//        RabbitRunnable rabbitRunnable = new RabbitRunnable();
//        Thread t1 = new Thread(rabbitRunnable, "第一個窗口");
//        Thread t2 = new Thread(rabbitRunnable, "第二個窗口");
//        Thread t3 = new Thread(rabbitRunnable, "第三個窗口");
//        t1.start();
//        t2.start();
//        t3.start();
        LocalDateTime now = LocalDateTime.now();
        String format = DateTimeFormatter.ISO_DATE_TIME.format(now);
        System.out.println("format = " + format);
        System.out.println("now = " + now);

    }


    static class RabbitRunnable implements Runnable{
        ArrayList<String> array =  new ArrayList<String>();
        int count = 60;

        @Override
        public void run() {

                while (true){
                    synchronized (array) {
                        if (count <= 0) {
                            break;
                        }
                        System.out.println(Thread.currentThread().getName() + "賣第" + count-- + "張票");
                    }
                }
            }

    }
}