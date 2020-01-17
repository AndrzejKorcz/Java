package com.standalone.ibmi.cli;

import com.standalone.ibmi.params.EnumParams;
import org.apache.commons.cli.Option;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;


public class ArgsParserTest {

    private static final String LOCAL_PATH = "local path";
    private static final String IFS_PATH = "ifs path";

    private static final String ACTION = "a";
    private static final String LOCAL = "l";
    private static final String REMOTE = "r";

    private static final String HYPHEN = "-";
    private static final String ACTION_ = HYPHEN + ACTION;
    private static final String LOCAL_ = HYPHEN + LOCAL;
    private static final String REMOTE_ = HYPHEN + REMOTE;

    private static ArgsParser argsParser;

    @BeforeClass
    public static void setUp() {
        argsParser = ArgsParser.builder()
                .action(new Option(ACTION, "action", true, "action on IBMi IFS"))
                .local(new Option(LOCAL, "local", true, "path to local"))
                .remote(new Option(REMOTE, "remote", true, "path to remote"))
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
    public void shouldAnswerWithTrue() throws IOException {
        String[] args = {ACTION_, EnumParams.Action.CPYTOIFS.toString(), LOCAL_, LOCAL_PATH, REMOTE_, IFS_PATH};
        assertTrue( "ParseArgs parameters are not null", argsParser.parseArgs(args) );
    }

    @Test
    public void shouldAnswerWithInputTrue() throws IOException {
        String[] args = {ACTION_, EnumParams.Action.CPYTOIFS.toString(), LOCAL_, LOCAL_PATH, REMOTE_, IFS_PATH};
        argsParser.parseArgs(args);
        String[] localFilePaths = argsParser.getLocalFilePaths();
        assertEquals(LOCAL_PATH, localFilePaths[0]);
    }

    @Test
    public void shouldAnswerWithOutputTrue() throws IOException {
        String[] args = {ACTION_, EnumParams.Action.CPYTOIFS.toString(), LOCAL_, LOCAL_PATH, REMOTE_, IFS_PATH};
        argsParser.parseArgs(args);
        String[] remoteFilePath = argsParser.getRemoteFilePaths();
        assertEquals(IFS_PATH, remoteFilePath[0]);
    }

    @Test
    public void shouldAnswerWithActionCpyToIfsEquals() throws IOException {
        String[] args = {ACTION_, "cpyToIfs", LOCAL_, LOCAL_PATH, REMOTE_, IFS_PATH};
        argsParser.parseArgs(args);
        assertEquals(EnumParams.Action.CPYTOIFS, argsParser.getEnumAction());
    }

    @Test
    public void shouldAnswerWithActionCpyFromIfsEquals() throws IOException {
        String[] args = {ACTION_, "cpyFromIfs", LOCAL_, LOCAL_PATH, REMOTE_, IFS_PATH};
        argsParser.parseArgs(args);
        assertEquals(EnumParams.Action.CPYFROMIFS, argsParser.getEnumAction());
    }

    @Test
    public void optionsNotNull() throws IOException {
        String[] args = {};
        argsParser.parseArgs(args);
        assertNotNull("Verify that argsParser options is NOT null", argsParser.getOptions());
    }

    @Test
    public void optionActionNotNull() throws IOException {
        String[] args = {ACTION_, EnumParams.Action.CPYFROMIFS.toString()};
        argsParser.parseArgs(args);
        assertNotNull("Verify that argsParser options is NOT null", argsParser.getAction());
    }

    @Test
    public void optionInputNotNull() throws IOException {
        String[] args = {"-i", "path"};
        argsParser.parseArgs(args);
        assertNotNull("Verify that input option is NOT null", argsParser.getLocal());
    }
    @Test
    public void optionOutputNotNull() throws IOException {
        String[] args = {"-o", "path"};
        argsParser.parseArgs(args);
        assertNotNull("Verify that output option is NOT null", argsParser.getRemote());
    }

}
