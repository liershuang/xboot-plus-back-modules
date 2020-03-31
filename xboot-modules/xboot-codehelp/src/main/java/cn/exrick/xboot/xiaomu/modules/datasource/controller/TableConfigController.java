/**
 * Copyright (C), 2019, 小木
 * FileName: TableConfigController
 * Author:   xiaomu
 * Date:     2019/12/20 15:32
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.datasource.controller;

import cn.exrick.xboot.xiaomu.common.exception.Result;
import cn.exrick.xboot.xiaomu.modules.datasource.model.dto.TableConfig;
import cn.exrick.xboot.xiaomu.modules.datasource.model.vo.TableConfigVo;
import cn.exrick.xboot.xiaomu.modules.datasource.service.TableConfigService;
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
@RequestMapping("/xboot/code/table")
public class TableConfigController {

    @Autowired
    private TableConfigService tableConfigService;


    @RequestMapping("list")
    public Result getTableList(TableConfigVo tableConfigVo,
                               @RequestParam(defaultValue="0") int pageNo,
                               @RequestParam(defaultValue="10")int pageSize){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(tableConfigVo.getId() != null, "id", tableConfigVo.getId());
        wrapper.eq(StringUtils.isNotBlank(tableConfigVo.getTableName()), "table_name", tableConfigVo.getTableName());
        wrapper.like(StringUtils.isNotBlank(tableConfigVo.getDescription()), "description", tableConfigVo.getDescription());

        IPage tableConfigPage = tableConfigService.page(new Page<>(pageNo, pageSize), wrapper);
        return Result.ok().put("page", tableConfigPage);
    }

    @RequestMapping("add")
    public Result addTable(TableConfig tableConfig){
        tableConfigService.save(tableConfig);
        return Result.ok();
    }

    /**
     * 删除
     * @param userId
     * @param ids 多个逗号隔开
     * @return
     */
    @RequestMapping("del")
    public Result delTable(String userId, String ids){
        tableConfigService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok();
    }


}
