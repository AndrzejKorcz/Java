package com.standalone.ibmi.yaml.models;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectionTest {
    private static final String PASSWORD = "password";
    private static final String USERNAME = "user";
    private static final String HOST = "ip";

    private static Connection connection;

    @BeforeClass
    public static void setUp() {

        connection = new Connection();
        connection.setHostName(HOST);
        connection.setUserId(USERNAME);
        connection.setPassword(PASSWORD);
    }

    @AfterClass
    public static void cleanUp(){
        connection = null;
    }


    @Test
    public void connectionParamsNotNull(){
        connection.setHostName(HOST);
        assertNotNull("HostName parameter is not null", connection.getHostName());
        connection.setUserId(USERNAME);
        assertNotNull("UserId parameter is not null", connection.getUserId());
        connection.setPassword(PASSWORD);
        assertNotNull("Password parameter is not null", connection.getPassword());
    }


}