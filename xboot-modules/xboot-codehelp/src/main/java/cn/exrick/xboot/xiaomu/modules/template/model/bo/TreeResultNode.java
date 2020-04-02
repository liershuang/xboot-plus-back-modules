/**
 * Copyright (C), 2020, 小木
 * FileName: TreeResultNode
 * Author:   xiaomu
 * Date:     2020/4/2 11:17
 * Description: 树形结构节点
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.template.model.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TreeResultNode {

    /** 节点id */
    private int nodeId;
    /** 节点名称 */
    private String nodeName;
    /** 模板内容 */
    private String content;
    /** 节点路径 */
    private String nodePath;
    /** 是否为文件，默认false */
    private boolean isFile = false;
    /** 子节点 */
    private List<TreeResultNode> childList;
}
