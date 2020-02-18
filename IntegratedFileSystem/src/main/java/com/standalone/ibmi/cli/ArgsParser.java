package com.standalone.ibmi.cli;

import com.standalone.ibmi.params.EnumParams;
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
    public static final boolean NOT_REQUIRED = false;

    private Options options;

    private Option action;
    private Option clean;
    private Option local;
    private Option remote;

    private EnumParams.Action enumAction;
    private String[] localFilePaths;
    private String[] remoteFilePaths;
    private Boolean cleanUp;

    private void setUpOptions() {
        options = new Options();

        action.setRequired(REQUIRED);
        options.addOption(action);

        local.setRequired(REQUIRED);
        local.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(local);

        remote.setRequired(REQUIRED);
        remote.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(remote);

        clean.setRequired(NOT_REQUIRED);
        options.addOption(clean);

    }

    public boolean parseArgs(String[] args) throws IOException {
        setUpOptions();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            LogFile.writeLog(Level.SEVERE, "Error in parameters! " + e.getMessage());
            formatter.printHelp("ibmiifs", options);
            return false;
        }

        return processCommand(cmd);
    }

    private boolean processCommand(@NotNull CommandLine cmd) {
        localFilePaths = cmd.getOptionValues("local");
        remoteFilePaths = cmd.getOptionValues("remote");
        cleanUp = cmd.hasOption("clean");

        enumAction = EnumParams.Action.get(cmd.getOptionValue("action"));

        return true;
    }
}
