package com.santander.ibmi.integratedfilesystem;

import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.IFSFile;
import com.ibm.as400.access.IFSFileOutputStream;
import com.ibm.as400.access.IFSFileReader;
import com.santander.ibmi.connector.As400Connection;
import lombok.Builder;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Builder
@Log
public class As400ifs {

    private As400Connection as400Connection;
    private String ifsFilePathName;
    private String localFilePathName;

    private IFSFileOutputStream createOutputFile(String ifsFilePathName) {

        //-- Create folder structure (Ignore errors as folder may already exist)
        IFSFile ifsFile = new IFSFile(as400Connection.getAs400(), ifsFilePathName);
        try {
            ifsFile.mkdirs();
        } catch (IOException e1) {
            log.info(e1.getMessage());
        }

        //-- Delete previous output (Ignore errors if it doesn't exist)
        try {
            ifsFile.delete();
        } catch (IOException e) {
            log.info(e.getMessage());
        }

        //-- Create a new empty file and Writer for that file
        IFSFileOutputStream writer = null;
        try {
            ifsFile.createNewFile();
            writer = new IFSFileOutputStream(ifsFile);
        } catch (Exception e) {
            log.severe(e.getMessage());
        }

        return writer;
    }

    public void writeIFS() {
        String localFileContent = getContent(localFilePathName);

        IFSFileOutputStream writer = createOutputFile(ifsFilePathName);
        try {
            if (writer != null) {
                writer.write(localFileContent.getBytes());
                writer.close();
            }
        } catch (IOException e) {
            log.severe(e.getMessage());
            return;
        }
        log.info("The contents of the local file: \n" + localFilePathName + "\nwere written to the IFS file:\n" + ifsFilePathName);
    }

    public void readIFS() {
        IFSFile ifsFile = new IFSFile(as400Connection.getAs400(), ifsFilePathName);
        try(IFSFileReader fr = new IFSFileReader (ifsFile);
            BufferedReader br = new BufferedReader(fr)) {

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null){
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
                line = br.readLine();
            }
            writeFile(localFilePathName, sb.toString());

        } catch (AS400SecurityException | IOException e) {
            log.severe(e.getMessage());
            return;
        }
        log.info("The contents of the IFS file: \n" + ifsFilePathName + "\nwere written to the local file:\n" + localFilePathName);
    }

    private void writeFile(String fileName, String fileContent) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(fileContent);
        }
    }

    private String getContent(String source) {
        String content = "";
        try {
            content = readFile(source);
        } catch (IOException e) {
            log.severe(e.getMessage());
        }
        return content;
    }

    @NotNull
    private String readFile(String fileName) throws IOException {
        return String.join(System.lineSeparator(), Files.readAllLines(Paths.get(fileName)));
    }


}

