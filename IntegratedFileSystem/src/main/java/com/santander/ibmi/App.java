package com.santander.ibmi;

import com.santander.ibmi.cli.ArgsParser;
import lombok.extern.java.Log;
import org.apache.commons.cli.Option;

@Log
public class App 
{
    public static void main( String[] args )  {

        ArgsParser argsParser = ArgsParser.builder()
                .action(new Option("a", "action", true, "action on IBMi IFS"))
                .input(new Option("i", "input", true, "path to input"))
                .output(new Option("o", "output", true, "path to output"))
                .build();

       if (!argsParser.parseArgs(args)) {
            log.severe("Error");
            System.exit(1);
        }
    }
}
