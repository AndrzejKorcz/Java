package com.santander.ibmi.cli;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.java.Log;
import org.apache.commons.cli.*;
import org.jetbrains.annotations.NotNull;

@Log
@Builder
@Getter
public class ArgsParser {
    public static final boolean REQUIRED = true;

    private Options options;
    private Option cmd;
    private String[] commands;

    private void setUpOptions() {
        options = new Options();

        cmd.setRequired(REQUIRED);
        cmd.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(cmd);
    }

    public boolean parseArgs(String[] args) {
        setUpOptions();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine commandLine;

        try {
            commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            log.severe("Error in parameters! " + e.getMessage());
            formatter.printHelp("ibmicmd", options);
            return false;
        }

        return processCommand(commandLine);
    }

    private boolean processCommand(@NotNull CommandLine cmd) {
        commands = cmd.getOptionValues("command");

        return true;
    }

}
