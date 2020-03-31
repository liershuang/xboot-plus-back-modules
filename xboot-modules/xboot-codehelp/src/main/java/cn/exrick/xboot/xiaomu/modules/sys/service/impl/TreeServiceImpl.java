/**
 * Copyright (C), 2019, 小木
 * FileName: TreeServiceImpl
 * Author:   xiaomu
 * Date:     2019/10/23 23:12
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.sys.service.impl;

import cn.exrick.xboot.xiaomu.modules.sys.convert.TreeBoConvert;
import cn.exrick.xboot.xiaomu.modules.sys.dao.TreeDao;
import cn.exrick.xboot.xiaomu.modules.sys.model.bo.TreeBo;
import cn.exrick.xboot.xiaomu.modules.sys.model.dto.Tree;
import cn.exrick.xboot.xiaomu.modules.sys.model.vo.ChildTreeAddVo;
import cn.exrick.xboot.xiaomu.modules.sys.model.vo.ParentTreeAddVo;
import cn.exrick.xboot.xiaomu.modules.sys.service.TreeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreeServiceImpl extends ServiceImpl<TreeDao, Tree> implements TreeService {

    @Autowired
    private TreeDao treeDao;


    @Override
    public List<TreeBo> getTree(int nodeGroupId){
        QueryWrapper queryWrapper = new QueryWrapper<Tree>();
        queryWrapper.eq("node_group_id", nodeGroupId);
        queryWrapper.orderByAsc("node_path");
        List<Tree> treeList = treeDao.selectList(queryWrapper);
        return analyseTree(treeList);
    }

    @Override
    public List<Tree> getTreeList(int nodeGroupId) {
        QueryWrapper queryWrapper = new QueryWrapper<Tree>();
        queryWrapper.eq("node_group_id", nodeGroupId);
        List<Tree> treeList = treeDao.selectList(queryWrapper);
        return treeList==null?new ArrayList():treeList;
    }

    @Override
    public List<TreeBo> analyseTree(List<Tree> treeList) {
        if(CollectionUtils.isEmpty(treeList)) return null;
        //树查询时默认已经按照node_path降序排序，第一个元素即为根节点，某则需要显示排序
        //注意：此处父节点可能有多个，不是唯一一个根节点
        /*Collections.sort(treeList, new Comparator<TreeBo>() {
            @Override
            public int compare(TreeBo o1, TreeBo o2) {
                return o1.getNodePath().compareTo(o2.getNodePath());
            }
        });*/

        List<TreeBo> treeBoList = new TreeBoConvert().convert(treeList);
        if(treeBoList.size() == 1) return treeBoList;

        List<TreeBo> resultTreeNodeList = new ArrayList<>();

        List<TreeBo> parentNodeList = getParentNodeList(treeBoList);
        for(TreeBo treeBo : parentNodeList){
            setChildList(treeBoList, treeBo);
            resultTreeNodeList.add(treeBo);
        }

        return resultTreeNodeList;
    }

    @Override
    public List<Tree> getChild(int id){
        return treeDao.getChildTree(id);
    }

    @Override
    public void deleteChild(int id){
        treeDao.deleteChild(id);
    }

    @Override
    public void addParent(ParentTreeAddVo partnerTreeAddVo) {
        treeDao.addParent(partnerTreeAddVo);
    }

    @Override
    public void addChild(ChildTreeAddVo childTreeAddVo) {
        treeDao.addChild(childTreeAddVo);
    }






    /**
     * 找出顶级节点列表（顶级节点可能有多个并行的，故为列表）
     * @param treeBoList
     * @return
     */
    private List<TreeBo> getParentNodeList(List<TreeBo> treeBoList){
        List<TreeBo> parentNodeList = new ArrayList<>();

        for(TreeBo outTreeBo : treeBoList){
            boolean parentFlag = true;
            for(TreeBo innerTreeBo : treeBoList){
                if(outTreeBo.getNodePath().contains(innerTreeBo.getNodePath())
                        && !outTreeBo.getNodePath().equals(innerTreeBo.getNodePath())){
                    parentFlag = false;
                }
            }
            if(parentFlag) parentNodeList.add(outTreeBo);
        }

        return parentNodeList;
    }

    /**
     * 解析设置树对象子节点列表
     * @param treeList
     * @param parentTree
     * @return
     */
    private TreeBo setChildList(List<TreeBo> treeList, TreeBo parentTree){
        List<TreeBo> childTreeList = new ArrayList<>();
        if(CollectionUtils.isEmpty(treeList)) return parentTree;

        int parentNodeLength = parentTree.getNodePath().split("\\/").length;
        for(TreeBo tree : treeList){
            //包含父节点路径且长度等于父节点长度+1的节点，满足条件的为子节点
            if(tree.getNodePath().indexOf(parentTree.getNodePath())>=0
                    && tree.getNodePath().split("\\/").length==parentNodeLength+1){
                childTreeList.add(tree);
            }
        }
        parentTree.setChildList(childTreeList);
        treeList.removeAll(childTreeList);//已作为子节点的数据移除，减少后续遍历次数，同时防止多个树时节点重复

        if(childTreeList.size() > 0){
            for(TreeBo tree : childTreeList){
                setChildList(treeList, tree);
            }
        }
        return parentTree;
    }

}
