package cn.hznu.islab.service;

import cn.hznu.islab.entity.ResearchEntity;

import java.util.HashMap;
import java.util.List;

public interface ResearchService {
    //添加研究方向
    void addResearch(ResearchEntity research);
    //更新研究方向
    void updateResearch(ResearchEntity researchEntity);
    //删除研究方向
    void deleteResearch(ResearchEntity researchEntity);
    //通过某些字段查找一个或多个研究方向
    List<ResearchEntity> findResearchsByProperties(HashMap<String ,Object> queryMap);
    //所有研究方向
    List<ResearchEntity> findAllResearchs();
}
