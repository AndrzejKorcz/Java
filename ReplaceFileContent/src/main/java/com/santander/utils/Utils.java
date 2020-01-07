package com.santander.utils;

import lombok.extern.java.Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Log
public class Utils {

    private Utils() {
    }

    public static String getContent(String fileName) throws IOException {
        return String.join(System.lineSeparator(), Files.readAllLines(Paths.get(fileName)));
    }

    public static String replaceAll(String in, Map<String, String> items) {
        for (Map.Entry<String, String> pair : items.entrySet()) {
            in = in.replace(pair.getKey(), pair.getValue());
        }
        return in;
    }

    public static boolean writeFile(String content, String dest) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(dest))) {
            writer.write(content);
        }
        return true;
    }

}
