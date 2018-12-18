package cn.hznu.islab.service.impl;

import cn.hznu.islab.dao.CompetitionDao;
import cn.hznu.islab.entity.CompetitionEntity;
import cn.hznu.islab.service.CompetitionService;

import java.util.HashMap;
import java.util.List;

public class CompetitionServiceImpl implements CompetitionService {
    private CompetitionDao competitionDao;

    public void setCompetitionDao(CompetitionDao competitionDao) {
        this.competitionDao = competitionDao;
    }

    @Override
    public void addCompetition(CompetitionEntity competition) {
        competitionDao.addCompetition(competition);
    }

    @Override
    public void updateCompetition(CompetitionEntity competitionEntity) {
        competitionDao.updateCompetition(competitionEntity);
    }

    @Override
    public void deleteCompetition(CompetitionEntity competitionEntity) {
        competitionDao.deleteCompetition(competitionEntity);
    }

    @Override
    public List<CompetitionEntity> findCompetitionsByProperties(HashMap<String, Object> queryMap) {
        return competitionDao.findCompetitionsByProperties(queryMap);
    }

    @Override
    public List<CompetitionEntity> findAllCompetitions() {
        return competitionDao.findAllCompetitions();
    }
}
