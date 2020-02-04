package com.standalone.ibmi;

import com.standalone.ibmi.cli.ArgsParser;
import com.standalone.ibmi.command.As400SbmCommand;
import com.standalone.ibmi.connector.As400Connection;
import com.standalone.ibmi.service.ConnectionService;
import com.standalone.utils.LogFile;
import org.apache.commons.cli.Option;

import java.io.IOException;
import java.util.logging.Level;

public class App
{
    public static void main( String[] args ) throws IOException {
        LogFile.writeLog(Level.INFO, "Start IBMi submitted command process.");
        ArgsParser argsParser = ArgsParser.builder()
                .cmd(new Option("c", "command", true, "submitted command on IBMi IFS"))
                .build();

        if (!argsParser.parseArgs(args)) {
            System.exit(1);
        }

        ConnectionService connectionService = new ConnectionService();
        As400Connection as400Connection = connectionService.as400Connection();

        As400SbmCommand as400Command = As400SbmCommand.builder()
                .as400Connection(as400Connection)
                .command(argsParser.getCommand())
                .build();

        boolean result = as400Command.runCommand();

        connectionService.closeAs400Connection(as400Connection);

        LogFile.writeLog(Level.INFO, "End IBMi submitted command process.");
        System.exit(result ? 0 : 1);
    }
}