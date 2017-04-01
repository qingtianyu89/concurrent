package com.bj58.daojia.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pangming on 2016/11/23.
 */
public class WaitTest {

    private static List<String> list = new ArrayList<String>();
    private int head,tail,count;

    public synchronized void push(String item) throws InterruptedException {
        while (list.size() == 10) {
            this.wait();
        }
        list.add(item);
        System.out.println("push。。。" + item);
        this.notifyAll();
    }

    public synchronized String take() throws InterruptedException {
        while (list.isEmpty()) {
            this.wait();
        }
        String item = list.get(list.size()-1);
        System.out.println("taker。。。" + item);
        list.remove(item);
        this.notifyAll();
        return item;
    }

    public static void main(String[] args) {
        final Random random = new Random();
        final WaitTest waitTest = new WaitTest();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        waitTest.push((random.nextInt() + 2)+"");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        
        for (int i = 0; i < 60; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        waitTest.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
