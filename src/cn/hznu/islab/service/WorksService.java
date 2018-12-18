package cn.hznu.islab.service;

import cn.hznu.islab.entity.WorksEntity;

import java.util.HashMap;
import java.util.List;

public interface WorksService {
    //添加作品
    void addWorks(WorksEntity works);
    //更新作品
    void updateWorks(WorksEntity worksEntity);
    //删除作品
    void deleteWorks(WorksEntity worksEntity);
    //通过某些字段查找一个或多个作品
    List<WorksEntity> findWorksByProperties(HashMap<String ,Object> queryMap);
    //所有作品
    List<WorksEntity> findAllWorks();
}
