package cn.hznu.islab.dao;

import cn.hznu.islab.entity.PaperEntity;

import java.util.HashMap;
import java.util.List;

public interface PaperDao {
    //添加论文
    void addPaper(PaperEntity paperEntity);
    //删除论文
    void deletePaper(PaperEntity paperEntity);
    //更新论文
    void updatePaper(PaperEntity paperEntity);
    //根据id查找论文
    PaperEntity findPaperById(int id);
    //返回所有论文
    List<PaperEntity> findAllPapers();
    //返回根据一个或多个字段查找的某些论文
    List<PaperEntity> findPapersByProperties(HashMap<String ,Object> queryMap);
}
