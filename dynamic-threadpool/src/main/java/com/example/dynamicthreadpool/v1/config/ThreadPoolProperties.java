package com.example.dynamicthreadpool.v1.config;

import com.example.dynamicthreadpool.v1.constant.DynamicTpConst;
import com.example.dynamicthreadpool.v1.enums.QueueTypeEnum;
import com.example.dynamicthreadpool.v1.enums.RejectedExecutionHandlerEnum;
import lombok.Data;

@Data
public class ThreadPoolProperties {

    /**
     * Name of ThreadPool. 线程池name
     */
    private String threadPoolName;

    /**
     * CoreSize of ThreadPool. 核心线程数
     */
    private int corePoolSize = 2;

    /**
     * MaxSize of ThreadPool. 最大线程数 默认为cpu核心数
     */
    private int maximumPoolSize = DynamicTpConst.AVAILABLE_PROCESSORS;

    /**
     * When the number of threads is greater than the core,
     * this is the maximum time that excess idle threads
     * will wait for new tasks before terminating.
     */
    private int keepAliveTime = 60;


    /**
     * BlockingQueue capacity.
     */
    private int queueCapacity = 1024;



    /**
     * 队列类型
     * @see QueueTypeEnum
     */
    private String queueType = QueueTypeEnum.LINKED_BLOCKING_QUEUE.getType();


    /**
     * 拒绝策略
     * @see RejectedExecutionHandlerEnum
     */
    private String rejectedExecutionType = RejectedExecutionHandlerEnum.ABORT_POLICY.getType();


    /**
     * SynchronousQueue 是否公平策略
     */
    private boolean fair = true;
}
