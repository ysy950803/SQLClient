package com.ysy.dbconnector;

/**
 * 请求SQL数据库的结果回调
 * Created by Sylvester on 17/4/18.
 */
public interface SQLCallback<R> {

    /**
     * 成功回调
     *
     * @param res 泛型参数，可为null，目前一共有2种类型：
     *            1. ResultSet类型；
     *            2. List<ResultSet>类型
     * @see java.sql.ResultSet
     * @see java.util.List
     */
    void onSuccess(R res);

    /**
     * 失败回调
     *
     * @param errorCode 错误码,具体对应信息参见SQLErrorConstant
     * @see com.ysy.dbconnector.SQLErrorConstant
     */
    void onFail(int errorCode);
}
