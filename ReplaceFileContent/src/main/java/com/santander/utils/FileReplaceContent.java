package com.santander.utils;

import lombok.Builder;
import lombok.extern.java.Log;
import org.apache.commons.cli.Option;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

@Builder
@Log
public class FileReplaceContent {

    private String[] inputs;
    private String[] outputs;
    private String object;
    private String project;
    private String serviceName;
    private String source;
    private String  description;

    public boolean modifyTemplate() throws IOException {
        int i = 0;
        for (String input: inputs) {
            String content = Utils.getContent(input);
            if (content.isEmpty()) {
                log.severe("Content is empty!: " + new File(input).getAbsolutePath());
                return false;
            }
            HashMap<String, String> items = new HashMap<>();
            items.put("@ObjectName", object);
            items.put("@ProjectId", project);
            items.put("@CurrtDate", new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));
            items.put("@FunctionName", serviceName);
            if(source == null || source.isEmpty()) {
                items.put("@Source", object.trim().substring(0, 3) + "SORC");
            } else {
                items.put("@Source", source.trim());
            }
            if(description == null || description.isEmpty()) {
                items.put("@description", "opis");
            } else {
                items.put("@description", description.trim());
            }

            if (!Utils.writeFile(Utils.replaceAll(content, items), outputs[i])) {
                log.severe("Error replaceAll()!: " + new File(input).getAbsolutePath());
                return false;
            }
            i++;
        }
        return true;
    }

}
