package com.santander.ibmi.cli;

import com.santander.ibmi.params.EnumParams;
import org.apache.commons.cli.Option;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void shouldAnswerWithFalse(){
        assertFalse("ParseArgs parameters are null", argsParser.parseArgs(null));
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        String[] args = {ACTION_, EnumParams.Action.CPYTOIFS.toString(), LOCAL_, LOCAL_PATH, REMOTE_, IFS_PATH};
        assertTrue( "ParseArgs parameters are not null", argsParser.parseArgs(args) );
    }

    @Test
    public void shouldAnswerWithInputTrue()
    {
        String[] args = {ACTION_, EnumParams.Action.CPYTOIFS.toString(), LOCAL_, LOCAL_PATH, REMOTE_, IFS_PATH};
        argsParser.parseArgs(args);
        assertEquals(LOCAL_PATH, argsParser.getLocalFilePath());
    }

    @Test
    public void shouldAnswerWithOutputTrue()
    {
        String[] args = {ACTION_, EnumParams.Action.CPYTOIFS.toString(), LOCAL_, LOCAL_PATH, REMOTE_, IFS_PATH};
        argsParser.parseArgs(args);
        assertEquals(IFS_PATH, argsParser.getRemoteFilePath());
    }

    @Test
    public void shouldAnswerWithActionCpyToIfsEquals()
    {
        String[] args = {ACTION_, "cpyToIfs", LOCAL_, LOCAL_PATH, REMOTE_, IFS_PATH};
        argsParser.parseArgs(args);
        assertEquals(EnumParams.Action.CPYTOIFS, argsParser.getEnumAction());
    }

    @Test
    public void shouldAnswerWithActionCpyFromIfsEquals()
    {
        String[] args = {ACTION_, "cpyFromIfs", LOCAL_, LOCAL_PATH, REMOTE_, IFS_PATH};
        argsParser.parseArgs(args);
        assertEquals(EnumParams.Action.CPYFROMFS, argsParser.getEnumAction());
    }

    @Test
    public void optionsNotNull(){
        String[] args = {};
        argsParser.parseArgs(args);
        assertNotNull("Verify that argsParser options is NOT null", argsParser.getOptions());
    }

    @Test
    public void optionActionNotNull(){
        String[] args = {ACTION_, EnumParams.Action.CPYFROMFS.toString()};
        argsParser.parseArgs(args);
        assertNotNull("Verify that argsParser options is NOT null", argsParser.getAction());
    }

    @Test
    public void optionInputNotNull(){
        String[] args = {"-i", "path"};
        argsParser.parseArgs(args);
        assertNotNull("Verify that input option is NOT null", argsParser.getLocal());
    }
    @Test
    public void optionOutputNotNull(){
        String[] args = {"-o", "path"};
        argsParser.parseArgs(args);
        assertNotNull("Verify that output option is NOT null", argsParser.getRemote());
    }

}
