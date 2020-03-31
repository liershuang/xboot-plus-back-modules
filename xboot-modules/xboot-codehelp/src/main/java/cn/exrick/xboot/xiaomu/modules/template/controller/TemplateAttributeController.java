/**
 * Copyright (C), 2019, 小木
 * FileName: TemplateAttributeController
 * Author:   xiaomu
 * Date:     2019/12/21 18:16
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.template.controller;

import cn.exrick.xboot.xiaomu.common.exception.Result;
import cn.exrick.xboot.xiaomu.modules.template.model.dto.TemplateAttribute;
import cn.exrick.xboot.xiaomu.modules.template.model.vo.TemplateAttributeVo;
import cn.exrick.xboot.xiaomu.modules.template.service.TemplateAttributeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/xboot/code/templateAttribute")
public class TemplateAttributeController {

    @Autowired
    private TemplateAttributeService templateAttributeService;


    @RequestMapping("list")
    public Result getColumnList(TemplateAttributeVo templateAttributeVoVo,
                                @RequestParam(defaultValue="0")int pageNo, @RequestParam(defaultValue="10")int pageSize){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(templateAttributeVoVo.getId() != null, "id", templateAttributeVoVo.getId());
        wrapper.eq(templateAttributeVoVo.getTemplateGroupId() != null, "user_id", templateAttributeVoVo.getTemplateGroupId());

        IPage templateAttributePage = templateAttributeService.page(new Page<>(pageNo, pageSize), wrapper);
        return Result.ok().put("page", templateAttributePage);
    }

    @RequestMapping("add")
    public Result addColumn(TemplateAttribute templateAttribute){
        templateAttributeService.save(templateAttribute);
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
        templateAttributeService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok();
    }


}
