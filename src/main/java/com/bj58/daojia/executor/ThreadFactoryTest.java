package com.bj58.daojia.executor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by pangming on 2016/11/18.
 */
public class ThreadFactoryTest {

    public static void main(String[] args) {
        ExecutorService executorService = new MyThreadPoolExecutor(10, 10, 0, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
        if(executorService instanceof MyThreadPoolExecutor){

        }
    }

    private static final class MyThreadPoolExecutor extends ThreadPoolExecutor{

        private final AtomicLong numTasks = new AtomicLong();

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
            numTasks.incrementAndGet();
        }


    }
}
