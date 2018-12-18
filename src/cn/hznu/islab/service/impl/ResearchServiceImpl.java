package cn.hznu.islab.service.impl;

import cn.hznu.islab.dao.ResearchDao;
import cn.hznu.islab.entity.ResearchEntity;
import cn.hznu.islab.service.ResearchService;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName ResearchServiceImpl
 * @Description
 * @Author GYJ
 * @Date 2018/11/6 18:10
 * @Version 1.0
 **/
public class ResearchServiceImpl implements ResearchService {
    private ResearchDao researchDao;

    public void setResearchDao(ResearchDao researchDao) {
        this.researchDao = researchDao;
    }

    @Override
    public void addResearch(ResearchEntity researchEntity) {
        researchDao.addResearch(researchEntity);
    }

    @Override
    public void updateResearch(ResearchEntity researchEntity) {
        researchDao.updateResearch(researchEntity);
    }

    @Override
    public void deleteResearch(ResearchEntity researchEntity) {
        researchDao.deleteResearch(researchEntity);
    }

    @Override
    public List<ResearchEntity> findResearchsByProperties(HashMap<String, Object> queryMap) {
        return researchDao.findResearchsByProperties(queryMap);
    }

    @Override
    public List<ResearchEntity> findAllResearchs() {
        return researchDao.findAllResearchs();
    }
}
