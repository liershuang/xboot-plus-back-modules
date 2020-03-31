/**
 * Copyright (C), 2019, 小木
 * FileName: TableConfigServiceImpl
 * Author:   xiaomu
 * Date:     2019/12/20 15:37
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.datasource.service.impl;

import cn.exrick.xboot.xiaomu.modules.datasource.dao.TableConfigDao;
import cn.exrick.xboot.xiaomu.modules.datasource.model.dto.TableConfig;
import cn.exrick.xboot.xiaomu.modules.datasource.service.TableConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TableConfigServiceImpl extends ServiceImpl<TableConfigDao, TableConfig> implements TableConfigService {



}
