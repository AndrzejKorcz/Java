package com.santander.utils;

import lombok.extern.java.Log;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.junit.Assert.*;

@Log
public class UtilsTest {

    private static Path resourceDirectory;

    @BeforeClass
    public static void setUp() {
        resourceDirectory = Paths.get("src","test","resources");
    }

    @Test
    public void readFileNotEmpty() throws IOException {
        String content = Utils.getContent(resourceDirectory + "/test.txt");
        log.info(content);
        assertFalse(content.trim().isEmpty());
    }

    @Test
    public void replaceAllEqual() {
        String object = "RST660101";
        HashMap<String, String> items = new HashMap<>();
        items.put("@ObjectName", object);

        assertEquals(object, Utils.replaceAll("@ObjectName", items));
    }

    @Test
    public void writeFileExists() throws IOException {
        String content = "test writeFileExists";
        String filePathString = resourceDirectory + "/writeTest.txt";

        Utils.writeFile(content, filePathString);

        File f = new File(filePathString);
        assertTrue((f.exists() && !f.isDirectory()));

    }

}