package com.ysy.dbconnector;

/**
 * SQL操作数据实体
 * Created by Sylvester on 17/4/18.
 */
public class SQLEntity<S> {

    private S sql;
    private SQLRequest.RequestType type;

    public SQLEntity() {

    }

    public void setSQL(S sql) {
        this.sql = sql;
    }

    public void setType(SQLRequest.RequestType requestType) {
        this.type = requestType;
    }

    public SQLRequest.RequestType getType() {
        return type;
    }

    public S getSql() {
        return sql;
    }
}
