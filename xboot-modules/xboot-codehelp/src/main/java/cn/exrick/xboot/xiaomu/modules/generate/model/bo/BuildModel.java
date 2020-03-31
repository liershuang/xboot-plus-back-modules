/**
 * Copyright (C), 2019, 小木
 * FileName: BuildModel
 * Author:   xiaomu
 * Date:     2019/10/21 23:27
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.generate.model.bo;

import lombok.Data;

import java.util.Map;

@Data
public class BuildModel {

    /** 模板分组id */
    private int groupId;
    /** 用户提交参数，包括默认属性和自定义属性，默认属性目前有：author(作者) */
    private Map<String, Object> dataMap;
    /** 表id，逗哥逗号隔开 */
    private String tableIds;


}
