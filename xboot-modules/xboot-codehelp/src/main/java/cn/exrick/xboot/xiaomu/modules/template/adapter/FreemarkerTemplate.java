/**
 * Copyright (C), 2019, 小木
 * FileName: FreemarkerTemplate
 * Author:   xiaomu
 * Date:     2019/10/21 21:52
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.template.adapter;


import cn.exrick.xboot.xiaomu.common.utils.FreemarkerUtil;

import java.util.Map;

public class FreemarkerTemplate implements TemplateFunction {

    @Override
    public String process(String templateContent, Map<String, Object> dataMap) {
        return FreemarkerUtil.process(templateContent, dataMap);
    }

}
