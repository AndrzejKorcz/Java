package com.standalone.utils.sqldbconnection;

import com.standalone.utils.ParamsConst;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class SqlConnectorTest {

    @Test
    public void sqlConnectionNotNull() throws Exception {
        try(SqlConnection sqlConnection = SqlConnection.builder()
                .driver(ParamsConst.driver)
                .connectionString(ParamsConst.connectionString)
                .build())
        {
            sqlConnection.connect();
            assertNotNull("Verify that sql connection is NOT null", sqlConnection);
            assertEquals(ParamsConst.driver, sqlConnection.getDriver());
            assertEquals(ParamsConst.connectionString, sqlConnection.getConnectionString());
            assertNull(sqlConnection.getHostName());
            assertNull(sqlConnection.getPassword());
            assertNull(sqlConnection.getUserId());
        }
    }
    

}