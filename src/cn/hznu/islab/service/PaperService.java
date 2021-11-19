package cn.hznu.islab.service;

import cn.hznu.islab.entity.PaperEntity;

import java.util.HashMap;
import java.util.List;

public interface PaperService {
    //添加论文
    void addPaper(PaperEntity paper);
    //更新论文
    void updatePaper(PaperEntity paperEntity);
    //删除论文
    void deletePaper(PaperEntity paperEntity);
    //通过某些字段查找一个或多个论文
    List<PaperEntity> findPapersByProperties(HashMap<String, Object> queryMap);
    //所有论文
    List<PaperEntity> findAllPapers();
}
