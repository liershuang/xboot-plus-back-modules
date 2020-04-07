package cn.exrick.xboot.xiaomu.modules.generate.service;


import cn.exrick.xboot.xiaomu.modules.generate.model.bo.BuildModel;
import cn.exrick.xboot.xiaomu.modules.template.model.bo.ResultNode;
import cn.exrick.xboot.xiaomu.modules.template.model.bo.TreeResultNode;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CodeBuildService {

    /**
     * 根据模板解析模板名称、路径和模板内容并返回
     * @param buildModel
     * @return
     */
    List<ResultNode> processTemplate(BuildModel buildModel);

    /**
     * 打包生成代码
     * @param buildModel
     * @return
     */
    void generate(BuildModel buildModel, HttpServletResponse httpServletResponse);

    /**
     * 获取树形结构节点列表
     * @param buildModel
     * @return
     */
    TreeResultNode getTreeResultNode(BuildModel buildModel);

}
