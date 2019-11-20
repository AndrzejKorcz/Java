package com.santander.ibmi;

import com.santander.ibmi.configuration.Configuration;
import lombok.extern.java.Log;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Log
class YamlConfigRunner {

    public Configuration readYaml(String filePath) {
        Yaml yaml = new Yaml();
        Configuration config = null;

        try( InputStream in = Files.newInputStream( Paths.get( filePath) ) ) {
            config = yaml.loadAs( in, Configuration.class );
        } catch (IOException e) {
             log.severe(e.getMessage());
        }

        return config;
    }
}
