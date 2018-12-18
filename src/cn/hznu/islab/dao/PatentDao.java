package cn.hznu.islab.dao;

import cn.hznu.islab.entity.PatentEntity;

import java.util.HashMap;
import java.util.List;

public interface PatentDao {
    //添加专利
    void addPatent(PatentEntity patentEntity);
    //删除专利
    void deletePatent(PatentEntity patentEntity);
    //更新专利
    void updatePatent(PatentEntity patentEntity);
    //根据id查找专利
    PatentEntity findPatentById(int id);
    //返回所有专利
    List<PatentEntity> findAllPatents();
    //返回根据一个或多个字段查找的某些专利
    List<PatentEntity> findPatentsByProperties(HashMap<String ,Object> queryMap);
}
