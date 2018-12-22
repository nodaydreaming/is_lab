package cn.hznu.islab.service.impl;

import cn.hznu.islab.dao.SoftwareDao;
import cn.hznu.islab.entity.SoftwareEntity;
import cn.hznu.islab.service.SoftwareService;

import java.util.HashMap;
import java.util.List;

public class SoftwareServiceImpl implements SoftwareService {
    private SoftwareDao softwareDao;

    public void setSoftwareDao(SoftwareDao softwareDao) {
        this.softwareDao = softwareDao;
    }

    @Override
    public void addSoftware(SoftwareEntity software) {
        softwareDao.addSoftware(software);
    }

    @Override
    public void updateSoftware(SoftwareEntity softwareEntity) {
        softwareDao.updateSoftware(softwareEntity);
    }

    @Override
    public void deleteSoftware(SoftwareEntity softwareEntity) {
        softwareDao.deleteSoftware(softwareEntity);
    }

    @Override
    public List<SoftwareEntity> findSoftwaresByProperties(HashMap<String, Object> queryMap) {
        return softwareDao.findSoftwaresByProperties(queryMap);
    }

    @Override
    public List<SoftwareEntity> findAllSoftwares() {
        return softwareDao.findAllSoftwares();
    }
}
