package com.standalone.ibmi.cli;

import lombok.extern.java.Log;
import org.junit.BeforeClass;
import org.apache.commons.cli.Option;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

@Log
public class ArgsParserTest {
    private static final String COMMAND = "c";

    private static ArgsParser argsParser;

    @BeforeClass
    public static void setUp() {
        argsParser = ArgsParser.builder()
                .cmd(new Option(COMMAND, "command" , true, "command on IBMi IFS"))
                .build();
    }

    @Test
    public void argsParserNotNull() {
        assertNotNull("Verify that argsParser is NOT null", argsParser);
    }

    @Test
    public void argsParserAnswerWithTrue() throws IOException {
        String[] args = {"-c", "command1", "command2", "command3"};
        assertTrue( "ParseArgs parameters exists", argsParser.parseArgs(args) );
    }

    @Test
    public void argsParserAnswerWithTrueWithList() throws IOException {
        String[] args = {"-c", "command1", "command2", "command3"};
        argsParser.parseArgs(args);
        assertTrue( "ParseArgs parameters exists and length equals 3", argsParser.getCommands().length==3 );
    }





}