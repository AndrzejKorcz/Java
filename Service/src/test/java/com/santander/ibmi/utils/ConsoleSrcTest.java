package com.santander.ibmi.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class ConsoleSrcTest {

    ConsoleSrc cs;

    @Before
    public void initialize(){
        cs =  mock(ConsoleSrc.class);
    }

    @Test
    public void readUserNameSuccess() {
        when(cs.readUserName()).thenReturn("userId");
        assertEquals("userId", cs.readUserName());
    }

    @Test
    public void readHostNameSuccess() {
        when(cs.readHostName()).thenReturn("host");
        assertEquals("host", cs.readHostName());
    }

    @Test
    public void readPasswordSuccess() {
        when(cs.readPassword()).thenReturn("password");
        assertEquals("password", cs.readPassword());
    }
}