# SQLClient
A library to help you that connect MySQL as simply as HTTP request on Android.

## Usage
1. Your app module must depend on jdbc library: https://github.com/ysy950803/SQLClient/blob/master/app/libs/mysql-connector-java-5.1.36-bin.jar
2. Download **dbconnector** module.
3. If your Android Studio is the lastest version, edit your app's **build.gradle** and add as below:
```
dependencies {
    implementation project(':dbconnector')
}
```
If your Android Studio is the old version just like 2.3, add as below:
```
dependencies {
    compile project(':dbconnector')
}
```

## Example
You can connect MySQL Databases and execute INSERT, UPDATE, SELECT, DELETE operations as using 
HttpClient to GET and POST.

```java
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
```
