package com.standalone.cli;

import com.standalone.utils.LogFile;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.logging.Level;

@Builder
@Getter
public class ArgsParser {
    public static final boolean REQUIRED = true;

    private Options options;

    private Option json;
    private Option url;

    private String jsonString;
    private String urlString;

    private void setUpOptions() {
        options = new Options();

        json.setRequired(REQUIRED);
        options.addOption(json);

        url.setRequired(REQUIRED);
        options.addOption(url);
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
            formatter.printHelp("sndJsonReport", options);
            return false;
        }

        return processCommand(cmd);
    }

    private boolean processCommand(CommandLine cmd) {
        jsonString = cmd.getOptionValue("json");
        urlString = cmd.getOptionValue("url");
        return true;
    }
}
