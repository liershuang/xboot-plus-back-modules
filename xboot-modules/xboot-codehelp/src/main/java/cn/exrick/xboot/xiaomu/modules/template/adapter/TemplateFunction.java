package cn.exrick.xboot.xiaomu.modules.template.adapter;

import java.util.Map;

public interface TemplateFunction {

    String process(String templateContent, Map<String, Object> dataMap);

}
