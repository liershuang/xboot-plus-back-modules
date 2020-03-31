package cn.exrick.xboot.activiti.service;

import cn.exrick.xboot.activiti.entity.ActModel;
import cn.exrick.xboot.core.base.XbootBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 模型管理接口
 * @author Exrick
 */
public interface ActModelService extends XbootBaseService<ActModel, String> {

    /**
     * 多条件分页获取
     * @param actModel
     * @param pageable
     * @return
     */
    Page<ActModel> findByCondition(ActModel actModel, Pageable pageable);
}