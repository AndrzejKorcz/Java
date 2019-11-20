package com.santander.ibmi.configuration;

import com.santander.ibmi.models.Connection;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ConfigurationTest {

    private static Connection connection;

    @BeforeClass
    public static void setUp(){
        connection = new Connection();
        String HOST = "ip";
        connection.setHostName(HOST);
        String USERNAME = "user";
        connection.setUserId(USERNAME);
        String PASSWORD = "password";
        connection.setPassword(PASSWORD);
    }

    @AfterClass
    public static void cleanUp(){
        connection = null;
    }

    @Test
    public void configConnectionNotNull(){
        Configuration config = new Configuration();
        config.setConnection(connection);
        assertNotNull(config.getConnection());
    }


}