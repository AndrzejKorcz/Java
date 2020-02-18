package com.standalone.ibmi;

import com.ibm.as400.access.AS400SecurityException;
import com.standalone.ibmi.cli.ArgsParser;
import com.standalone.ibmi.connector.As400Connection;
import com.standalone.ibmi.integratedfilesystem.As400ifs;
import com.standalone.ibmi.params.EnumParams;
import com.standalone.ibmi.service.ConnectionService;
import com.standalone.utils.LogFile;
import org.apache.commons.cli.Option;

import java.io.IOException;
import java.util.logging.Level;

public class App 
{
    public static void main( String[] args ) throws IOException, AS400SecurityException {
        LogFile.writeLog(Level.INFO, "Start process work on IFS.");
        ArgsParser argsParser = ArgsParser.builder()
                .action(new Option("a", "action", true, "action on IBMi IFS"))
                .local(new Option("l", "local", true, "path to local"))
                .remote(new Option("r", "remote", true, "path to remote"))
                .clean(new Option("c", "clean", false, "clean objects on IBMi IFS"))
                .build();

       if (!argsParser.parseArgs(args)) {
            System.exit(1);
        }

        ConnectionService connectionService = new ConnectionService();
        As400Connection as400Connection = connectionService.as400Connection();

        As400ifs as400ifs = As400ifs.builder()
                .as400Connection(as400Connection)
                .localFilePathNames(argsParser.getLocalFilePaths())
                .ifsFilePathNames(argsParser.getRemoteFilePaths())
                .build();

        LogFile.writeLog(Level.INFO, "Action: " + argsParser.getEnumAction().getActionType());

        if ( (argsParser.getEnumAction() == EnumParams.Action.CPYTXTFROMIFS) ||
             (argsParser.getEnumAction() == EnumParams.Action.CPYBYTEFROMIFS) ) {
               as400ifs.readIFS(argsParser.getEnumAction(), argsParser.getCleanUp());
        }
        else if (argsParser.getEnumAction() == EnumParams.Action.CPYTOIFS) {
            as400ifs.writeIFS();
        }

        connectionService.closeAs400Connection(as400Connection);

        LogFile.writeLog(Level.INFO, "End process work on IFS.");
        System.exit(0);
    }

}
