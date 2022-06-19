package com.example.threadpool;

import org.apache.tomcat.util.threads.TaskQueue;
import org.apache.tomcat.util.threads.TaskThreadFactory;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class TomcatThreadPoolApplicationTests {

    @Test
    public void tomcatTest() throws InterruptedException {
        String namePrefix = "why not only jishu";
        boolean daemon = true;
        TaskQueue taskqueue = new TaskQueue(300);
        TaskThreadFactory tf = new TaskThreadFactory(namePrefix, daemon, Thread.NORM_PRIORITY);
        org.apache.tomcat.util.threads.ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                150, 60000, TimeUnit.MILLISECONDS, taskqueue, tf);
        taskqueue.setParent(executor);
        for (int i = 0; i < 300; i++) {
            try {
                executor.execute(() -> {
                    logTomcatStatus(executor, "创建任务");
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

    private static void logTomcatStatus(ThreadPoolExecutor executor, String name) {
        TaskQueue queue = (TaskQueue) executor.getQueue();
        System.out.println(Thread.currentThread().getName() + "-----" +
                name + "-:" +
                "核心线程数:" + executor.getCorePoolSize() +
                "\t活动线程数:" + executor.getActiveCount() +
                "\t最大线程数:" + executor.getMaximumPoolSize() +
                "\t总任务数:" + executor.getTaskCount() +
                "\t当前排队线程数:" + queue.size() +
                "\t队列剩余大小:" + queue.remainingCapacity());
    }
}
