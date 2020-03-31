/**
 * Copyright (C), 2019, 小木
 * FileName: ParentTreeVo
 * Author:   xiaomu
 * Date:     2019/12/23 9:33
 * Description:一级目录添加入参类
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.sys.model.vo;

import lombok.Data;

@Data
public class ParentTreeAddVo {

    private Integer treeType = 1;
    private Integer nodeGroupId;
    private String nodeName;

}
