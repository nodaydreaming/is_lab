package cn.hznu.islab.service;

import cn.hznu.islab.entity.SoftwareEntity;

import java.util.HashMap;
import java.util.List;

public interface SoftwareService {
    //添加软著
    void addSoftware(SoftwareEntity software);
    //更新软著
    void updateSoftware(SoftwareEntity softwareEntity);
    //删除软著
    void deleteSoftware(SoftwareEntity softwareEntity);
    //通过某些字段查找一个或多个软著
    List<SoftwareEntity> findSoftwaresByProperties(HashMap<String, Object> queryMap);
    //所有软著
    List<SoftwareEntity> findAllSoftwares();
}
