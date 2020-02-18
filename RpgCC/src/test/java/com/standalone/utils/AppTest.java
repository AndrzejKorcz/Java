package com.standalone.utils;

import static org.junit.Assert.assertTrue;

import com.standalone.cli.ArgsParser;
import org.apache.commons.cli.Option;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppTest 
{
    final Path resourceDirectory = Paths.get("src","test","resources");
    final String object = "RSTDEMO01U.cczip";

    private static ArgsParser argsParser;

    @BeforeClass
    public static void setUp() {
        argsParser = ArgsParser.builder()
                .ccFile(new Option("f", "file", true, "path to cc file"))
                .percent(new Option("p", "percent", true, "percent"))
                .build();
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldAnswerWithSuccess() throws IOException {
        exit.expectSystemExitWithStatus(0);
        final String input = resourceDirectory + "/" + object;
        App.main(new String[] {"-f", input});
    }

    @Test
    public void shouldAnswerWithNotValidFile() throws IOException {
        exit.expectSystemExitWithStatus(1);
        final String input = resourceDirectory + "/" + "dummy";
        App.main(new String[] {"-f", input});
    }

    @Test
    public void shouldAnswerWithNotEnoughPercent() throws IOException {
        exit.expectSystemExitWithStatus(1);
        final String input = resourceDirectory + "/" + object;
        App.main(new String[] {"-f", input, "-p", "101"});
    }

    @Test
    public void shouldAnswerWithNotValidContentFile() throws IOException {
        exit.expectSystemExitWithStatus(1);
        final String input = resourceDirectory + "/" + "NotValid.cczip";
        App.main(new String[] {"-f", input, "-p", "101"});
    }

    @Test
    public void shouldExitWithError() throws IOException {
        exit.expectSystemExitWithStatus(1);
        App.main(new String[] {});
    }
}
