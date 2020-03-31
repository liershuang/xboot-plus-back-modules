package cn.exrick.xboot.xiaomu.modules.sys.dao;

import cn.exrick.xboot.xiaomu.modules.sys.model.dto.Tree;
import cn.exrick.xboot.xiaomu.modules.sys.model.vo.ChildTreeAddVo;
import cn.exrick.xboot.xiaomu.modules.sys.model.vo.ParentTreeAddVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TreeDao extends BaseMapper<Tree> {

    List<Tree> getChildTree(int id);

    void deleteChild(int id);

    void addParent(ParentTreeAddVo partnerTreeAddVo);

    void addChild(ChildTreeAddVo childTreeAddVo);

}
