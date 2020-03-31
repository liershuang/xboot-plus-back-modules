/**
 * Copyright (C), 2019, 小木
 * FileName: TreeService
 * Author:   xiaomu
 * Date:     2019/10/23 23:11
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.sys.service;

import cn.exrick.xboot.xiaomu.modules.sys.model.bo.TreeBo;
import cn.exrick.xboot.xiaomu.modules.sys.model.dto.Tree;
import cn.exrick.xboot.xiaomu.modules.sys.model.vo.ChildTreeAddVo;
import cn.exrick.xboot.xiaomu.modules.sys.model.vo.ParentTreeAddVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TreeService extends IService<Tree> {

    /**
     * 获取树形数据
     * @param nodeGroupId
     * @return
     */
    List<TreeBo> getTree(int nodeGroupId);

    /**
     * 获取子节点列表（不包含本身）
     * @param id 节点id
     * @return
     */
    List<Tree> getChild(int id);

    /**
     * 删除子节点
     * @param id
     */
    void deleteChild(int id);

    /**
     * 添加一级目录
     * @param partnerTreeVo
     */
    void addParent(ParentTreeAddVo partnerTreeVo);

    /**
     * 添加子目录
     * @param childTreeAddVo
     */
    void addChild(ChildTreeAddVo childTreeAddVo);

    /**
     * 获取列表数据
     * @param nodeGroupId
     * @return
     */
    List<Tree> getTreeList(int nodeGroupId);

    /**
     * 解析为树形结构
     * @param treeList
     * @return
     */
    List<TreeBo> analyseTree(List<Tree> treeList);


}
