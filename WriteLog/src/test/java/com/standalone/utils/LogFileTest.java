package com.standalone.utils;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import static org.junit.Assert.*;
import static com.standalone.utils.LogFile.*;

public class LogFileTest {

    @Test
    public void writeLogFileExists() throws IOException {
        String filePathName = LOGS_FOLDER + System.getProperty("file.separator") + formatLogFileName();
        deleteLogFile(filePathName);
        writeLog(Level.INFO, "test");
        File file = new File(filePathName);
        assertTrue(file.exists());
    }

    private void deleteLogFile(String filePathName) {
        File file = new File(filePathName);
        file.deleteOnExit();
    }
}