package cn.exrick.xboot.activiti.service;

import cn.exrick.xboot.activiti.entity.ActCategory;
import cn.exrick.xboot.core.base.XbootBaseService;

import java.util.List;

/**
 * 流程分类接口
 * @author Exrick
 */
public interface ActCategoryService extends XbootBaseService<ActCategory, String> {

    /**
     * 通过父id获取
     * @param parentId
     * @return
     */
    List<ActCategory> findByParentIdOrderBySortOrder(String parentId);

    /**
     * 通过名称模糊搜索
     * @param title
     * @return
     */
    List<ActCategory> findByTitleLikeOrderBySortOrder(String title);
}