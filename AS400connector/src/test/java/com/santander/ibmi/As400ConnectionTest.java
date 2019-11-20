package com.santander.ibmi;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class As400ConnectionTest {
    private static String PASSWORD = "password";
    private static String USERNAME = "user";
    private static String CROAS = "ip";

    private static As400Connection as400Connection;

    @BeforeClass
    public static void setUp() {
          as400Connection = As400Connection.builder()
                .hostName(CROAS)
                .userId(USERNAME)
                .password(PASSWORD)
                .build();
    }

    @Test
    public void as400ConnectionNotNull() {
        assertNotNull("Verify that as400Connection is NOT null", as400Connection);
    }

    @Test
    public void as400ConnectionParametersNotNull() {
        assertNotNull("Verify that host name parameter is not null", as400Connection.getHostName());
        assertNotNull("Verify that user id parameter is not null", as400Connection.getUserId());
        assertNotNull("Verify that password parameter is not null", as400Connection.getPassword());
    }

    @Test
    public void as400ConnectionParameterAs400Null(){
        assertNull("As400 parameter is null", as400Connection.getAs400());
    }

    @Test
    public void as400ConnectionParameterAs400NotNull(){
        as400Connection.setAs400(null);
        as400Connection.openConnection();
        assertNotNull("As400 parameter is null", as400Connection.getAs400());
        as400Connection.closeConnection();
    }

    @Test
    public void as400ConnectionSetParameters(){
         as400Connection.setHostName(CROAS);
         assertEquals(CROAS, as400Connection.getHostName());

         as400Connection.setUserId(USERNAME);
         assertEquals(USERNAME, as400Connection.getUserId());

         as400Connection.setPassword(PASSWORD);
         assertEquals(PASSWORD, as400Connection.getPassword());
    }

}
