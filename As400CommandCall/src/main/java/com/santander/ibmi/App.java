package com.santander.ibmi;

import com.santander.ibmi.cli.ArgsParser;
import com.santander.ibmi.command.As400Command;
import com.santander.ibmi.connector.As400Connection;
import com.santander.ibmi.service.ConnectionService;
import lombok.extern.java.Log;
import org.apache.commons.cli.Option;

import java.io.IOException;

@Log
public class App 
{
    public static void main( String[] args ) throws IOException {
        log.info("Start IBMi command process");
        ArgsParser argsParser = ArgsParser.builder()
                .cmd(new Option("c", "command", true, "command(s) on IBMi IFS"))
                .build();

        if (!argsParser.parseArgs(args)) {
            System.exit(1);
        }

        ConnectionService connectionService = new ConnectionService();
        As400Connection as400Connection = connectionService.as400Connection();

        As400Command as400Command = As400Command.builder()
                .as400Connection(as400Connection)
                .commands(argsParser.getCommands())
                .build();

        boolean result = as400Command.runCommand();

        connectionService.closeAs400Connection(as400Connection);
        log.info("End IBMi command process");

        System.exit(result ? 0 : 1);
    }
}
