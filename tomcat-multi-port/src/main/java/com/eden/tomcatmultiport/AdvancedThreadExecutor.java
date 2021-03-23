package com.eden.tomcatmultiport;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardThreadExecutor;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class AdvancedThreadExecutor extends StandardThreadExecutor {
    @Override
    protected void startInternal() throws LifecycleException {
        super.namePrefix = "my-tomcat-";
        super.maxThreads = 10;
        super.minSpareThreads = 5;
        super.maxIdleTime = 3;

        super.startInternal();
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                //add stat
                throw new RejectedExecutionException("Task " + r.toString() +
                        " rejected from " +
                        executor.toString());
            }
        });
    }
}
