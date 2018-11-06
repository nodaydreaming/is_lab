package cn.hznu.islab.service;

import cn.hznu.islab.entity.NewsEntity;

import java.util.HashMap;
import java.util.List;

public interface NewsService {
    //添加新闻
    void addNews(NewsEntity news);
    //更新新闻
    void updateNews(NewsEntity newsEntity);
    //删除新闻
    void deleteNews(NewsEntity newsEntity);
    //通过某些字段查找一个或多个新闻
    List<NewsEntity> findNewsByProperties(HashMap<String ,String> queryMap);
    //所有新闻
    List<NewsEntity> findAllNews();
}
