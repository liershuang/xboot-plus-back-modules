/**
 * Copyright (C), 2019, 小木
 * FileName: CodeBuildController
 * Author:   xiaomu
 * Date:     2019/10/21 23:03
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.generate.controller;

import cn.exrick.xboot.xiaomu.common.exception.Result;
import cn.exrick.xboot.xiaomu.modules.generate.model.bo.BuildModel;
import cn.exrick.xboot.xiaomu.modules.generate.service.CodeBuildService;
import cn.exrick.xboot.xiaomu.modules.template.model.bo.ResultNode;
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


    @RequestMapping("getProcessedTemplateList")
    public Result build(BuildModel buildModel){
        List<ResultNode> processedTemplateList = codeBuildService.processTemplate(buildModel);
        return Result.ok().put("processedTemplateList", processedTemplateList);
    }

    @RequestMapping("generate")
    public Result generate(BuildModel buildModel, HttpServletResponse httpServletResponse){
        codeBuildService.generate(buildModel, httpServletResponse);
        return Result.ok();
    }







}
