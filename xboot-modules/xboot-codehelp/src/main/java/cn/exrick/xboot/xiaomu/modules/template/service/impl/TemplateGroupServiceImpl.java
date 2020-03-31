/**
 * Copyright (C), 2019, 小木
 * FileName: TemplateGroupServiceImpl
 * Author:   xiaomu
 * Date:     2019/12/19 18:51
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.template.service.impl;

import cn.exrick.xboot.xiaomu.modules.sys.model.bo.TreeBo;
import cn.exrick.xboot.xiaomu.modules.sys.service.TreeService;
import cn.exrick.xboot.xiaomu.modules.template.dao.TemplateGroupDao;
import cn.exrick.xboot.xiaomu.modules.template.model.bo.NodeTemplate;
import cn.exrick.xboot.xiaomu.modules.template.model.dto.Template;
import cn.exrick.xboot.xiaomu.modules.template.model.dto.TemplateGroup;
import cn.exrick.xboot.xiaomu.modules.template.service.TemplateGroupService;
import cn.exrick.xboot.xiaomu.modules.template.service.TemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TemplateGroupServiceImpl extends ServiceImpl<TemplateGroupDao, TemplateGroup> implements TemplateGroupService {

    @Autowired
    private TreeService treeService;
    @Autowired
    private TemplateService templateService;
    @Autowired
    private TemplateGroupDao templateGroupDao;


    @Override
    public List<Template> getGroupTemplate(int groupId){
        List<Template> templateList = templateGroupDao.getTemplate(groupId);
        return templateList==null?new ArrayList<>():templateList;
    }

    @Override
    public List<NodeTemplate> getTreeTemplatList(int groupId){
        List<NodeTemplate> nodeTemplateList = new ArrayList();

        List<Template> templateList = getGroupTemplate(groupId);
        Map<Integer, Template> templateMap = new HashMap<>();
        for(Template template : templateList){
            templateMap.put(template.getId(), template);
        }

        List<TreeBo> treeBoList = treeService.getTree(groupId);
        for(TreeBo treeBo : treeBoList){
            NodeTemplate nodeTemplate = new NodeTemplate();
            setTreeTemplate(treeBo, nodeTemplate, templateMap);
            nodeTemplateList.add(nodeTemplate);
        }

        return nodeTemplateList;
    }




    /**
     * 递归设置树形模板
     * @param treeBo
     * @param nodeTemplate
     * @param templateMap
     */
    private void setTreeTemplate(TreeBo treeBo, NodeTemplate nodeTemplate, Map<Integer, Template> templateMap){
        nodeTemplate.setNodeId(treeBo.getId());
        nodeTemplate.setBusinId(treeBo.getBusinId());
        nodeTemplate.setNodeName(treeBo.getNodeName());
        if(templateMap.get(treeBo.getBusinId()) != null)
            nodeTemplate.setContent(templateMap.get(treeBo.getBusinId()).getContent());

        List<TreeBo> treeChildList = treeBo.getChildList();
        if(!CollectionUtils.isEmpty(treeChildList)){

            List<NodeTemplate> childTemplateList = new ArrayList<>();
            for(TreeBo tempTree : treeChildList){
                NodeTemplate childTemplate = new NodeTemplate();
                setTreeTemplate(tempTree, childTemplate,templateMap);
                childTemplateList.add(childTemplate);
            }
            nodeTemplate.setChildList(childTemplateList);
        }
    }


}
