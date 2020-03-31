/**
 * Copyright (C), 2019, 小木
 * FileName: TemplateServiceImpl
 * Author:   xiaomu
 * Date:     2019/12/19 10:25
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.template.service.impl;

import cn.exrick.xboot.xiaomu.common.utils.PageUtils;
import cn.exrick.xboot.xiaomu.modules.template.dao.TemplateDao;
import cn.exrick.xboot.xiaomu.modules.template.model.dto.Template;
import cn.exrick.xboot.xiaomu.modules.template.model.vo.TemplateVo;
import cn.exrick.xboot.xiaomu.modules.template.service.TemplateService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl extends ServiceImpl<TemplateDao, Template> implements TemplateService {

    @Autowired
    private TemplateDao templateDao;


    @Override
    public PageUtils queryPage(TemplateVo templateVo, int pageNo, int pageSize) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", templateVo.getUserId());
        wrapper.like("template_name", templateVo.getTemplateName());

        IPage<Template> page = this.page(new Page<>(pageNo, pageSize), wrapper);

        return new PageUtils(page);
    }







}
