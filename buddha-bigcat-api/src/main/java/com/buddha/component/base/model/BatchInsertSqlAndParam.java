package com.buddha.component.base.model;

import java.util.List;

public class BatchInsertSqlAndParam {
    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public  String sql;

    public List<JRFieldInfo> getFields() {
        return Fields;
    }

    public void setFields(List<JRFieldInfo> fields) {
        Fields = fields;
    }

    private List<JRFieldInfo> Fields;

}
