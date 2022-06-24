package com.example.threadpool;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
public class JdkThreadPoolApplicationTests {

    @Test
    public void jdkTest() throws InterruptedException {
        String name = "jdk****";
        BlockingQueue workQueue = new ArrayBlockingQueue(300);
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
        java.util.concurrent.ThreadPoolExecutor executor = new java.util.concurrent.ThreadPoolExecutor(5, 300, 60000, TimeUnit.MILLISECONDS, workQueue, rejectedExecutionHandler);
        for (int i = 0; i < 300; i++) {
            try {
                executor.execute(() -> {
                    logJdkStatus(executor, "创建任务");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Thread.currentThread().join();

    }


    private static void logJdkStatus(java.util.concurrent.ThreadPoolExecutor executor, String name) {
        ArrayBlockingQueue queue = (ArrayBlockingQueue) executor.getQueue();
        System.out.println(Thread.currentThread().getName() + "-----" +
                name + "-:" +
                "核心线程数:" + executor.getCorePoolSize() +
                "\t活动线程数:" + executor.getActiveCount() +
                "\t最大线程数:" + executor.getMaximumPoolSize() +
                "\t总任务数:" + executor.getTaskCount() +
                "\t当前排队线程数:" + queue.size() +
                "\t队列剩余大小:" + queue.remainingCapacity());
    }

    private final Lock lock = new ReentrantLock();
    private int count;

    public void add(int n) {
        lock.lock();
        try {
            count += n;
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void lockTest(){
        add(8);
    }
}
