/**
 * Copyright (C), 2019, 小木
 * FileName: TableConfig
 * Author:   xiaomu
 * Date:     2019/10/21 23:52
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.datasource.model.bo;

import cn.exrick.xboot.xiaomu.modules.datasource.model.dto.ColumnConfig;
import lombok.Data;

import java.util.List;

@Data
public class TableConfigBo {

    private String tableName;

    private String description;

    private List<ColumnConfig> columnList;



}
