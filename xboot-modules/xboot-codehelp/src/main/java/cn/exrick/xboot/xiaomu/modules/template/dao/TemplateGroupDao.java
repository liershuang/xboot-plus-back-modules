package cn.exrick.xboot.xiaomu.modules.template.dao;

import cn.exrick.xboot.xiaomu.modules.template.model.dto.Template;
import cn.exrick.xboot.xiaomu.modules.template.model.dto.TemplateGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TemplateGroupDao extends BaseMapper<TemplateGroup> {

    List<Template> getTemplate(int groupId);

}
