package cn.hznu.islab.service;

import cn.hznu.islab.entity.CultureEntity;

import java.util.HashMap;
import java.util.List;

public interface CultureService {
    //添加实验室文化
    void addCulture(CultureEntity cultureEntity);
    //更新实验室文化
    void updateCulture(CultureEntity cultureEntity);
    //删除实验室文化
    void deleteCulture(CultureEntity cultureEntity);
    //通过某些字段查找一个或多个实验室文化
    List<CultureEntity> findCulturesByProperties(HashMap<String ,Object> queryMap);
    //所有实验室文化
    List<CultureEntity> findAllCultures();
}
