package com.standalone.ibmi.service;

import com.standalone.ibmi.connector.As400Connection;
import com.santander.ibmi.scrambler.Scrambler;
import com.standalone.ibmi.utils.ConsoleSrc;
import com.santander.ibmi.yaml.YamlConfigRunner;
import com.santander.ibmi.yaml.configuration.Configuration;
import com.santander.ibmi.yaml.constants.Constants;
import com.santander.ibmi.yaml.models.Connection;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;

@Log
public class ConnectionService {
    private Scrambler scrambler;
    private Configuration config;
    @Setter
    @Getter
    ConsoleSrc consoleSrc ;

    public ConnectionService() {
        consoleSrc = new ConsoleSrc();
    }

    public As400Connection as400Connection() throws IOException {
        getConfig();
        return new As400Connection(config.getConnection().getHostName(), config.getConnection().getUserId(), scrambler.decode(config.getConnection().getPassword()));
    }

    public void closeAs400Connection(As400Connection as400Connection) {
        if (as400Connection.getAs400() != null)
            as400Connection.close();
    }

    private void getConfig() throws IOException {
        scrambler = new Scrambler();
        File file = new File(Constants.CONFIG_FILEPATH);
        if (!file.exists()) {
            log.severe("Config file doesn't exist");
            log.info("Process will create config file.");
            config = createConfigYaml();
            YamlConfigRunner.writeConfigYaml(config);
        } else {
            config = YamlConfigRunner.readConfigYaml();
        }
    }

    private Configuration createConfigYaml() {
        Connection connection = new Connection();
        connection.setHostName(consoleSrc.readHostName());
        connection.setUserId(consoleSrc.readUserName());
        connection.setPassword(scrambler.encode(consoleSrc.readPassword()));
        Configuration configuration = new Configuration();
        configuration.setConnection(connection);
        return configuration;
    }
}
