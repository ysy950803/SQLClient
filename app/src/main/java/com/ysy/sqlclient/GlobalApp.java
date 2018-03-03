package com.ysy.sqlclient;

import android.app.Application;

import com.ysy.dbconnector.SQLClient;
import com.ysy.dbconnector.SQLClientConfig;

/**
 * SQLClient的初始化操作建议都在Application中进行
 * Created by Sylvester on 18/3/3.
 */
public class GlobalApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SQLClient.init(new SQLClientConfig(this)
                .dbDriver("com.mysql.jdbc.Driver")
                .dbUrl("jdbc:mysql://127.0.0.1:3306/db_name")
                .dbUser("root")
                .dbPw("123456")
                .timeOutSeconds(16));
    }
}
