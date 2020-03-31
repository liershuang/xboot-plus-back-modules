/**
 * Copyright (C), 2019, 小木
 * FileName: FreemarkerUtil
 * Author:   xiaomu
 * Date:     2019/10/21 22:36
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.common.utils;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class FreemarkerUtil {


    public static String process(String templateContent, Map<String,Object> dataMap) {

        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate("codeTemplate", templateContent);

        Configuration cfg = new Configuration();
        cfg.setDefaultEncoding("utf-8");
        cfg.setTemplateLoader(stringLoader);
        StringWriter out = new StringWriter();
        try {
            Template template = cfg.getTemplate("codeTemplate","utf-8");
            template.process(dataMap, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(out!=null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return out.toString();
    }


}
