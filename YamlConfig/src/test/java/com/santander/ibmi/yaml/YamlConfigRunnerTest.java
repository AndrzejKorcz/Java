package com.santander.ibmi.yaml;

import com.santander.ibmi.yaml.configuration.Configuration;
import com.santander.ibmi.yaml.constants.Constants;
import com.santander.ibmi.yaml.models.Connection;
import lombok.extern.java.Log;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

@Log
public class YamlConfigRunnerTest {

    private static File file;

    @BeforeClass
    public static void setUp() {
        file = new File(Constants.CONFIG_FILEPATH);
    }

    @AfterClass
    public static void cleanUp() {
        if (file.exists()) {
            log.info(file.getPath());
            file.delete();
        }
    }

    @Test
    public void configurationIsNull() throws IOException {
        Configuration config = YamlConfigRunner.readConfigYaml();
        assertNull(config);
    }


    @Test
    public void configurationFileExists() throws IOException {
        Configuration config = new Configuration();
        Connection connection = new Connection();
        connection.setHostName("hostName");
        connection.setUserId("userId");
        connection.setPassword("password");
        config.setConnection(connection);

        YamlConfigRunner.writeConfigYaml(config);
        Configuration config2 = YamlConfigRunner.readConfigYaml();

        assertTrue(file.exists());
        assertNotNull(config2);
        assertEquals("hostName", config2.getConnection().getHostName());
        assertEquals("userId", config2.getConnection().getUserId());
        assertEquals("password", config2.getConnection().getPassword());
    }

}