package com.standalone.cli;

import com.santander.utils.LogFile;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.logging.Level;

@Builder
@Getter
public class ArgsParser {
    public static final boolean REQUIRED = true;
    public static final boolean NOT_REQUIRED = false;

    private Options options;

    private Option ccFile;
    private Option percent;
    private Option archive;

    private String fileNamePath;
    private String percentGate;
    private String archivePath;

    private void setUpOptions() {
        options = new Options();

        ccFile.setRequired(REQUIRED);
        options.addOption(ccFile);

        percent.setRequired(NOT_REQUIRED);
        options.addOption(percent);

        archive.setRequired(NOT_REQUIRED);
        options.addOption(archive);

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
            formatter.printHelp("rpgcc", options);
            return false;
        }

        return processCommand(cmd);
    }

    private boolean processCommand(CommandLine cmd) {
        fileNamePath = cmd.getOptionValue("file");
        percentGate = cmd.getOptionValue("percent");
        archivePath = cmd.getOptionValue("archive");
        return true;
    }
}
