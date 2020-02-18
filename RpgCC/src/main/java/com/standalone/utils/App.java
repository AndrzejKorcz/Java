package com.standalone.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import com.ibm.debug.pdt.codecoverage.core.results.CCResultException;
import com.ibm.debug.pdt.codecoverage.core.results.CCResultsFactory;
import com.ibm.debug.pdt.codecoverage.core.results.ICCFile;
import com.ibm.debug.pdt.codecoverage.core.results.ICCResult;
import com.santander.utils.LogFile;
import com.standalone.cli.ArgsParser;
import org.apache.commons.cli.Option;

public class App 
{
    public static void main( String[] args ) throws IOException {
        LogFile.writeLog(Level.INFO, "Start code coverage process.");

        ArgsParser argsParser = ArgsParser.builder()
                .ccFile(new Option("f", "file", true, "path to cc file"))
                .percent(new Option("p", "percent", true, "percent"))
                .build();

        if (!argsParser.parseArgs(args)) {
            System.exit(1);
        }

        if (!validFile(argsParser.getFileNamePath())) {
            endProgramWithError("No data for code coverage!");
        }

        ICCResult results = null;
        try {
            results = CCResultsFactory.getInstance().createResult(new String[]{argsParser.getFileNamePath()});
            LogFile.writeLog(Level.INFO, "Percentage code coverage: " + results.getPercentCoverage());

            LogFile.writeLog(Level.INFO,"Objects covered: ");
            ICCFile[] iccFiles = results.getFiles();
            for (ICCFile iccFile : iccFiles) {
                LogFile.writeLog(Level.INFO, iccFile.getName());
            }

            if( (argsParser.getPercentGate() != null) && (!argsParser.getPercentGate().isEmpty()) &&
                (results.getPercentCoverage() < Integer.parseInt(argsParser.getPercentGate())) )
                    System.exit(1);

        } catch (CCResultException e) {
            endProgramWithError("Invalid code coverage data. Error message: " + e.getMessage());
        }

        LogFile.writeLog(Level.INFO, "End code coverage process.");
        System.exit(0);
    }

     private static void endProgramWithError(String errMsg) throws IOException {
        LogFile.writeLog(Level.SEVERE, errMsg);
        System.exit(1);
    }

        private static boolean validFile(String fileName) {
            File file = new File(fileName);
            return (file.exists() && file.isFile());
    }

}
