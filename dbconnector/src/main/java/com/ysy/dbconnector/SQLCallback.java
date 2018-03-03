package com.ysy.dbconnector;

/**
 * Created by Sylvester on 17/4/18.
 */

public interface SQLCallback<R> {
    void onSuccess(R res);

    void onFail(int errorCode);
}
