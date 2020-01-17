package com.standalone.ibmi;

import com.ibm.as400.access.AS400SecurityException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldExitWithSuccessForCpyTxtFromIfs() throws IOException, AS400SecurityException {
        final String LOCAL_PATH = "d:/DevOps/src/CICD/RST661001U.txt";
        final String IFS_PATH = "/usr/local/src/CICD/RST661001U.rpt";
        exit.expectSystemExitWithStatus(0);
        App.main(new String[] {"-a", "cpyTxtFromIfs", "-l", LOCAL_PATH, "-r", IFS_PATH});
    }
    @Test
    public void shouldExitWithSuccessForCpyByteFromIfs() throws IOException, AS400SecurityException {
        final String LOCAL_PATH = "d:/DevOps/src/CICD/RST661001T.zip";
        final String IFS_PATH = "/usr/local/src/CICD/RST661001T.cczip";
        exit.expectSystemExitWithStatus(0);
        App.main(new String[] {"-a", "cpyByteFromIfs", "-l", LOCAL_PATH, "-r", IFS_PATH});
    }
    @Test
    public void shouldExitWithSuccessForCpyToIfs() throws IOException, AS400SecurityException {
        final String LOCAL_PATH = "d:/DevOps/src/CICD/test.txt";
        final String IFS_PATH = "/usr/local/src/CICD/test.txt";
        exit.expectSystemExitWithStatus(0);
        App.main(new String[] {"-a", "cpyToIfs", "-l", LOCAL_PATH, "-r", IFS_PATH});
    }
    @Test
    public void shouldExitWithError() throws IOException, AS400SecurityException {
        exit.expectSystemExitWithStatus(1);
        App.main(new String[] {});
    }

}
