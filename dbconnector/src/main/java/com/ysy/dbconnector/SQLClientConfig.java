package com.ysy.dbconnector;

import android.content.Context;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * SQLClient全局配置
 * Created by Sylvester on 17/4/18.
 */
public class SQLClientConfig {

    // 默认核心池大小
    private static final int DEFAULT_CORE_SIZE = 5;
    // 最大线程数
    private static final int DEFAULT_MAX_SIZE = 10;
    // 池中空余线程存活时间
    private static final long DEFAULT_KEEP_ALIVE_TIME = 15;
    // 时间单位
    private static final TimeUnit DEFAULT_KEEP_ALIVE_TIME_UNIT = TimeUnit.MINUTES;
    // 线程池阻塞队列（默认队列长度为50）
    private static final int BLOCKING_QUEUE_SIZE = 50;
    private static BlockingQueue<Runnable> defaultQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_SIZE);

    private static final String DEFAULT_DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DEFAULT_DB_URL = "jdbc:mysql://127.0.0.1:3306/db_name";
    private static final String DEFAULT_DB_USER = "root";
    private static final String DEFAULT_DB_PW = "123456";
    private static final int DEFAULT_TIME_OUT_SECONDS = 16;

    // 上下文环境对象
    Context context;
    // 默认初始化
    int corePoolZie = DEFAULT_CORE_SIZE;
    int maxPoolSize = DEFAULT_MAX_SIZE;
    long keepAliveTime = DEFAULT_KEEP_ALIVE_TIME;
    TimeUnit keepAliveTimeUnit = DEFAULT_KEEP_ALIVE_TIME_UNIT;
    BlockingQueue<Runnable> blockingQueue = defaultQueue;

    String dbDriver = DEFAULT_DB_DRIVER;
    String dbUrl = DEFAULT_DB_URL;
    String dbUser = DEFAULT_DB_USER;
    String dbPw = DEFAULT_DB_PW;
    int timeOutSeconds = DEFAULT_TIME_OUT_SECONDS;

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

    public SQLClientConfig keepAliveTimeUnit(TimeUnit keepAliveTimeUnit) {
        this.keepAliveTimeUnit = keepAliveTimeUnit;
        return this;
    }

    public SQLClientConfig blockingQueue(BlockingQueue<Runnable> blockingQueue) {
        this.blockingQueue = blockingQueue;
        return this;
    }

    public SQLClientConfig dbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
        return this;
    }

    public SQLClientConfig dbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
        return this;
    }

    public SQLClientConfig dbUser(String dbUser) {
        this.dbUser = dbUser;
        return this;
    }

    public SQLClientConfig dbPw(String dbPw) {
        this.dbPw = dbPw;
        return this;
    }

    public SQLClientConfig timeOutSeconds(int timeOutSeconds) {
        this.timeOutSeconds = timeOutSeconds;
        return this;
    }
}
