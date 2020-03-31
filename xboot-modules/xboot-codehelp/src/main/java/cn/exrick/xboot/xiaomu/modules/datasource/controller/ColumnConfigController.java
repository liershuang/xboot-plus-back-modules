/**
 * Copyright (C), 2019, 小木
 * FileName: ColumnConfigController
 * Author:   xiaomu
 * Date:     2019/12/20 18:05
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.datasource.controller;

import cn.exrick.xboot.xiaomu.common.exception.Result;
import cn.exrick.xboot.xiaomu.modules.datasource.model.dto.ColumnConfig;
import cn.exrick.xboot.xiaomu.modules.datasource.model.vo.ColumnConfigVo;
import cn.exrick.xboot.xiaomu.modules.datasource.service.ColumnConfigService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/xboot/code/column")
public class ColumnConfigController {

    @Autowired
    private ColumnConfigService columnConfigService;


    @RequestMapping("list")
    public Result getColumnList(ColumnConfigVo columnConfigVo,
                                @RequestParam(defaultValue="0")int pageNo, @RequestParam(defaultValue="10")int pageSize){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(columnConfigVo.getId() != null, "id", columnConfigVo.getId());
        wrapper.eq(columnConfigVo.getTableId() != null, "table_id", columnConfigVo.getTableId());
        wrapper.eq(StringUtils.isNotBlank(columnConfigVo.getColumnName()), "column_name", columnConfigVo.getColumnName());

        IPage tableConfigPage = columnConfigService.page(new Page<>(pageNo, pageSize), wrapper);
        return Result.ok().put("page", tableConfigPage);
    }

    @RequestMapping("add")
    public Result addColumn(ColumnConfig columnConfig){
        columnConfigService.save(columnConfig);
        return Result.ok();
    }

    /**
     * 删除
     * @param userId
     * @param ids 多个逗号隔开
     * @return
     */
    @RequestMapping("del")
    public Result delColumn(String userId, String ids){
        columnConfigService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok();
    }



}
