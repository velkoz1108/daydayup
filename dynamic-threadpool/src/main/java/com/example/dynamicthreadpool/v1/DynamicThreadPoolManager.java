package com.example.dynamicthreadpool.v1;

import com.example.dynamicthreadpool.v1.config.DtpProperties;
import com.example.dynamicthreadpool.v1.enums.QueueTypeEnum;
import com.example.dynamicthreadpool.v1.enums.RejectedExecutionHandlerEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.*;

@Slf4j
@Component
public class DynamicThreadPoolManager {

    @Autowired
    private DtpProperties dtpProperties;

    /**
     * 存储线程池对象，Key:名称 Value:对象
     */
    private Map<String, ThreadPoolExecutor> threadPoolExecutorMap = new HashMap<>();

    @PostConstruct
    public void init() {
        createThreadPoolExecutor(dtpProperties);
    }


    /**
     * 创建线程池
     * @param threadPoolProperties
     */
    public void createThreadPoolExecutor(DtpProperties threadPoolProperties) {
        threadPoolProperties.getExecutors().forEach(executor -> {
            if (!threadPoolExecutorMap.containsKey(executor.getThreadPoolName())) {
                ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                        executor.getCorePoolSize(),
                        executor.getMaximumPoolSize(),
                        executor.getKeepAliveTime(),
                        TimeUnit.MILLISECONDS,
                        getBlockingQueue(executor.getQueueType(), executor.getQueueCapacity(), executor.isFair()),
                        getRejectedExecutionHandler(executor.getRejectedExecutionType(), executor.getThreadPoolName()));

                threadPoolExecutorMap.put(executor.getThreadPoolName(), threadPoolExecutor);
            }
        });
    }

    /**
     * 获取拒绝策略
     * @param rejectedExecutionType
     * @param threadPoolName
     * @return
     */
    private RejectedExecutionHandler getRejectedExecutionHandler(String rejectedExecutionType, String threadPoolName) {
        if (RejectedExecutionHandlerEnum.CALLER_RUNS_POLICY.getType().equals(rejectedExecutionType)) {
            return new ThreadPoolExecutor.CallerRunsPolicy();
        }
        if (RejectedExecutionHandlerEnum.DISCARD_OLDEST_POLICY.getType().equals(rejectedExecutionType)) {
            return new ThreadPoolExecutor.DiscardOldestPolicy();
        }
        if (RejectedExecutionHandlerEnum.DISCARD_POLICY.getType().equals(rejectedExecutionType)) {
            return new ThreadPoolExecutor.DiscardPolicy();
        }
        ServiceLoader<RejectedExecutionHandler> serviceLoader = ServiceLoader.load(RejectedExecutionHandler.class);
        Iterator<RejectedExecutionHandler> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            RejectedExecutionHandler rejectedExecutionHandler = iterator.next();
            String rejectedExecutionHandlerName = rejectedExecutionHandler.getClass().getSimpleName();
            if (rejectedExecutionType.equals(rejectedExecutionHandlerName)) {
                return rejectedExecutionHandler;
            }
        }
        return new ThreadPoolExecutor.CallerRunsPolicy();
    }


    /**
     * 获取阻塞队列
     * @param queueType
     * @param queueCapacity
     * @param fair
     * @return
     */
    private BlockingQueue getBlockingQueue(String queueType, int queueCapacity, boolean fair) {
//        if (!QueueTypeEnum.exists(queueType)) {
//            throw new RuntimeException("队列不存在 " + queueType);
//        }
        if (QueueTypeEnum.ARRAY_BLOCKING_QUEUE.getType().equals(queueType)) {
            return new ArrayBlockingQueue(queueCapacity);
        }
        if (QueueTypeEnum.SYNCHRONOUS_QUEUE.getType().equals(queueType)) {
            return new SynchronousQueue(fair);
        }
        if (QueueTypeEnum.PRIORITY_BLOCKING_QUEUE.getType().equals(queueType)) {
            return new PriorityBlockingQueue(queueCapacity);
        }
        if (QueueTypeEnum.DELAY_QUEUE.getType().equals(queueType)) {
            return new DelayQueue();
        }
        if (QueueTypeEnum.LINKED_TRANSFER_DEQUE.getType().equals(queueType)) {
            return new LinkedTransferQueue();
        }
        return new LinkedBlockingDeque(queueCapacity);

    }


    /**
     * 刷新线程池
     */
    public void refreshThreadPoolExecutor(boolean isWaitConfigRefreshOver) {
        try {
            if (isWaitConfigRefreshOver) {
                // 等待Nacos配置刷新完成
                Thread.sleep( 1000);
            }
        } catch (InterruptedException e) {

        }
        dtpProperties.getExecutors().forEach(executor -> {
            ThreadPoolExecutor threadPoolExecutor = threadPoolExecutorMap.get(executor.getThreadPoolName());
            threadPoolExecutor.setCorePoolSize(executor.getCorePoolSize());
            threadPoolExecutor.setMaximumPoolSize(executor.getMaximumPoolSize());
            threadPoolExecutor.setKeepAliveTime(executor.getKeepAliveTime(), TimeUnit.MILLISECONDS);
            threadPoolExecutor.setRejectedExecutionHandler(getRejectedExecutionHandler(executor.getRejectedExecutionType(), executor.getThreadPoolName()));
            BlockingQueue<Runnable> queue = threadPoolExecutor.getQueue();

        });
    }

    public ThreadPoolExecutor getThreadPoolExecutor(String threadPoolName) {
        ThreadPoolExecutor threadPoolExecutor = threadPoolExecutorMap.get(threadPoolName);
        if (threadPoolExecutor == null) {
            throw new NullPointerException("找不到线程池 " + threadPoolName);
        }
        return threadPoolExecutor;
    }


}
