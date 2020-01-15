package com.standalone.ibmi.command;

import com.standalone.ibmi.connector.As400Connection;
import com.standalone.ibmi.service.ConnectionService;

import lombok.extern.java.Log;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

@Log
public class As400CommandTest {

    @Test
    public void runCommandWithSuccess() throws IOException {
        ConnectionService connectionService = new ConnectionService();
        As400Connection as400Connection = connectionService.as400Connection();
        String copyStreamFileTemplate = "CPYFRMSTMF FROMSTMF('%s') TOMBR('/qsys.lib/%s.lib/%s.file/%s.mbr') MBROPT(*REPLACE) CVTDTA(*AUTO)";
        String[] commands = {String.format(copyStreamFileTemplate, "/Home/KORCZA03/AKO666T.RPGLE", "KORCZA03", "TSTSRC", "AKO666T")};
        As400Command as400Command = As400Command.builder()
                .as400Connection(as400Connection)
                .commands(commands)
                .build();
        assertTrue("Procces end with success", as400Command.runCommand());
        connectionService.closeAs400Connection(as400Connection);
    }

}