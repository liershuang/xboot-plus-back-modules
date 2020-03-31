package cn.exrick.xboot.activiti.service;

import cn.exrick.xboot.activiti.entity.ActNode;
import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.core.entity.Department;
import cn.exrick.xboot.core.entity.Role;
import cn.exrick.xboot.core.entity.User;

import java.util.List;

/**
 * 流程节点用户接口
 * @author Exrick
 */
public interface ActNodeService extends XbootBaseService<ActNode, String> {

    /**
     * 通过nodeId获取用户
     * @param nodeId
     * @return
     */
    List<User> findUserByNodeId(String nodeId);

    /**
     * 通过nodeId获取角色
     * @param nodeId
     * @return
     */
    List<Role> findRoleByNodeId(String nodeId);

    /**
     * 通过nodeId获取部门
     * @param nodeId
     * @return
     */
    List<Department> findDepartmentByNodeId(String nodeId);

    /**
     * 通过nodeId获取部门id
     * @param nodeId
     * @return
     */
    List<String> findDepartmentIdsByNodeId(String nodeId);

    /**
     * 是否有选操作人的部门负责人
     * @param nodeId
     * @return
     */
    Boolean hasChooseDepHeader(String nodeId);

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