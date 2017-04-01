package com.bj58.daojia.sychonized;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by pangming on 2016/11/18.
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
//        final CountDownLatch latch = new CountDownLatch(5);
        final CyclicBarrier barrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "is waiting");
//                    latch.countDown();
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " is end");
                }
            }).start();
        }
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
        System.out.println("thread is finish");

    }
}
