package com.standalone.utils;

import com.standalone.cli.ArgsParser;
import org.apache.commons.cli.Option;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;

import java.io.OutputStream;
import java.net.HttpURLConnection;

public class App {
    public static void main( String[] args ) throws Exception {
        LogFile.writeLog(Level.INFO, "Start process send metrics to data server.");
        ArgsParser argsParser = ArgsParser.builder()
                .json(new Option("j", "json", true, "json string"))
                .url(new Option("u", "url", true, "rest server url"))
                .build();

        if (!argsParser.parseArgs(args)) {
            System.exit(1);
        }

        int response = post(argsParser.getUrlString(), argsParser.getJsonString());

        LogFile.writeLog(Level.INFO, "Http response code: " + response);

        LogFile.writeLog(Level.INFO, "End process send metrics to data server.");
        System.exit(0);

    }

    private static int post(String urlPath, String param ) throws IOException {
        int responseCode = 0;
        String charset = "UTF-8";

        URL url = new URL(urlPath);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        connection.setDoOutput(true); // Triggers POST.
        connection.setRequestProperty("Accept-Charset", charset);
        connection.setRequestProperty("Content-Type", "application/json;charset=" + charset);

        try (OutputStream output = connection.getOutputStream()) {
            output.write(param.getBytes(charset));
            responseCode = connection.getResponseCode();
        }

        return responseCode;
    }
}
