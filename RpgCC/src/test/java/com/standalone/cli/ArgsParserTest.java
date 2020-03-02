package com.standalone.cli;

import org.junit.BeforeClass;
import org.apache.commons.cli.Option;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ArgsParserTest {

    private static final String FILE = "f";
    private static final String PERCENT = "p";
    private static final String ARCHIVE =  "s";

    private static final String HYPHEN = "-";
    private static final String FILE_ = HYPHEN + FILE;
    private static final String PERCENT_ = HYPHEN + PERCENT;
    private static final String ARCHIVE_  = HYPHEN + ARCHIVE;

    private static ArgsParser argsParser;

    @BeforeClass
    public static void setUp() {
        argsParser = ArgsParser.builder()
                .ccFile(new Option(FILE, "file", true, "path to cc file"))
                .percent(new Option(PERCENT, "percent", true, "percent"))
                .archive(new Option(ARCHIVE, "archive", true, "archive flag"))
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
    public void shouldAnswerWithTrueWithOneParameter() throws IOException {
        String[] args = {FILE_, "path_to_file"};
        assertTrue( "ParseArgs parameters are not null", argsParser.parseArgs(args) );
    }

    @Test
    public void shouldAnswerWithTrueWithTwoParameters() throws IOException {
        String[] args = {FILE_, "path_to_file", PERCENT_, "40"};
        assertTrue( "ParseArgs parameters are not null", argsParser.parseArgs(args) );
    }

    @Test
    public void shouldAnswerWithEquelWithFilePath() throws IOException {
        String[] args = {FILE_, "path_to_file"};
        argsParser.parseArgs(args);
        assertEquals("path_to_file", argsParser.getFileNamePath());
    }

    @Test
    public void shouldAnswerWithEquelWithPercent() throws IOException {
        String[] args = {FILE_, "path_to_file", PERCENT_, "40"};
        argsParser.parseArgs(args);
        assertEquals("40", argsParser.getPercentGate());
    }

    @Test
    public void shouldAnswerWithNotNullForCCFileOption() throws IOException {
        String[] args = {FILE_, "path_to_file"};
        argsParser.parseArgs(args);
        assertNotNull( "CC file parameter are not null", argsParser.getCcFile() );
    }

    @Test
    public void shouldAnswerWithNotNullForPercentOption() throws IOException {
        String[] args = {FILE_, "path_to_file", PERCENT_, "40"};
        argsParser.parseArgs(args);
        assertNotNull( "CC file parameter are not null", argsParser.getPercent() );
    }

    @Test
    public void shouldAnswerWithNotNullForOption() throws IOException {
        String[] args = {FILE_, "path_to_file"};
        argsParser.parseArgs(args);
        assertNotNull( "CC file parameter are not null", argsParser.getOptions() );
    }

    @Test
    public void shouldAnswerWithNotNullForArchive() throws IOException {
        String[] args = {FILE_, "path_to_file", ARCHIVE_};
        argsParser.parseArgs(args);
        assertNotNull( "Archive flag parameter are not null", argsParser.getArchive() );
    }

    @Test
    public void shouldAnswerWithTrueForArchiveFlag() throws IOException {
        String[] args = {FILE_, "path_to_file", ARCHIVE_, "path_to_archive"};
        argsParser.parseArgs(args);
        assertTrue("Archive flag should be true", !argsParser.getArchivePath().isEmpty());
    }

}