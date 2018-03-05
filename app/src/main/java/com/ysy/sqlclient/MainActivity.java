package com.ysy.sqlclient;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.ysy.dbconnector.SQLCallback;
import com.ysy.dbconnector.SQLClient;
import com.ysy.dbconnector.SQLEntity;
import com.ysy.dbconnector.SQLErrorConstant;
import com.ysy.dbconnector.SQLRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用例
 * Created by Sylvester on 18/3/3.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queryDataFromDB();
    }

    // Single SQL operation.
    private void queryDataFromDB() {
        SQLEntity<String> entity = new SQLEntity<>();
        entity.setSQL("select * from table_name where id = 1");
        entity.setType(SQLRequest.RequestType.SELECT);
        SQLClient.invokeStringRequest(this, entity, new SQLCallback<ResultSet>() {
            @Override
            public void onSuccess(ResultSet res) {
                // List<DataEntity> list = new ArrayList<>();
                try {
                    while (res.next()) {
                        // DataEntity data = new DataEntity();
                        String resultColumn1 = res.getString(1);
                        int resultColumn2 = res.getInt(2);
                        // ...
                        // data.setOne(resultColumn1);
                        // data.setTwo(resultColumn2);
                        // ...
                        // list.add(data);
                    }
                    // ...(some code using list)
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(int errorCode) {
                Toast.makeText(MainActivity.this, SQLErrorConstant.getErrorMsg(errorCode),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Also support multi SQL operations.
    private void updateDataToDB() {
        SQLEntity<List<String>> entity = new SQLEntity<>();
        List<String> sqls = new ArrayList<>();
        sqls.add("update ...");
        sqls.add("insert into ...");
        sqls.add("delete ...");
        entity.setSQL(sqls);
        entity.setType(SQLRequest.RequestType.MULTI_UPDATE);
        SQLClient.invokeListRequest(this, entity, new SQLCallback<List<ResultSet>>() {
            @Override
            public void onSuccess(List<ResultSet> res) {
                // Usually, res here is not useful.
                // Do something about success.
                Toast.makeText(MainActivity.this, "Update DB Successfully.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(int errorCode) {
                Toast.makeText(MainActivity.this, SQLErrorConstant.getErrorMsg(errorCode),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SQLClient.cancel(this);
    }
}
