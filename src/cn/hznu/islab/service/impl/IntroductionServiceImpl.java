package cn.hznu.islab.service.impl;

import cn.hznu.islab.dao.IntroductionDao;
import cn.hznu.islab.entity.IntroductionEntity;
import cn.hznu.islab.service.IntroductionService;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName IntroductionServiceImpl
 * @Description
 * @Author GYJ
 * @Date 2018/11/6 18:12
 * @Version 1.0
 **/
public class IntroductionServiceImpl implements IntroductionService {
    private IntroductionDao introductionDao;

    public void setIntroductionDao(IntroductionDao introductionDao) {
        this.introductionDao = introductionDao;
    }

    @Override
    public void addIntroduction(IntroductionEntity introductionEntity) {
        introductionDao.addIntroduction(introductionEntity);
    }

    @Override
    public void updateIntroduction(IntroductionEntity introductionEntity) {
        introductionDao.updateIntroduction(introductionEntity);
    }

    @Override
    public void deleteIntroduction(IntroductionEntity introductionEntity) {
        introductionDao.deleteIntroduction(introductionEntity);
    }

    @Override
    public List<IntroductionEntity> findIntroductionsByProperties(HashMap<String, Object> queryMap) {
        return introductionDao.findIntroductionsByProperties(queryMap);
    }

    @Override
    public List<IntroductionEntity> findAllIntroductions() {
        return introductionDao.findAllIntroductions();
    }
}
