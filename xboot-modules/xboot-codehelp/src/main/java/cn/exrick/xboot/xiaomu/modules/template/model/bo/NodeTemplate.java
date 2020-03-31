/**
 * Copyright (C), 2019, 小木
 * FileName: NodeTemplate
 * Author:   xiaomu
 * Date:     2019/12/19 11:07
 * Description: 树形模板类
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.template.model.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class NodeTemplate {

    /** 节点id */
    private int nodeId;
    /** 节点名称 */
    private String nodeName;
    /** 模板内容 */
    private String content;

    private Integer businId;

    private List<NodeTemplate> childList = Collections.emptyList();

}
