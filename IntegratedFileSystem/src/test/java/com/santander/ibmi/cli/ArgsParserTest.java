package com.santander.ibmi.cli;

import org.apache.commons.cli.Option;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class ArgsParserTest {

    private static ArgsParser argsParser;

    @BeforeClass
    public static void setUp() {
        argsParser = ArgsParser.builder()
                .action(new Option("a", "action", true, "action on IBMi IFS"))
                .input(new Option("i", "input", true, "path to input"))
                .output(new Option("o", "output", true, "path to output"))
                .build();
    }

    @Test
    public void setArgsParserNotNull() {
        assertNotNull("Verify that argsParser is NOT null", argsParser);
    }

    @Test
    public void shouldAnswerWithFalse(){
        assertFalse("ParseArgs parameters are null", argsParser.parseArgs(null));
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        String[] args = {"-a", "cpyTo", "-i", "local path", "-o", "ifs path"};
        assertTrue( "ParseArgs parameters are not null", argsParser.parseArgs(args) );
    }

    @Test
    public void optionsNotNull(){
        String[] args = {};
        argsParser.parseArgs(args);
        assertNotNull("Verify that argsParser options is NOT null", argsParser.getOptions());
    }

    @Test
    public void optionActionNotNull(){
        String[] args = {"-a", "cpyFrom"};
        argsParser.parseArgs(args);
        assertNotNull("Verify that argsParser options is NOT null", argsParser.getAction());
    }

    @Test
    public void optionInputNotNull(){
        String[] args = {"-i", "path"};
        argsParser.parseArgs(args);
        assertNotNull("Verify that input option is NOT null", argsParser.getInput());
    }
    @Test
    public void optionOutputNotNull(){
        String[] args = {"-o", "path"};
        argsParser.parseArgs(args);
        assertNotNull("Verify that output option is NOT null", argsParser.getOutput());
    }

}
