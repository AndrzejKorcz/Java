package com.santander.ibmi;

import com.santander.ibmi.configuration.Configuration;
import org.junit.Test;

import static org.junit.Assert.*;

public class YamlConfigRunnerTest {

    @Test
    public void readYaml(){
       YamlConfigRunner yamlConfigRunner = new YamlConfigRunner();
        Configuration config = yamlConfigRunner.readYaml("config/config.yml");
        assertNotNull(config);
    }

}