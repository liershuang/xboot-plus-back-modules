/**
 * Copyright (C), 2019, 小木
 * FileName: ColumnConfigService
 * Author:   xiaomu
 * Date:     2019/12/20 18:06
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.datasource.service;

import cn.exrick.xboot.xiaomu.modules.datasource.model.dto.ColumnConfig;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ColumnConfigService extends IService<ColumnConfig> {

    List<ColumnConfig> getColumnByTableId(Integer tableId);

}
