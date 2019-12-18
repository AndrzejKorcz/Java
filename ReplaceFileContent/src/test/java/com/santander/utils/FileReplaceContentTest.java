package com.santander.utils;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.junit.Assert.*;

public class FileReplaceContentTest {

    private static FileReplaceContent fileReplaceContent;
    private static Path resourceDirectory;

    private static void createFileReplaceContentInstance(String tempName) {

        resourceDirectory = Paths.get("src","test","resources");

        final String object = "RST660101";
        final String template = tempName;
        final String outputObject = template.replace("PROTOTYPE", object) ;
        final String project = "NP10666";
        final String serviceName = "GetTestFunction";

        final String[] inputs = new String[] {resourceDirectory + "/" + template};
        final String[] outputs = new String[] {resourceDirectory + "/" + outputObject};


        fileReplaceContent = FileReplaceContent.builder()
                .inputs(inputs)
                .outputs(outputs)
                .object(object)
                .project(project)
                .serviceName(serviceName)
                .build();
    }

    @BeforeClass
    public static void setUp() {

    }

    @AfterClass
    public  static void tearDown() {
        fileReplaceContent = null;
    }

    @Test
    public void modifyTemplateTrue() throws IOException {
        createFileReplaceContentInstance("PROTOTYPE.RPGLE");
        boolean result = fileReplaceContent.modifyTemplate();
       assertTrue(result);
    }

@Test
public void modifyTemplateFalse() throws IOException {
    createFileReplaceContentInstance("EMPTY");
    boolean result = fileReplaceContent.modifyTemplate();
    assertFalse(result);
}


}