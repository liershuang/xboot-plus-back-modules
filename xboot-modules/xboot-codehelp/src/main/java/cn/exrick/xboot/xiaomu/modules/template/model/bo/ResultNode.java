/**
 * Copyright (C), 2019, 小木
 * FileName: ResultNode
 * Author:   xiaomu
 * Date:     2019/12/20 13:20
 * Description: 解析后文件目录节点
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.template.model.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultNode {

    /** 节点id */
    private int nodeId;
    /** 节点名称 */
    private String nodeName;
    /** 模板内容 */
    private String content;
    /** 节点路径 */
    private String nodePath;
    /** 是否为文件，默认true */
    private boolean isFile = false;



}
