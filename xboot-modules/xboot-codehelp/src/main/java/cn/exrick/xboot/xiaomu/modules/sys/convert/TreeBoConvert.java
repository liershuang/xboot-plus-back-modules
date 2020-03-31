/**
 * Copyright (C), 2019, 小木
 * FileName: TreeConvert
 * Author:   xiaomu
 * Date:     2019/10/25 18:15
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.sys.convert;

import cn.exrick.xboot.xiaomu.common.convert.BoConvert;
import cn.exrick.xboot.xiaomu.modules.sys.model.bo.TreeBo;
import cn.exrick.xboot.xiaomu.modules.sys.model.dto.Tree;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class TreeBoConvert implements BoConvert<Tree, TreeBo> {


    @Override
    public TreeBo convert(Tree tree) {
        TreeBo treeBo = new TreeBo();
        treeBo.setId(tree.getId());
        treeBo.setBusinId(tree.getBusinId());
        treeBo.setNodeName(tree.getNodeName());
        treeBo.setNodePath(tree.getNodePath());
        treeBo.setTreeType(tree.getTreeType());
        return treeBo;
    }

    @Override
    public List<TreeBo> convert(List<Tree> trees) {
        List<TreeBo> treeBoList = new ArrayList<>();
        if(CollectionUtils.isEmpty(trees)) return treeBoList;
        for(Tree tree : trees){
            treeBoList.add(convert(tree));
        }
        return treeBoList;
    }

}
