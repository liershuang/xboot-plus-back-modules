/**
 * Copyright (C), 2019, 小木
 * FileName: TreeBo
 * Author:   xiaomu
 * Date:     2019/10/25 10:31
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.sys.model.bo;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
//@Builder
public class TreeBo {

    /** 用户id */
    private Integer id;
    /** 1(默认):代码模板组树 */
    private Integer treeType;
    /** 节点路径 */
    private String nodePath;
    /** 节点名称 */
    private String nodeName;
    /** 节点对应业务id，如模板id */
    private Integer businId;
    /** 子节点 */
//    @Builder.Default
    private List<TreeBo> childList = Collections.emptyList();


}
