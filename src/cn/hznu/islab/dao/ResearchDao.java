package cn.hznu.islab.dao;

import cn.hznu.islab.entity.ResearchEntity;

import java.util.HashMap;
import java.util.List;

public interface ResearchDao {
    //添加研究方向
    void addResearch(ResearchEntity researchEntity);
    //删除研究方向
    void deleteResearch(ResearchEntity researchEntity);
    //更新研究方向
    void updateResearch(ResearchEntity researchEntity);
    //根据id查找研究方向
    ResearchEntity findResearchById(int id);
    //返回根据一个或多个字段查找一个或多个研究方向
    List<ResearchEntity> findResearchsByProperties(HashMap<String ,Object> queryMap);
    //返回所有研究方向
    List<ResearchEntity> findAllResearchs();
}
