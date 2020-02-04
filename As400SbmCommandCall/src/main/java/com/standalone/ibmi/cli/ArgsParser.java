package com.standalone.ibmi.cli;

import com.standalone.utils.LogFile;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.cli.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.logging.Level;

@Builder
@Getter
public class ArgsParser {
    public static final boolean REQUIRED = true;

    private Options options;
    private Option cmd;
    private String command;

    private void setUpOptions() {
        options = new Options();

        cmd.setRequired(REQUIRED);
        cmd.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(cmd);
    }

    public boolean parseArgs(String[] args) throws IOException {
        setUpOptions();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine commandLine;

        try {
            commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            LogFile.writeLog(Level.SEVERE, "Error in parameters! " + e.getMessage());
            formatter.printHelp("ibmicbmcmd", options);
            return false;
        }

        return processCommand(commandLine);
    }

    private boolean processCommand(@NotNull CommandLine cmd) {
        command = cmd.getOptionValue("command");

        return true;
    }

}
