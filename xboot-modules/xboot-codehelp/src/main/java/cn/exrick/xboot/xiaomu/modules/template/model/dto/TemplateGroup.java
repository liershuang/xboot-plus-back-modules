/**
 * Copyright (C), 2019, 小木
 * FileName: TemplateGroup
 * Author:   xiaomu
 * Date:     2019/10/23 22:06
 * Description: 模板组
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.template.model.dto;


import cn.exrick.xboot.xiaomu.common.base.BaseEntity;
import lombok.Data;

@Data
public class TemplateGroup extends BaseEntity {

    /** 用户id */
    private Integer userId;
    /** 模板组名称 */
    private String TemplateGroupName;
    /** 描述 */
    private String description;


}
