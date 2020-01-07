package com.santander.utils;

import lombok.extern.java.Log;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

@Log
public class AppTest {

    Path resourceDirectory = Paths.get("src","test","resources");
    final String object = "RST660101";
    final String project = "NP10666";
    final String function = "getFunction";

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldExitWithSuccessForPrototype() throws IOException {
        exit.expectSystemExitWithStatus(0);
        String template = "PROTOTYPE.RPGLE";
        final String outputObject = template.replace("PROTOTYPE", object + "P");
        final String input = resourceDirectory + "/" + template;
        final String output = resourceDirectory + "/" + outputObject;

        App.main(new String[] {"-i", input, "-o", output, "-m", object, "-t", project, "-f", function});
    }

    @Test
    public void shouldExitWithSuccessForBind() throws IOException {
        exit.expectSystemExitWithStatus(0);
        String template = "BIND.BND";
        final String outputObject = template.replace("BIND", object + "B");
        final String input = resourceDirectory + "/" + template;
        final String output = resourceDirectory + "/" + outputObject;

        log.info("input: " + input);
        log.info("output: " + output);
        App.main(new String[] {"-i", input, "-o", output, "-m", object, "-t", project, "-f", function});
    }

    @Test
    public void shouldExitWithSuccessForModule() throws IOException {
        exit.expectSystemExitWithStatus(0);
        String template = "MODULE.RPGLE";
        final String outputObject = template.replace("MODULE", object + "M");
        final String input = resourceDirectory + "/" + template;
        final String output = resourceDirectory + "/" + outputObject;

        App.main(new String[] {"-i", input, "-o", output, "-m", object, "-t", project, "-f", function});
    }

    @Test
    public void shouldExitWithSuccessForTest() throws IOException {
        exit.expectSystemExitWithStatus(0);
        String template = "TEST.RPGLE";
        final String outputObject = template.replace("TEST", object + "T");
        final String input = resourceDirectory + "/" + template;
        final String output = resourceDirectory + "/" + outputObject;

        App.main(new String[] {"-i", input, "-o", output, "-m", object, "-t", project, "-f", function});
    }

    @Test
    public void shouldExitWithSuccessForUnitTest() throws IOException {
        exit.expectSystemExitWithStatus(0);
        String template = "UNITTEST.RPGLE";
        final String outputObject = template.replace("UNITTEST", object + "U");
        final String input = resourceDirectory + "/" + template;
        final String output = resourceDirectory + "/" + outputObject;

        App.main(new String[] {"-i", input, "-o", output, "-m", object, "-t", project, "-f", function});
    }

    @Test
    public void shouldExitWithError() throws IOException {
        exit.expectSystemExitWithStatus(1);
        App.main(new String[] {});
    }
}