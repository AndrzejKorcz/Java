package com.standalone.utils;

import lombok.extern.java.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

@Log
public class LogFile {

    public static final String LOGS_FOLDER = "logs";

    private static final String ERROR_CREATING_NEW_LOG_FILE = "Error creating new log file";

    private LogFile() {
    }

    public static void writeLog(Level level, String msg) throws IOException {
        log.log(level, msg);
        File file = new File(LOGS_FOLDER + System.getProperty("file.separator") + formatLogFileName());
        if (!file.exists() && !file.createNewFile())
        {
            log.severe(ERROR_CREATING_NEW_LOG_FILE);
            return ;
        }

        try(FileWriter fw = new FileWriter(file.getAbsoluteFile(), true)) {
            fw.write("[" + dateSimple("HH:mm:ss") + "]: " + msg + System.lineSeparator());
        }

    }

    private static String dateSimple(String format) {
        SimpleDateFormat dt = new SimpleDateFormat(format);
        Date date = new Date();
        return dt.format(date);
    }

    public static String formatLogFileName() {
        return "log-" + dateSimple("yyyy-MM-dd");
    }

}
