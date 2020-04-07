/**
 * Copyright (C), 2019, 小木
 * FileName: CodeBuildController
 * Author:   xiaomu
 * Date:     2019/10/21 23:03
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.generate.controller;

import cn.exrick.xboot.core.common.utils.SecurityUtil;
import cn.exrick.xboot.core.entity.User;
import cn.exrick.xboot.xiaomu.common.exception.Result;
import cn.exrick.xboot.xiaomu.modules.generate.model.bo.BuildModel;
import cn.exrick.xboot.xiaomu.modules.generate.service.CodeBuildService;
import cn.exrick.xboot.xiaomu.modules.template.model.bo.ResultNode;
import cn.exrick.xboot.xiaomu.modules.template.model.bo.TreeResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/xboot/code/build")
public class CodeBuildController {

    @Autowired
    private CodeBuildService codeBuildService;
    @Autowired
    private SecurityUtil securityUtil;


    @RequestMapping("getProcessedTemplateList")
    public Result build(BuildModel buildModel){
        List<ResultNode> processedTemplateList = codeBuildService.processTemplate(buildModel);
        return Result.ok().put("processedTemplateList", processedTemplateList);
    }

    /**
     * 下载解析后模板组
     * @param buildModel
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("generate")
    public Result generate(BuildModel buildModel, HttpServletResponse httpServletResponse){
        codeBuildService.generate(buildModel, httpServletResponse);
        return Result.ok();
    }

    @RequestMapping("getTreeResultNode")
    public Result getTreeProcessedTemplateList(BuildModel buildModel){
        TreeResultNode treeNode = codeBuildService.getTreeResultNode(buildModel);
        return Result.ok().put("treeProcessedTemplate", treeNode);
    }







}
