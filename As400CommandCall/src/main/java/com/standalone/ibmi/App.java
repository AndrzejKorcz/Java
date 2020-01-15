package com.standalone.ibmi;

import com.standalone.ibmi.cli.ArgsParser;
import com.standalone.ibmi.command.As400Command;
import com.standalone.ibmi.connector.As400Connection;
import com.standalone.ibmi.service.ConnectionService;
import com.standalone.utils.LogFile;
import org.apache.commons.cli.Option;

import java.io.IOException;
import java.util.logging.Level;

public class App 
{
    public static void main( String[] args ) throws IOException {
        LogFile.writeLog(Level.INFO, "Start IBMi command process.");
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

        LogFile.writeLog(Level.INFO, "End IBMi command process.");
        System.exit(result ? 0 : 1);
    }
}
