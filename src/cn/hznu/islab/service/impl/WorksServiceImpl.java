package cn.hznu.islab.service.impl;

import cn.hznu.islab.dao.WorksDao;
import cn.hznu.islab.entity.WorksEntity;
import cn.hznu.islab.service.WorksService;

import java.util.HashMap;
import java.util.List;

public class WorksServiceImpl implements WorksService {
    private WorksDao worksDao;

    public void setWorksDao(WorksDao worksDao) {
        this.worksDao = worksDao;
    }

    @Override
    public void addWorks(WorksEntity works) {
        worksDao.addWorks(works);
    }

    @Override
    public void updateWorks(WorksEntity worksEntity) {
        worksDao.updateWorks(worksEntity);
    }

    @Override
    public void deleteWorks(WorksEntity worksEntity) {
        worksDao.deleteWorks(worksEntity);
    }

    @Override
    public List<WorksEntity> findWorksByProperties(HashMap<String, Object> queryMap) {
        return worksDao.findWorksByProperties(queryMap);
    }

    @Override
    public List<WorksEntity> findAllWorks() {
        return worksDao.findAllWorks();
    }
}
