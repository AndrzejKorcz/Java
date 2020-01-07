package com.santander.ibmi;

import com.santander.ibmi.cli.ArgsParser;
import com.santander.ibmi.connector.As400Connection;
import com.santander.ibmi.integratedfilesystem.As400ifs;
import com.santander.ibmi.params.EnumParams;
import com.santander.ibmi.service.ConnectionService;
import lombok.extern.java.Log;
import org.apache.commons.cli.Option;

import java.io.IOException;

@Log
public class App 
{
    public static void main( String[] args ) throws IOException {
        log.info("Start process");
        ArgsParser argsParser = ArgsParser.builder()
                .action(new Option("a", "action", true, "action on IBMi IFS"))
                .local(new Option("l", "local", true, "path to local"))
                .remote(new Option("r", "remote", true, "path to remote"))
                .build();

       if (!argsParser.parseArgs(args)) {
            System.exit(1);
        }

        ConnectionService connectionService = new ConnectionService();
        As400Connection as400Connection = connectionService.as400Connection();

        As400ifs as400ifs = As400ifs.builder()
                .as400Connection(as400Connection)
                .localFilePathName(argsParser.getLocalFilePath())
                .ifsFilePathName(argsParser.getRemoteFilePath())
                .build();

        log.info(argsParser.getEnumAction().getActionType());
        if (argsParser.getEnumAction() == EnumParams.Action.CPYFROMFS) {
            as400ifs.readIFS();
        }
        else if (argsParser.getEnumAction() == EnumParams.Action.CPYTOIFS) {
            as400ifs.writeIFS();
        }
        connectionService.closeAs400Connection(as400Connection);

        log.info("End of process");
        System.exit(0);
    }

}
