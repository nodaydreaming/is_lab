package cn.hznu.islab.service;

import cn.hznu.islab.entity.CompetitionEntity;

import java.util.HashMap;
import java.util.List;

public interface CompetitionService {
    //添加竞赛情况
    void addCompetition(CompetitionEntity competition);
    //更新竞赛情况
    void updateCompetition(CompetitionEntity competitionEntity);
    //删除竞赛情况
    void deleteCompetition(CompetitionEntity competitionEntity);
    //通过某些字段查找一个或多个竞赛情况
    List<CompetitionEntity> findCompetitionsByProperties(HashMap<String ,Object> queryMap);
    //所有竞赛情况
    List<CompetitionEntity> findAllCompetitions();
}
