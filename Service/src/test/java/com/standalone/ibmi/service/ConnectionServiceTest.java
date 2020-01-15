package com.standalone.ibmi.service;

import com.standalone.ibmi.connector.As400Connection;
import com.standalone.ibmi.utils.ConsoleSrc;
import com.standalone.ibmi.yaml.constants.Constants;
import org.junit.AfterClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectionServiceTest {

    @AfterClass
    public static void cleanUp() {
        File file = new File(Constants.CONFIG_FILEPATH);
        if (file.exists()) {
            file.delete();
        }
    }
    @Test
    public void as400ConnectionNotNull() throws IOException {

        ConnectionService connectionService = new ConnectionService();
        connectionService.setConsoleSrc(mock(ConsoleSrc.class));
        when(connectionService.getConsoleSrc().readUserName()).thenReturn("userId");
        when(connectionService.getConsoleSrc().readHostName()).thenReturn("host");
        when(connectionService.getConsoleSrc().readPassword()).thenReturn("password");

        As400Connection as400Connection = connectionService.as400Connection();
        assertNotNull(as400Connection.getAs400());
        connectionService.closeAs400Connection(as400Connection);
    }


}