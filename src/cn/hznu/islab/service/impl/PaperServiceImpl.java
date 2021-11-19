package cn.hznu.islab.service.impl;

import cn.hznu.islab.dao.PaperDao;
import cn.hznu.islab.entity.PaperEntity;
import cn.hznu.islab.service.PaperService;

import java.util.HashMap;
import java.util.List;

public class PaperServiceImpl implements PaperService {
    private PaperDao paperDao;

    public void setPaperDao(PaperDao paperDao) {
        this.paperDao = paperDao;
    }

    @Override
    public void addPaper(PaperEntity paper) {
        paperDao.addPaper(paper);
    }

    @Override
    public void updatePaper(PaperEntity paperEntity) {
        paperDao.updatePaper(paperEntity);
    }

    @Override
    public void deletePaper(PaperEntity paperEntity) {
        paperDao.deletePaper(paperEntity);
    }

    @Override
    public List<PaperEntity> findPapersByProperties(HashMap<String, Object> queryMap) {
        return paperDao.findPapersByProperties(queryMap);
    }

    @Override
    public List<PaperEntity> findAllPapers() {
        return paperDao.findAllPapers();
    }
}
