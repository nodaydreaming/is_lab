package cn.hznu.islab.service;

import cn.hznu.islab.entity.PatentEntity;

import java.util.HashMap;
import java.util.List;

public interface PatentService {
    //添加专利
    void addPatent(PatentEntity patent);
    //更新专利
    void updatePatent(PatentEntity patentEntity);
    //删除专利
    void deletePatent(PatentEntity patentEntity);
    //通过某些字段查找一个或多个专利
    List<PatentEntity> findPatentsByProperties(HashMap<String ,Object> queryMap);
    //所有专利
    List<PatentEntity> findAllPatents();
}
