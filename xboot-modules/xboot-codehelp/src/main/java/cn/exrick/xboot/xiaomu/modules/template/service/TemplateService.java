package cn.exrick.xboot.xiaomu.modules.template.service;

import cn.exrick.xboot.xiaomu.common.utils.PageUtils;
import cn.exrick.xboot.xiaomu.modules.template.model.dto.Template;
import cn.exrick.xboot.xiaomu.modules.template.model.vo.TemplateVo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface TemplateService extends IService<Template> {

    PageUtils queryPage(TemplateVo templateVo, int pageNo, int pageSize);

}
