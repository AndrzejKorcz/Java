package com.santander.utils;

import com.santander.utils.cli.ArgsParser;
import lombok.extern.java.Log;
import org.apache.commons.cli.Option;

import java.io.IOException;
import java.util.HashMap;

@Log
public class App {
    public static void main(String[] args) throws IOException {
        log.info("Start process");
        ArgsParser argsParser = ArgsParser.builder()
                .input(new Option("i", "input", true, "path to input"))
                .output(new Option("o", "output", true, "path to output"))
                .object(new Option("m", "object", true, "object name"))
                .project(new Option("t", "project", true, "project name"))
                .function(new Option("f", "function", true, "function name"))
                .source(new Option("s", "source", true, "source name"))
                .description(new Option("d", "description", true, "description"))
                .build();

        if (!argsParser.parseArgs(args)) {
            System.exit(1);
        }

        FileReplaceContent fileReplaceContent = FileReplaceContent.builder()
                .inputs(argsParser.getInputPath())
                .outputs(argsParser.getOutputPath())
                .object(argsParser.getObjectName())
                .project(argsParser.getProjectName())
                .serviceName(argsParser.getFunctionName())
                .source(argsParser.getSourceName())
                .description(argsParser.getDesc())
                .build();

        if (!fileReplaceContent.modifyTemplate()) {
            System.exit(1);
        }

        log.info("End of process");
        System.exit(0);
    }
}
