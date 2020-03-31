/**
 * Copyright (C), 2019, 小木
 * FileName: Tree
 * Author:   xiaomu
 * Date:     2019/10/23 22:42
 * Description: 属性关系表
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.sys.model.dto;

import cn.exrick.xboot.xiaomu.common.base.BaseEntity;
import lombok.Data;

@Data
public class Tree extends BaseEntity {

    /** 1(默认):代码模板组树 */
    private Integer treeType = 1;
    /** 节点分组id，如模板组id，同一个树此组id相同 */
    private Integer nodeGroupId;
    /** 节点路径 */
    private String nodePath;
    /** 节点名称 */
    private String nodeName;
    /** 节点对应业务id，如模板id */
    private Integer businId;

}
