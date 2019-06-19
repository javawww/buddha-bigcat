package com.buddha.component.base.model;

import java.util.List;

public class SqlAndParamWrapper {
    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    private  String sql;

    public List<ESParameter> getSqlparams() {
        return sqlparams;
    }

    public void setSqlparams(List<ESParameter> sqlparams) {
        this.sqlparams = sqlparams;
    }

    private List<ESParameter> sqlparams;
}
