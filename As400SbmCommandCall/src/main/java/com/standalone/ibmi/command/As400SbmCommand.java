package com.standalone.ibmi.command;


import com.ibm.as400.access.*;
import com.standalone.ibmi.connector.As400Connection;
import com.standalone.utils.LogFile;
import lombok.Builder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
public class As400SbmCommand {
    private As400Connection as400Connection;
    private String command;

    public boolean runCommand() throws IOException {
        boolean cmdSuccess;
        CommandCall cmd = new CommandCall(as400Connection.getAs400());
        try {
            LogFile.writeLog(Level.FINE, "Command: " + command);

                cmdSuccess = cmd.run(command);
                printMessage(cmd);
                if (!cmdSuccess) return false;

        } catch (Exception e) {
            LogFile.writeLog(Level.SEVERE, "Error run IBMi commands: " + e.getMessage());
            return false;
        }

        return true;
    }

    private void printMessage(CommandCall cmd) throws IOException, InterruptedException, ErrorCompletingRequestException, AS400SecurityException, ObjectDoesNotExistException {
        AS400Message[] messageList = cmd.getMessageList();
        String job;
        String jobStatus;

        for (int i = 0; i < messageList.length; i++) {
            LogFile.writeLog(Level.INFO, "Message: " + messageList[i].getText());
            // Load additional message information.
            messageList[i].load();
            job = getJobFromMessage(messageList[i].getText());
            if (!job.isEmpty()) {
                jobStatus = getJobStatus(job);
                LogFile.writeLog(Level.INFO, "Job: " + job + " status: " + jobStatus);
                if (jobStatus.equals(Job.ACTIVE_JOB_STATUS_WAIT_MESSAGE)) throw new IllegalArgumentException("Job in *MSGW");
            }
            //Show help text.
            String helpText = messageList[i].getHelp();
            if (!helpText.isEmpty())
                LogFile.writeLog(Level.INFO, helpText);
        }
    }

    private String getJobFromMessage(String text) {
        String jobPattern = "\\d{6,}\\/\\w{3,}\\/\\w{3,}";
        Pattern expression = Pattern.compile(jobPattern);
        Matcher matcher = expression.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

    private  String getJobStatus(String jobText) throws InterruptedException, ErrorCompletingRequestException, AS400SecurityException, ObjectDoesNotExistException, IOException {
        final byte jobNumber = 0;
        final byte userName = 1;
        final byte jobName = 2;

        String[] results = jobText.split("/s*");

        Job job = new Job(as400Connection.getAs400(), results[jobName], results[userName], results[jobNumber]);
        while (job.getStatus().equals(Job.JOB_STATUS_ACTIVE))
        {
            // Wait a while.
            Thread.sleep(1000);
            // Refresh the attribute values.
            job.loadInformation();
        }

        return job.getStatus();
    }

}
