/**
 * Copyright (C), 2019, 小木
 * FileName: TemplateGroupController
 * Author:   xiaomu
 * Date:     2019/12/21 18:05
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.template.controller;

import cn.exrick.xboot.xiaomu.common.exception.Result;
import cn.exrick.xboot.xiaomu.modules.generate.model.vo.TreeTemplateVo;
import cn.exrick.xboot.xiaomu.modules.sys.model.vo.ChildTreeAddVo;
import cn.exrick.xboot.xiaomu.modules.sys.service.TreeService;
import cn.exrick.xboot.xiaomu.modules.template.model.bo.NodeTemplate;
import cn.exrick.xboot.xiaomu.modules.template.model.dto.Template;
import cn.exrick.xboot.xiaomu.modules.template.model.dto.TemplateGroup;
import cn.exrick.xboot.xiaomu.modules.template.model.vo.TemplateGroupVo;
import cn.exrick.xboot.xiaomu.modules.template.service.TemplateGroupService;
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
import java.util.List;

@RestController
@RequestMapping("/xboot/code/templateGroup")
public class TemplateGroupController {

    @Autowired
    private TemplateGroupService templateGroupService;
    @Autowired
    private TemplateService templateService;
    @Autowired
    private TreeService treeService;

    @RequestMapping("list")
    public Result getTemplateGroupList(TemplateGroupVo templateGroupVo,
                                       @RequestParam(defaultValue="0")int pageNo, @RequestParam(defaultValue="10")int pageSize){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(templateGroupVo.getId() != null, "id", templateGroupVo.getId());
        wrapper.eq(templateGroupVo.getUserId() != null, "user_id", templateGroupVo.getUserId());
        wrapper.eq(StringUtils.isNotBlank(templateGroupVo.getTemplateGroupName()), "template_group_name", templateGroupVo.getTemplateGroupName());

        IPage templateGroupPage = templateGroupService.page(new Page<>(pageNo, pageSize), wrapper);
        return Result.ok().put("page", templateGroupPage);
    }

    /**
     *
     * @param templateGroup
     * @return
     */
    @RequestMapping("add")
    public Result addTemplateGroup(TemplateGroup templateGroup){
        templateGroupService.save(templateGroup);
        return Result.ok();
    }

    /**
     * 删除
     * @param userId
     * @param ids 多个逗号隔开
     * @return
     */
    @RequestMapping("del")
    public Result delTemplateGroup(String userId, String ids){
        templateGroupService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok();
    }

    @RequestMapping("getTemplateGroupTree")
    public Result getCodeTree(Integer nodeGroupId){
        List<NodeTemplate> treeTemplateList = templateGroupService.getTreeTemplatList(nodeGroupId);

        return Result.ok().put("treeTemplateList", treeTemplateList);
    }

    /**
     * 添加模板到树结构中
     * @param treeTemplateVo
     * @return
     */
    @RequestMapping("addTreeTemplate")
    public Result addTreeTemplate(TreeTemplateVo treeTemplateVo){
        Template template = templateService.getById(treeTemplateVo.getTemplateId());

        ChildTreeAddVo childTreeAddVo = new ChildTreeAddVo();
        childTreeAddVo.setParentId(treeTemplateVo.getParentId());
        childTreeAddVo.setNodeName(template.getTemplateName());
        childTreeAddVo.setBusinId(template.getId());
        treeService.addChild(childTreeAddVo);

        return Result.ok();
    }

}
