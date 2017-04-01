package com.bj58.daojia.executor;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by pangming on 2016/11/18.
 */
public class ExecutorTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        final Random random = new Random();
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
//                System.out.println(Thread.currentThread() + " is call");
                int i = random.nextInt(10)+1;
                Thread.sleep(i*1000);
                return Thread.currentThread().getName();
            }
        };
        List<Future<String>> futureList = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            Future submit = executor.submit(callable);
            futureList.add(submit);
        }

        for (Future<String> future : futureList) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        if(executor.isShutdown()){
            System.out.println("executor is shutdown");
        }
        System.out.println("task is over");
    }

    private void completionService(){
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
        final Random random = new Random();
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
//                System.out.println(Thread.currentThread() + " is call");
                int i = random.nextInt(10)+1;
                Thread.sleep(i*1000);
                return Thread.currentThread().getName();
            }
        };
        for (int i = 0; i < 10; i++) {
            completionService.submit(callable);
        }

        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(completionService.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(executor.isShutdown()){
            System.out.println("executor is shutdown");
        }
        System.out.println("task is over");
    }
}
