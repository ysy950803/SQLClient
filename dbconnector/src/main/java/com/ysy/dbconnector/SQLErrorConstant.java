package com.ysy.dbconnector;

/**
 * 错误信息
 * Created by Sylvester on 17/4/18.
 */
public class SQLErrorConstant {

    public static int ERROR_TIMEOUT = 0;
    public static int ERROR_NETWORK = 1;
    public static int ERROR_SYSTEM = 2;
    public static int ERROR_DUPLICATE = 3;

    public static String getErrorMsg(int errorCode) {
        String msg = null;
        switch (errorCode) {
            case 0:
                msg = "Timeout to connect.";
                break;
            case 1:
                msg = "Network exception.";
                break;
            case 2:
                msg = "System Error.";
                break;
            case 3:
                msg = "Duplicate when inserting.";
                break;
            default:
                break;
        }
        return msg;
    }
}
