package com.santander.ibmi.connector;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class As400ConnectionTest {

    private static As400Connection as400Connection;

    @BeforeClass
    public static void setUp() {
          as400Connection = new As400Connection("hostName", "UserId", "secret");
    }

    @AfterClass
    public static void cleanUp() {
        if (as400Connection.getAs400() != null)
        as400Connection.closeConnection();
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
    public void as400ConnectionSetParameters() {
         as400Connection.setHostName("croas");
         assertEquals("croas", as400Connection.getHostName());

         as400Connection.setUserId("user");
         assertEquals("user", as400Connection.getUserId());

         as400Connection.setPassword("secret");
         assertEquals("secret", as400Connection.getPassword());
    }

    @Test
    public void as400ConnectionNull() {
       as400Connection.closeConnection();
      as400Connection.setAs400(null);
      assertNull(as400Connection.getAs400());
    }

}
