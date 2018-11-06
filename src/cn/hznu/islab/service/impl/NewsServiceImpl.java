package cn.hznu.islab.service.impl;

import cn.hznu.islab.dao.NewsDao;
import cn.hznu.islab.entity.NewsEntity;
import cn.hznu.islab.service.NewsService;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName NewsServiceImpl
 * @Description
 * @Author GYJ
 * @Date 2018/11/6 18:07
 * @Version 1.0
 **/
public class NewsServiceImpl implements NewsService {
    private NewsDao newsDao;

    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public void addNews(NewsEntity newsEntity) {
        newsDao.addNews(newsEntity);
    }

    @Override
    public void updateNews(NewsEntity newsEntity) {
        newsDao.updateNews(newsEntity);
    }

    @Override
    public void deleteNews(NewsEntity newsEntity) {
        newsDao.deleteNews(newsEntity);
    }

    @Override
    public List<NewsEntity> findNewsByProperties(HashMap<String, String> queryMap) {
        return newsDao.findNewsByProperties(queryMap);
    }

    @Override
    public List<NewsEntity> findAllNews() {
        return newsDao.findAllNews();
    }
}
