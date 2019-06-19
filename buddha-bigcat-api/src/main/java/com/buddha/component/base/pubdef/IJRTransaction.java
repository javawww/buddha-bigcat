package com.buddha.component.base.pubdef;

import java.util.Map;

public interface IJRTransaction {
    //本地数据库连接
    Map<String, JRTransConnGroup> getLocalConns();
    String getTransactionId();
    void commit() throws Exception;
    void rollback() throws Exception;
}
