package com.standalone.ibmi;

import com.ibm.as400.access.AS400SecurityException;
import com.standalone.ibmi.connector.As400Connection;
import com.standalone.ibmi.integratedfilesystem.As400ifs;
import com.standalone.ibmi.service.ConnectionService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    final static Path resourceDirectory = Paths.get("src","test","resources");
    final static String textFile = "test.txt";
    final static String binaryFile = "APB9017U.cczip";
    final static String ifsPath = "/home/KORCZA03/test";

    private static As400ifs as400ifs;
    private static As400Connection as400Connection;
    private static ConnectionService connectionService;

    private static void buildAs400(String fileName) {
        as400ifs = As400ifs.builder()
                .localFilePathNames(new String[]{resourceDirectory + "/" + fileName})
                .ifsFilePathNames(new String[]{ifsPath + "/" + fileName})
                .as400Connection(as400Connection)
                .build();
    }

    private static void writeFile(String fileName) throws IOException {
        as400ifs = null ;
        buildAs400(fileName);
        as400ifs.writeIFS();
    }

    @BeforeClass
    public static void setup() throws IOException {
        connectionService = new ConnectionService();
        as400Connection = connectionService.as400Connection();
    }

    @AfterClass
    public static void tearDown() {
        as400ifs = null;
        as400Connection = null;
        connectionService = null;
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldExitWithSuccessForCpyTxtFromIfsWithClean() throws IOException, AS400SecurityException {
        final String LOCAL_PATH = resourceDirectory + "/" + textFile;
        final String IFS_PATH = ifsPath + "/" + textFile;
        writeFile(textFile);
        exit.expectSystemExitWithStatus(0);
        App.main(new String[] {"-a", "cpyTxtFromIfs", "-l", LOCAL_PATH, "-r", IFS_PATH, "-c"});
    }

    @Test
    public void shouldExitWithSuccessForCpyBinaryFromIfsWithClean() throws IOException, AS400SecurityException {
        final String LOCAL_PATH = resourceDirectory + "/" + binaryFile;
        final String IFS_PATH = ifsPath + "/" + binaryFile;
        writeFile(binaryFile);
        exit.expectSystemExitWithStatus(0);
        App.main(new String[] {"-a", "cpyTxtFromIfs", "-l", LOCAL_PATH, "-r", IFS_PATH, "-c"});
    }

    @Test
    public void shouldExitWithSuccessForCpyTxtFromIfs() throws IOException, AS400SecurityException {
        final String LOCAL_PATH = resourceDirectory + "/" + textFile;
        final String IFS_PATH = ifsPath + "/" + textFile;
        writeFile(textFile);
        exit.expectSystemExitWithStatus(0);
        App.main(new String[] {"-a", "cpyTxtFromIfs", "-l", LOCAL_PATH, "-r", IFS_PATH});
    }

    @Test
    public void shouldExitWithSuccessForCpyByteFromIfs() throws IOException, AS400SecurityException {
        final String LOCAL_PATH = resourceDirectory + "/" + binaryFile;
        final String IFS_PATH = ifsPath + "/" + binaryFile;
        writeFile(binaryFile);
        exit.expectSystemExitWithStatus(0);
        App.main(new String[] {"-a", "cpyByteFromIfs", "-l", LOCAL_PATH, "-r", IFS_PATH});
    }

    @Test
    public void shouldExitWithSuccessForCpyToIfs() throws IOException, AS400SecurityException {
        final String LOCAL_PATH = resourceDirectory + "/" + textFile;
        final String IFS_PATH = ifsPath + "/" + textFile;
        exit.expectSystemExitWithStatus(0);
        App.main(new String[] {"-a", "cpyToIfs", "-l", LOCAL_PATH, "-r", IFS_PATH});
    }

    @Test
    public void shouldExitWithError() throws IOException, AS400SecurityException {
        exit.expectSystemExitWithStatus(1);
        App.main(new String[] {});
    }

}
