/**
 * Copyright (C), 2019, 小木
 * FileName: CodeTemplate
 * Author:   xiaomu
 * Date:     2019/10/21 23:28
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.template.model.dto;

import cn.exrick.xboot.xiaomu.common.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Template extends BaseEntity {

    /** 用户id */
    private Integer userId;
    /** 模板名称 */
    private String templateName;
    /** 模板内容 */
    private String content;
    /** 描述 */
    private String description;



}
