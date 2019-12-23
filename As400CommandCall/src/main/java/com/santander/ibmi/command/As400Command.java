package com.santander.ibmi.command;

import com.ibm.as400.access.*;
import com.santander.ibmi.connector.As400Connection;
import lombok.Builder;
import lombok.extern.java.Log;

import java.io.IOException;

@Builder
@Log
public class As400Command {
    private As400Connection as400Connection;
    private String[] commands;

    public boolean runCommand() {
        boolean cmdSuccess;
        CommandCall cmd = new CommandCall(as400Connection.getAs400());
        try {
            for (String command: commands) {
                log.info(command);
                cmdSuccess = cmd.run(command);
                printMessage(cmd);
                if (!cmdSuccess) return false;
        }

        } catch (Exception e) {
            log.severe("Error run IBMi commands: " + e.getMessage());
            return false;
        }

        return true;
    }

    private void printMessage(CommandCall cmd) throws InterruptedException, ErrorCompletingRequestException, AS400SecurityException, ObjectDoesNotExistException, IOException {
        log.info("Command: " + cmd.getCommand());
        AS400Message[] messageList = cmd.getMessageList();
        for (int i = 0; i < messageList.length; i++) {
            log.info(messageList[i].getText());
            // Load additional message information.
            messageList[i].load();
            //Show help text.
            log.info("Help: " + messageList[i].getHelp());
        }
    }
}
