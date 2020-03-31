/**
 * Copyright (C), 2019, 小木
 * FileName: ColumnConfig
 * Author:   xiaomu
 * Date:     2019/10/22 0:01
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.datasource.model.vo;

import lombok.Data;

@Data
public class ColumnConfigVo {

    private String userId;
    private Integer id;
    private Integer tableId;
    private String columnName;

}
