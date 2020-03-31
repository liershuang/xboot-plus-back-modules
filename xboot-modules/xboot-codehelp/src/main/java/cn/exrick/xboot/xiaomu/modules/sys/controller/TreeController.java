/**
 * Copyright (C), 2019, 小木
 * FileName: TreeController
 * Author:   xiaomu
 * Date:     2019/12/22 14:21
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.sys.controller;

import cn.exrick.xboot.xiaomu.common.exception.Result;
import cn.exrick.xboot.xiaomu.modules.sys.model.dto.Tree;
import cn.exrick.xboot.xiaomu.modules.sys.model.vo.ChildTreeAddVo;
import cn.exrick.xboot.xiaomu.modules.sys.model.vo.ParentTreeAddVo;
import cn.exrick.xboot.xiaomu.modules.sys.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/xboot/code/tree")
public class TreeController {

    @Autowired
    private TreeService treeService;


    /**
     * 增加节点
     * @param partnerTreeVo
     * @return
     */
    @RequestMapping("addParent")
    public Result addParent(ParentTreeAddVo partnerTreeVo){
        treeService.addParent(partnerTreeVo);
        return Result.ok();
    }

    @RequestMapping("addChild")
    public Result addChild(ChildTreeAddVo childTreeAddVo){
        treeService.addChild(childTreeAddVo);
        return Result.ok();
    }

    @Transactional
    @RequestMapping("deleteNode")
    public Result deleteNode(Integer id){
        treeService.removeById(id);
        treeService.deleteChild(id);
        return Result.ok();
    }

    @RequestMapping("renameNode")
    public Result renameNode(Integer id, String nodeName){
        Tree tree = new Tree();
        tree.setId(id);
        tree.setNodeName(nodeName);
        treeService.updateById(tree);
        return Result.ok();
    }



}
