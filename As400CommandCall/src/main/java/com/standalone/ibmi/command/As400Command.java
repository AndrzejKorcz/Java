package com.standalone.ibmi.command;

import com.ibm.as400.access.*;
import com.standalone.ibmi.connector.As400Connection;
import com.standalone.utils.LogFile;
import lombok.Builder;

import java.io.IOException;
import java.util.logging.Level;

@Builder
public class As400Command {
    private As400Connection as400Connection;
    private String[] commands;

    public boolean runCommand() throws IOException {
        boolean cmdSuccess;
        CommandCall cmd = new CommandCall(as400Connection.getAs400());
        try {
            for (String command: commands) {
                LogFile.writeLog(Level.FINE, "Command: " + command);

                cmdSuccess = cmd.run(command);
                printMessage(cmd);
                if (!cmdSuccess) return false;
        }

        } catch (Exception e) {
            LogFile.writeLog(Level.SEVERE, "Error run IBMi commands: " + e.getMessage());
            return false;
        }

        return true;
    }

    private void printMessage(CommandCall cmd) throws InterruptedException, ErrorCompletingRequestException, AS400SecurityException, ObjectDoesNotExistException, IOException {
        AS400Message[] messageList = cmd.getMessageList();
        for (int i = 0; i < messageList.length; i++) {
            LogFile.writeLog(Level.INFO, "Message: " + messageList[i].getText());
            // Load additional message information.
            messageList[i].load();
            //Show help text.
            String helpText = messageList[i].getHelp();
            if (!helpText.isEmpty())
                LogFile.writeLog(Level.INFO, helpText);
        }
    }
}
