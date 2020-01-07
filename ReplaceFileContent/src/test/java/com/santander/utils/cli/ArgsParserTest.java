package com.santander.utils.cli;

import org.junit.BeforeClass;
import org.apache.commons.cli.Option;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArgsParserTest {

    private static ArgsParser argsParser;

    @BeforeClass
    public static void setUp() {
        argsParser = ArgsParser.builder()
                .input(new Option("i", "input", true, "path to input"))
                .output(new Option("o", "output", true, "path to output"))
                .object(new Option("m", "object", true, "object name"))
                .project(new Option("t", "project", true, "project name"))
                .function(new Option("f", "function", true, "function name"))
                .source(new Option("s", "source", true, "source name"))
                .description(new Option("d", "description", true, "description"))
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
        String[] args = {"-i", "d:/path_input_test/file", "-o", "d:/path_output_test/file", "-m", "RST660101", "-t", "NP10666", "-f", "getFunction"};
        assertTrue( "ParseArgs parameters are not null", argsParser.parseArgs(args) );
    }

    @Test
    public void shouldAnswerWithInputEquel()
    {
        String input = "d:/path_input_test/file";
        String[] args = {"-i", input, "-o", "d:/path_output_test/file", "-m", "RST660101", "-t", "NP10666", "-f", "getFunction"};
        argsParser.parseArgs(args);
        String[] inputPaths = argsParser.getInputPath();
        assertEquals(input, inputPaths[0]);

    }

}