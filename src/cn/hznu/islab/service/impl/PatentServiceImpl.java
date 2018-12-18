package cn.hznu.islab.service.impl;

import cn.hznu.islab.dao.PatentDao;
import cn.hznu.islab.entity.PatentEntity;
import cn.hznu.islab.service.PatentService;

import java.util.HashMap;
import java.util.List;

public class PatentServiceImpl implements PatentService {
    private PatentDao patentDao;

    public void setPatentDao(PatentDao patentDao) {
        this.patentDao = patentDao;
    }

    @Override
    public void addPatent(PatentEntity patent) {
        patentDao.addPatent(patent);
    }

    @Override
    public void updatePatent(PatentEntity patentEntity) {
        patentDao.updatePatent(patentEntity);
    }

    @Override
    public void deletePatent(PatentEntity patentEntity) {
        patentDao.deletePatent(patentEntity);
    }

    @Override
    public List<PatentEntity> findPatentsByProperties(HashMap<String, Object> queryMap) {
        return patentDao.findPatentsByProperties(queryMap);
    }

    @Override
    public List<PatentEntity> findAllPatents() {
        return patentDao.findAllPatents();
    }
}
