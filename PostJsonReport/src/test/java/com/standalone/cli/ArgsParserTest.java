package com.standalone.cli;

import org.junit.BeforeClass;

import static org.junit.Assert.*;
import org.apache.commons.cli.Option;
import org.junit.Test;

import java.io.IOException;

public class ArgsParserTest {

    private static final String JSON = "j";
    private static final String URL = "u";

    private static final String HYPHEN = "-";
    private static final String JSON_ = HYPHEN + JSON;
    private static final String URL_ = HYPHEN + URL;

    private static ArgsParser argsParser;

    @BeforeClass
    public static void setUp() {
        argsParser = ArgsParser.builder()
                .json(new Option(JSON, "json", true, "json string"))
                .url(new Option(URL, "url", true, "rest server url"))
                .build();
    }

    @Test
    public void setArgsParserNotNull() {
        assertNotNull("Verify that argsParser is NOT null", argsParser);
    }

    @Test
    public void shouldAnswerWithFalse() throws IOException {
        assertFalse("ParseArgs parameters are null", argsParser.parseArgs(null));
    }

    @Test
    public void shouldAnswerWithTrueWithTwoParameters() throws IOException {
        String[] args = {JSON_, "{}", URL_, "http://path_to_server"};
        assertTrue( "ParseArgs parameters are not null", argsParser.parseArgs(args) );
    }

    @Test
    public void shouldAnswerWithEqualWithJson() throws IOException {
        String[] args = {JSON_, "{}", URL_, "http://path_to_server"};
        argsParser.parseArgs(args);
        assertEquals("{}", argsParser.getJsonString());
    }

    @Test
    public void shouldAnswerWithEqualWithUrl() throws IOException {
        String[] args = {JSON_, "{}", URL_, "http://path_to_server"};
        argsParser.parseArgs(args);
        assertEquals("http://path_to_server", argsParser.getUrlString());
    }

    @Test
    public void shouldAnswerWithNotNullForJsonOption() throws IOException {
        String[] args = {JSON_, "{}", URL_, "http://path_to_server"};
        argsParser.parseArgs(args);
        assertNotNull( "Json parameter is not null", argsParser.getJson() );
    }

    @Test
    public void shouldAnswerWithNotNullForUrlOption() throws IOException {
        String[] args = {JSON_, "{}", URL_, "http://path_to_server"};
        argsParser.parseArgs(args);
        assertNotNull( "Url parameter is not null", argsParser.getUrl() );
    }


}