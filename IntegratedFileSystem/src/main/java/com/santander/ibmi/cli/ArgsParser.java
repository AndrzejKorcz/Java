package com.santander.ibmi.cli;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.java.Log;
import org.apache.commons.cli.*;

@Log
@Builder
@Getter
public class ArgsParser {
    public static final boolean REQUIRED = true;

    private Options options;

    private Option action;
    private Option input;
    private Option output;

    private void setUpOptions() {
        options = new Options();

        action.setRequired(REQUIRED);
        options.addOption(action);

        input.setRequired(REQUIRED);
        options.addOption(input);

        output.setRequired(REQUIRED);
        options.addOption(output);
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
        processCommand(cmd);

        return true;
    }

    private boolean processCommand(CommandLine cmd) {
        String inputFilePath = cmd.getOptionValue("input");
        String outputFilePath = cmd.getOptionValue("output");

        log.info(inputFilePath);
        log.info(outputFilePath);

        return true;
    }


}
