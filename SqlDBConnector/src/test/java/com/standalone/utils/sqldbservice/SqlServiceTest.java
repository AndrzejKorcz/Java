package com.standalone.utils.sqldbservice;

import com.standalone.utils.ParamsConst;
import com.standalone.utils.sqldbconnection.SqlConnection;
import lombok.extern.java.Log;
import org.junit.Test;

import java.sql.ResultSet;

import static org.junit.Assert.*;

@Log
public class SqlServiceTest {

    @Test
    public void sqlServiceNotNull() throws Exception {
        try(SqlConnection sqlConnection = SqlConnection.builder()
                .driver(ParamsConst.driver)
                .connectionString(ParamsConst.connectionString)
                .build())
        {
               sqlConnection.connect();
               SqlService sqlService = new SqlService(sqlConnection.getConnection());

               assertNotNull("Verify that sql service is NOT null", sqlService);
        }
    }

    @Test
    public void sqlServiceQuery() throws Exception {
        try(SqlConnection sqlConnection = SqlConnection.builder()
                .driver(ParamsConst.driver)
                .connectionString(ParamsConst.connectionString)
                .build())
        {
            sqlConnection.connect();
            SqlService sqlService = new SqlService(sqlConnection.getConnection());
            sqlService.execQuery("drop table if exists person");
            sqlService.execQuery("create table person (id integer, name string)");
            int rowCount = 5;
            for (int i = 0; i < rowCount; i++) {
                sqlService.execQuery(String.format("insert into person values(%d, 'user')", i));
            }

            ResultSet rs = sqlService.runQuery("select * from person");

            int numberOfRecords = 0;
            while(rs.next())
            {
                log.info(String.format("id: %d  name: %s", rs.getInt("id"), rs.getString("name")));
                numberOfRecords++;
            }
            assertEquals(rowCount, numberOfRecords);

        }
    }

}