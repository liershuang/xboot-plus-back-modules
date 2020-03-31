/**
 * Copyright (C), 2019, 小木
 * FileName: TemplateAttribute
 * Author:   xiaomu
 * Date:     2019/10/23 22:21
 * Description: 模板属性表
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.template.model.dto;

import cn.exrick.xboot.xiaomu.common.base.BaseEntity;
import lombok.Data;

@Data
public class TemplateAttribute extends BaseEntity {

    /** 模板组id */
    private Integer templateGroupId;
    /** 模板组属性key */
    private String attributeKey;
    /** 模板组属性值 */
    private String attributeValue;


}
