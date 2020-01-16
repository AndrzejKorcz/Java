package com.standalone.utils.sqldbconnection;

import java.sql.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Log
@Builder
@Getter
public class SqlConnection implements AutoCloseable {
    private Connection connection;
    private  String driver;
    private  String connectionString;

    private String hostName;
    private String userId;
    private String password;

    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(connectionString);
    }

    public void close() throws Exception {
        connection.close();
        log.info("Connection to database has been closed.");
    }
}
