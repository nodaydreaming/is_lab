package cn.hznu.islab.dao;

import cn.hznu.islab.entity.WorksEntity;

import java.util.HashMap;
import java.util.List;

public interface WorksDao {
    //添加作品
    void addWorks(WorksEntity worksEntity);
    //删除作品
    void deleteWorks(WorksEntity worksEntity);
    //更新作品
    void updateWorks(WorksEntity worksEntity);
    //根据id查找作品
    WorksEntity findWorksById(int id);
    //返回所有作品
    List<WorksEntity> findAllWorks();
    //返回根据一个或多个字段查找的某些作品
    List<WorksEntity> findWorksByProperties(HashMap<String ,Object> queryMap);
}
