package cn.exrick.xboot.xiaomu.modules.template.service;

import cn.exrick.xboot.xiaomu.modules.template.model.bo.NodeTemplate;
import cn.exrick.xboot.xiaomu.modules.template.model.dto.Template;
import cn.exrick.xboot.xiaomu.modules.template.model.dto.TemplateGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TemplateGroupService extends IService<TemplateGroup> {

    /**
     * 根据分组id获取模板列表
     * @param groupId
     * @return
     */
    public List<Template> getGroupTemplate(int groupId);

    /**
     * 解析树形模板
     * @param groupId
     * @return
     */
    public List<NodeTemplate> getTreeTemplatList(int groupId);
}
