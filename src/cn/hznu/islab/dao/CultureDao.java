package cn.hznu.islab.dao;

import cn.hznu.islab.entity.CultureEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface CultureDao {
    //添加实验室文化
    void addCulture(CultureEntity cultureEntity);
    //删除实验室文化
    void deleteCulture(CultureEntity cultureEntity);
    //更新实验室文化
    void updateCulture(CultureEntity cultureEntity);
    //根据id查找实验室文化
    CultureEntity findCultureById(int id);
    //返回所有实验室文化
    List<CultureEntity> findAllCultures();
    //返回根据一个或多个字段查找的一个或多个实验室文化
    List<CultureEntity> findCulturesByProperties(HashMap<String ,Object> queryMap);
    //返回某个时间段内的实验室文化
    List<CultureEntity> findCulturesByDate(Date starttime , Date endtime);
    //返回某个类型的实验室文化
    List<CultureEntity> findCulturesByTypes(int type);
}
