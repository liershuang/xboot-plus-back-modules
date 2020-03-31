/**
 * Copyright (C), 2019, 小木
 * FileName: ColumnConfig
 * Author:   xiaomu
 * Date:     2019/10/22 0:01
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.datasource.model.bo;

import lombok.Data;

@Data
public class ColumnConfigBo {

    private String columnName;
    private String columnType;
    private Integer columnLength;
    private String description;
    /** 是否主键，0：否，1：是 */
    private int isKey;

}
