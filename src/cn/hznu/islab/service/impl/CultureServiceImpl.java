package cn.hznu.islab.service.impl;

import cn.hznu.islab.dao.CultureDao;
import cn.hznu.islab.entity.CultureEntity;
import cn.hznu.islab.service.CultureService;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName CultureServiceImpl
 * @Description
 * @Author GYJ
 * @Date 2018/11/6 18:04
 * @Version 1.0
 **/
public class CultureServiceImpl implements CultureService {
    private CultureDao cultureDao;

    public void setCultureDao(CultureDao cultureDao) {
        this.cultureDao = cultureDao;
    }

    @Override
    public void addCulture(CultureEntity cultureEntity) {
        cultureDao.addCulture(cultureEntity);
    }

    @Override
    public void updateCulture(CultureEntity cultureEntity) {
        cultureDao.updateCulture(cultureEntity);
    }

    @Override
    public void deleteCulture(CultureEntity cultureEntity) {
        cultureDao.deleteCulture(cultureEntity);
    }

    @Override
    public List<CultureEntity> findCulturesByProperties(HashMap<String, Object> queryMap) {
        return cultureDao.findCulturesByProperties(queryMap);
    }

    @Override
    public List<CultureEntity> findAllCultures() {
        return cultureDao.findAllCultures();
    }
}
