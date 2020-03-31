/**
 * Copyright (C), 2019, 小木
 * FileName: TemplateController
 * Author:   xiaomu
 * Date:     2019/12/21 17:57
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.template.controller;

import cn.exrick.xboot.xiaomu.common.exception.Result;
import cn.exrick.xboot.xiaomu.modules.template.model.dto.Template;
import cn.exrick.xboot.xiaomu.modules.template.model.vo.TemplateVo;
import cn.exrick.xboot.xiaomu.modules.template.service.TemplateService;
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
@RequestMapping("/xboot/code/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @RequestMapping("list")
    public Result getColumnList(TemplateVo templateVo,
                                @RequestParam(defaultValue="0")int pageNo, @RequestParam(defaultValue="10")int pageSize){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(templateVo.getId() != null, "id", templateVo.getId());
        wrapper.eq(templateVo.getUserId() != null, "user_id", templateVo.getUserId());
        wrapper.like(StringUtils.isNotBlank(templateVo.getTemplateName()), "template_name", templateVo.getTemplateName());

        IPage templatePage = templateService.page(new Page<>(pageNo, pageSize), wrapper);
        return Result.ok().put("page", templatePage);
    }

    @RequestMapping("add")
    public Result addColumn(Template template){
        templateService.save(template);
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
        templateService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok();
    }


}
