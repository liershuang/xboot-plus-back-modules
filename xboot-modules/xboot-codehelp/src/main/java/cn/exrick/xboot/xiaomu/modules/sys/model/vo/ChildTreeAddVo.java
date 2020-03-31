/**
 * Copyright (C), 2019, 小木
 * FileName: ChildAddVo
 * Author:   xiaomu
 * Date:     2019/12/23 9:44
 * Description: 子节点添加入参类
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.sys.model.vo;

import lombok.Data;

@Data
public class ChildTreeAddVo {

    private Integer parentId;

    private String nodeName;

    private Integer businId;


}
