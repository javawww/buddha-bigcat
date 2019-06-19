package com.buddha.component.base.pubdef;

import org.apache.tomcat.jdbc.pool.DataSource;

import java.io.Serializable;
import java.sql.Connection;

public class JRTransConnection implements Serializable {
    public JRDatabaseType getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(JRDatabaseType databaseType) {
        this.databaseType = databaseType;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private JRDatabaseType databaseType;
    private DataSource dataSource;
    private Connection connection;
}
