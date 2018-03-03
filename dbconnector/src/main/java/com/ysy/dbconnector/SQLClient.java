package com.ysy.dbconnector;

import android.annotation.SuppressLint;
import android.content.Context;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQLClient主体
 * Created by Sylvester on 17/4/18.
 */
public class SQLClient {

    @SuppressLint("StaticFieldLeak")
    static SQLClientConfig config;

    // 存放每个Context对应的RequestManager
    private static Map<Context, SQLRequestManager> managerMap;

    /**
     * 初始化
     *
     * @param config 全局配置信息
     */
    public static void init(SQLClientConfig config) {
        SQLClient.config = config;
        managerMap = new HashMap<>();
        // 初始化线程池
        SQLRequestThreadPool.init();
    }

    /**
     * 执行SQL请求
     *
     * @param context  发起SQL请求的Context
     * @param callBack SQL请求执行完毕后的回调接口
     */
    public static SQLRequest invokeStringRequest(Context context,
                                                 SQLEntity<String> entity, SQLCallback<ResultSet> callBack) {
        // 获取该Context对应的RequestManager对象，并创建SQLRequest对象
        SQLRequestManager manager = checkRequestManager(context, true);
        SQLRequest request = manager.createStringRequest(entity, callBack);
        // 执行请求
        SQLRequestThreadPool.execute(request);
        return request;
    }

    public static SQLRequest invokeListRequest(Context context,
                                               SQLEntity<List<String>> entity, SQLCallback<List<ResultSet>> callBack) {
        SQLRequestManager manager = checkRequestManager(context, true);
        SQLRequest request = manager.createListRequest(entity, callBack);
        SQLRequestThreadPool.execute(request);
        return request;
    }

    /**
     * 取消指定Context中发起的所有HTTP请求
     */
    public static void cancelAllRequest(Context context) {
        SQLRequestManager requestManager = checkRequestManager(context, false);
        if (requestManager != null)
            requestManager.cancelAllRequest();
    }

    /**
     * 取消线程池中整个阻塞队列所有SQL操作请求
     */
    public static void cancelAllRequest() {
        SQLRequestThreadPool.removeAllTask();
    }

    /**
     * 取消指定Context中未执行的请求
     */
    public static void cancelBlockingRequest(Context context) {
        SQLRequestManager requestManager = checkRequestManager(context, false);
        if (requestManager != null)
            requestManager.cancelBlockingRequest();
    }

    /**
     * 取消指定请求
     */
    public static void cancelDesignatedRequest(Context context, SQLRequest request) {
        checkRequestManager(context, false).cancelDesignatedRequest(request);
    }

    public static void cancel(Context context) {
        cancelBlockingRequest(context);
        cancelAllRequest(context);
        cancelAllRequest();
        managerMap.remove(context);
    }

    /**
     * 访问Context对应的RequestManager对象
     *
     * @param createNew 当RequestManager对象为null时是否创建新的RequestManager对象
     */
    private static SQLRequestManager checkRequestManager(Context context, boolean createNew) {
        SQLRequestManager manager;
        if ((manager = managerMap.get(context)) == null) {
            if (createNew) {
                manager = new SQLRequestManager();
                managerMap.put(context, manager);
            } else {
                throw new NullPointerException(context.getClass().getSimpleName() + "'s RequestManager is null!");
            }
        }
        return manager;
    }

    /**
     * 关闭线程池，并等待任务执行完成，不接受新任务
     */
    public static void shutdown() {
        SQLRequestThreadPool.shutdown();
    }

    /**
     * 关闭，立即关闭，并挂起所有正在执行的线程，不接受新任务
     */
    public static void shutdownRightnow() {
        SQLRequestThreadPool.shutdownRightNow();
    }
}
