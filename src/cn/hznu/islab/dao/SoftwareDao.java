package cn.hznu.islab.dao;

import cn.hznu.islab.entity.SoftwareEntity;

import java.util.HashMap;
import java.util.List;

public interface SoftwareDao {
    //添加软著
    void addSoftware(SoftwareEntity softwareEntity);
    //删除软著
    void deleteSoftware(SoftwareEntity softwareEntity);
    //更新软著
    void updateSoftware(SoftwareEntity softwareEntity);
    //根据id查找软著
    SoftwareEntity findSoftwareById(int id);
    //返回所有软著
    List<SoftwareEntity> findAllSoftwares();
    //返回根据一个或多个字段查找的某些软著
    List<SoftwareEntity> findSoftwaresByProperties(HashMap<String ,Object> queryMap);
}
