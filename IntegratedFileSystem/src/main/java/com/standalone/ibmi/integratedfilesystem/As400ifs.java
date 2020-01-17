package com.standalone.ibmi.integratedfilesystem;

import com.ibm.as400.access.*;
import com.standalone.ibmi.connector.As400Connection;
import com.standalone.ibmi.params.EnumParams;
import com.standalone.utils.LogFile;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;

@Builder
public class As400ifs {

    private As400Connection as400Connection;
    private String[] ifsFilePathNames;
    private String[] localFilePathNames;

    private IFSFileOutputStream createOutputFile(String ifsFilePathName) throws IOException {

        //-- Create folder structure (Ignore errors as folder may already exist)
        IFSFile ifsFile = new IFSFile(as400Connection.getAs400(), ifsFilePathName);
        try {
            ifsFile.mkdirs();
        } catch (IOException e1) {
            LogFile.writeLog(Level.WARNING, "Ignore errors as folder may already exist" + e1.getMessage());
        }

        //-- Delete previous output (Ignore errors if it doesn't exist)
        try {
            ifsFile.delete();
        } catch (IOException e) {
            LogFile.writeLog(Level.WARNING, "Ignore errors if it doesn't exist" + e.getMessage());
        }

        //-- Create a new empty file and Writer for that file
        IFSFileOutputStream writer = null;
        try {
            ifsFile.createNewFile();
            writer = new IFSFileOutputStream(ifsFile);
        } catch (Exception e) {
            LogFile.writeLog(Level.SEVERE, "Error create writer for empty file: " + e.getMessage());
        }

        return writer;
    }

    public void writeIFS() throws IOException {
        int i = 0;
        for (String localFilePathName: localFilePathNames) {
           String localFileContent = getContent(localFilePathName);
           IFSFileOutputStream writer = createOutputFile(ifsFilePathNames[i]);
            try {
                if (writer != null) {
                  writer.write(localFileContent.getBytes());
                  writer.close();
                }
            } catch (IOException e) {
                LogFile.writeLog(Level.SEVERE, "Error write to ifs: " + e.getMessage());
                return;
            }
            LogFile.writeLog(Level.FINE, "The contents of the local file: " + localFilePathName + " were written to the IFS file: " + ifsFilePathNames[i]);
            i++;
        }

    }

    @NotNull
    private String readFileString(AS400 system, String path) throws AS400SecurityException, IOException {
        IFSFile ifsFile = new IFSFile(system, path);
        try(IFSFileReader fr = new IFSFileReader (ifsFile);
            BufferedReader br = new BufferedReader(fr)) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
                line = br.readLine();
            }
            return sb.toString();
        }
    }

    @NotNull
    private byte[] readFileByte(AS400 system, String path) throws AS400SecurityException,
            IOException{
        IFSFile file = new IFSFile(system, path);
        /** creates a file input stream */
        try(IFSFileInputStream fis = new IFSFileInputStream (file, IFSFileInputStream.SHARE_READERS);) {
            byte[] data = new byte[fis.available()];
            int count = 0;
            while((count = fis.read(data)) > 0) {
                LogFile.writeLog(Level.WARNING, "Should never happen!");
            }

            return data;
        }
    }

    public void readIFS(EnumParams.Action enumAction) throws IOException, AS400SecurityException {
        int i = 0;
        for (String ifsFilePathName : ifsFilePathNames) {
            try (FileOutputStream fos = new FileOutputStream(localFilePathNames[i])) {
                if (enumAction == EnumParams.Action.CPYTXTFROMIFS) {
                    fos.write(readFileString(as400Connection.getAs400(), ifsFilePathName).getBytes());
                }
                if (enumAction == EnumParams.Action.CPYBYTEFROMIFS) {
                    fos.write(readFileByte(as400Connection.getAs400(), ifsFilePathName));
                }
            }
            LogFile.writeLog(Level.FINE, "The contents of the IFS file: " + ifsFilePathName + " were written to the local file: " + localFilePathNames[i]);
            i++;
      }
    }

    private String getContent(String source) throws IOException {
        String content = "";
        try {
            content = readFile(source);
        } catch (IOException e) {
            LogFile.writeLog(Level.SEVERE, "Eror read file: " + source + " " + e.getMessage());
        }
        return content;
    }

    @NotNull
    private String readFile(String fileName) throws IOException {
        return String.join(System.lineSeparator(), Files.readAllLines(Paths.get(fileName)));
    }


}

