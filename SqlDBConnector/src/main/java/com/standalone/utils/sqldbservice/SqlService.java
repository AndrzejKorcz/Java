package com.standalone.utils.sqldbservice;

import org.jetbrains.annotations.NotNull;

import java.sql.*;

public class SqlService {
    private Statement statement;

    public SqlService(@NotNull Connection connection) throws SQLException {
        statement = connection.createStatement();
        statement.setQueryTimeout(30);
    }

    public ResultSet runQuery(String sql) throws SQLException {
        return statement.executeQuery(sql);
    }
    public void execQuery(String sql) throws SQLException {
        statement.executeUpdate(sql);
    }
}
