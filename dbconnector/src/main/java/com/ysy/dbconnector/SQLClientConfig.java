package com.ysy.dbconnector;

import android.content.Context;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class SQLClientConfig {

    // 默认核心池大小
    private static final int DEFAULT_CORE_SIZE = 5;
    // 最大线程数
    private static final int DEFAULT_MAX_SIZE = 10;
    // 池中空余线程存活时间
    private static final long DEFAULT_KEEP_ALIVE_TIME = 15;
    // 时间单位
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.MINUTES;
    // 线程池阻塞队列（默认队列长度为50）
    private static final int BLOCKING_QUEUE_SIZE = 50;
    private static BlockingQueue<Runnable> defaultQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_SIZE);

    // 上下文环境对象
    Context context;
    // 默认初始化
    int corePoolZie = DEFAULT_CORE_SIZE;
    int maxPoolSize = DEFAULT_MAX_SIZE;
    long keepAliveTime = DEFAULT_KEEP_ALIVE_TIME;
    TimeUnit timeUnit = DEFAULT_TIME_UNIT;
    BlockingQueue<Runnable> blockingQueue = defaultQueue;

    public SQLClientConfig(Context context) {
        this.context = context;
    }

    public SQLClientConfig corePoolZie(int corePoolZie) {
        this.corePoolZie = corePoolZie;
        return this;
    }

    public SQLClientConfig maxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
        return this;
    }

    public SQLClientConfig keepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
        return this;
    }

    public SQLClientConfig timeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }

    public SQLClientConfig blockingQueue(BlockingQueue<Runnable> blockingQueue) {
        this.blockingQueue = blockingQueue;
        return this;
    }
}
