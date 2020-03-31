/**
 * Copyright (C), 2019, 小木
 * FileName: ColumnConfig
 * Author:   xiaomu
 * Date:     2019/10/22 0:01
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.datasource.model.dto;

import cn.exrick.xboot.xiaomu.common.base.BaseEntity;
import lombok.Data;

@Data
public class ColumnConfig extends BaseEntity {

    private String userId;
    private Integer tableId;
    private String columnName;
    private String columnType;
    private Integer columnLength;
    private String description;
    private boolean isKey;

}
