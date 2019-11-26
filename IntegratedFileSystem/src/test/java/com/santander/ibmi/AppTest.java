package com.santander.ibmi;

import com.santander.ibmi.params.EnumParams;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static final String LOCAL_PATH = "local path";
    private static final String IFS_PATH = "ifs path";

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldExitWithSuccess() throws IOException {
        exit.expectSystemExitWithStatus(0);
        App.main(new String[] {"-a", "cpyFromIfs", "-l", LOCAL_PATH, "-r", IFS_PATH});
    }
    @Test
    public void shouldExitWithError() throws IOException {
        exit.expectSystemExitWithStatus(1);
        App.main(new String[] {});
    }

}
