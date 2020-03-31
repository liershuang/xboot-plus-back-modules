/**
 * Copyright (C), 2019, 小木
 * FileName: ColumnConfigServiceImpl
 * Author:   xiaomu
 * Date:     2019/12/20 18:07
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.datasource.service.impl;

import cn.exrick.xboot.xiaomu.modules.datasource.dao.ColumnConfigDao;
import cn.exrick.xboot.xiaomu.modules.datasource.model.dto.ColumnConfig;
import cn.exrick.xboot.xiaomu.modules.datasource.service.ColumnConfigService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnConfigServiceImpl extends ServiceImpl<ColumnConfigDao,ColumnConfig> implements ColumnConfigService {


    @Override
    public List<ColumnConfig> getColumnByTableId(Integer tableId){
        QueryWrapper<ColumnConfig> queryWrapper = new QueryWrapper();
        queryWrapper.eq("table_id", tableId);
        List<ColumnConfig> columnConfigList = this.list(queryWrapper);
        return columnConfigList;
    }

}
