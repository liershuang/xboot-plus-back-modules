package cn.exrick.xboot.activiti.dao;

import cn.exrick.xboot.activiti.entity.ActNode;
import cn.exrick.xboot.core.base.XbootBaseDao;

import java.util.List;

/**
 * 流程节点用户数据处理层
 * @author Exrick
 */
public interface ActNodeDao extends XbootBaseDao<ActNode, String> {

    /**
     * 通过nodeId获取
     * @param nodeId
     * @param type
     * @return
     */
    List<ActNode> findByNodeIdAndType(String nodeId, Integer type);

    /**
     * 通过nodeId删除
     * @param nodeId
     */
    void deleteByNodeId(String nodeId);

    /**
     * 通过relateId删除
     * @param relateId
     */
    void deleteByRelateId(String relateId);
}