package cn.hznu.islab.dao;

import cn.hznu.islab.entity.CompetitionEntity;

import java.util.HashMap;
import java.util.List;

public interface CompetitionDao {
    //添加竞赛获奖情况
    void addCompetition(CompetitionEntity competitionEntity);
    //删除竞赛获奖情况
    void deleteCompetition(CompetitionEntity competitionEntity);
    //更新竞赛获奖情况
    void updateCompetition(CompetitionEntity competitionEntity);
    //根据id查找竞赛获奖情况
    CompetitionEntity findCompetitionById(int id);
    //返回所有竞赛获奖情况
    List<CompetitionEntity> findAllCompetitions();
    //返回根据一个或多个字段查找的某些竞赛获奖情况
    List<CompetitionEntity> findCompetitionsByProperties(HashMap<String ,Object> queryMap);
}
