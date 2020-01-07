package com.santander.ibmi.cli;

import com.santander.ibmi.params.EnumParams;
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

    private Option action;
    private Option local;
    private Option remote;

    private EnumParams.Action enumAction;
    private String localFilePath;
    private String remoteFilePath;

    private void setUpOptions() {
        options = new Options();

        action.setRequired(REQUIRED);
        options.addOption(action);

        local.setRequired(REQUIRED);
        options.addOption(local);

        remote.setRequired(REQUIRED);
        options.addOption(remote);
    }

    public boolean parseArgs(String[] args) {
        setUpOptions();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            log.severe("Error in parameters! " + e.getMessage());
            formatter.printHelp("ibmiifs", options);
            return false;
        }

        return processCommand(cmd);
    }

    private boolean processCommand(@NotNull CommandLine cmd) {
        localFilePath = cmd.getOptionValue("local");
        remoteFilePath = cmd.getOptionValue("remote");

        enumAction = EnumParams.Action.get(cmd.getOptionValue("action"));

        return true;
    }
}
