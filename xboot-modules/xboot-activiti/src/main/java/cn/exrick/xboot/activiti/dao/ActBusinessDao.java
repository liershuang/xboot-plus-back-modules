package cn.exrick.xboot.activiti.dao;

import cn.exrick.xboot.activiti.entity.ActBusiness;
import cn.exrick.xboot.core.base.XbootBaseDao;

import java.util.List;

/**
 * 申请业务数据处理层
 * @author Exrick
 */
public interface ActBusinessDao extends XbootBaseDao<ActBusiness, String> {

    /**
     * 通过流程定义id获取
     * @param procDefId
     * @return
     */
    List<ActBusiness> findByProcDefId(String procDefId);
}