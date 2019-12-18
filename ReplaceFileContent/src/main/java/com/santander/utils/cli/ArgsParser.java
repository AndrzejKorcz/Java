package com.santander.utils.cli;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.java.Log;
import org.apache.commons.cli.*;

@Log
@Builder
@Getter
public class ArgsParser {
    private static final boolean REQUIRED = true;
    private static final boolean NOT_REQUIRED = false;

    private Options options;

    private Option input;
    private Option output;
    private Option object;
    private Option project;
    private Option function;
    private Option source;
    private Option description;

    private String[] inputPath;
    private String[] outputPath;
    private String objectName;
    private String projectName;
    private String functionName;
    private String sourceName;
    private String desc;

    private void setUpOptions() {
        options = new Options();

        input.setRequired(REQUIRED);
        input.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(input);

        output.setRequired(REQUIRED);
        output.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(output);

        object.setRequired(REQUIRED);
        options.addOption(object);

        project.setRequired(REQUIRED);
        options.addOption(project);

        function.setRequired(REQUIRED);
        options.addOption(function);

        source.setRequired(NOT_REQUIRED);
        options.addOption(source);

        description.setRequired(NOT_REQUIRED);
        options.addOption(description);
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
            formatter.printHelp("cpyRplObj", options);
            return false;
        }

        return processCommand(cmd);
    }

    private boolean processCommand(CommandLine cmd) {
        inputPath = cmd.getOptionValues("input");
        outputPath = cmd.getOptionValues("output");
        objectName = cmd.getOptionValue("object");
        projectName = cmd.getOptionValue("project");
        functionName = cmd.getOptionValue("function");
        sourceName = cmd.getOptionValue("source");
        desc = cmd.getOptionValue("description");

        return true;
    }
}
