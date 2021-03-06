package com.hsun.mango;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * @author hsun
 * @version 1.0
 * @since 2017/4/26 14:39
 */
public class SourceGenerator {

    private static Configuration config = null;

    static{
        config = new Configuration();
        config.setClassForTemplateLoading(SourceGenerator.class, "/ftl");
        config.setDefaultEncoding("UTF-8");
    }

    public static void generate(String template, Object data, String target) {
        try {
            Template tp = config.getTemplate(template);

            File file = new File(target);
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();

            Writer out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            tp.setEncoding("UTF-8");
            tp.process(data, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
