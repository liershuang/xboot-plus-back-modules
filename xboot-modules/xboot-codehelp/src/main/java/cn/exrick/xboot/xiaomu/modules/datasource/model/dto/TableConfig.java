/**
 * Copyright (C), 2019, 小木
 * FileName: TableConfig
 * Author:   xiaomu
 * Date:     2019/10/21 23:52
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.datasource.model.dto;

import cn.exrick.xboot.xiaomu.common.base.BaseEntity;
import lombok.Data;

@Data
public class TableConfig extends BaseEntity {

    private String userId;

    private String tableName;

    private String description;

}
