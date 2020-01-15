package com.standalone.ibmi.yaml;

import com.standalone.ibmi.yaml.configuration.Configuration;
import com.standalone.ibmi.yaml.constants.Constants;
import lombok.extern.java.Log;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Log
public class YamlConfigRunner {

    private YamlConfigRunner() {
    }

    public static Configuration readConfigYaml() throws IOException {
        Configuration configSetting = null;
        try (InputStream configIs = Files.newInputStream(Paths.get(Constants.CONFIG_FILEPATH))) {
            Yaml yaml = new Yaml(new Constructor(Configuration.class));
            configSetting = yaml.loadAs(configIs, Configuration.class);
        } catch (Exception e) {
            log.severe(e.getMessage());
        }

        return configSetting;
    }

    public static void writeConfigYaml(Configuration configSetting) {
        try {
            Yaml yaml = new Yaml();
            String output = yaml.dump(configSetting);
            byte[] sourceByte = output.getBytes();
            writeOutputStream(sourceByte);
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
    }

    private static void writeOutputStream(byte[] sourceByte) throws IOException {
            File file = new File(Constants.CONFIG_FILEPATH);
            try(FileOutputStream fileOutputStream = new FileOutputStream(String.valueOf(file));)
            {
                fileOutputStream.write(sourceByte);
            }  catch (Exception e) {
                log.severe(e.getMessage());
            }
        }

}
