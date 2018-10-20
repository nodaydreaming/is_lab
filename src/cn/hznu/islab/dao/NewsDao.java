package cn.hznu.islab.dao;

import cn.hznu.islab.entity.NewsEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface NewsDao {
    //添加新闻
    void addNews(NewsEntity newsEntity);
    //删除新闻
    void deleteNews(NewsEntity newsEntity);
    //更新新闻
    void updateNews(NewsEntity newsEntity);
    //根据id查找新闻
    NewsEntity findNewsById(int id);
    //返回所有新闻
    List<NewsEntity> findAllNews();
    //返回根据一个或多个字段查找一个或多个新闻
    List<NewsEntity> findNewsByProperties(HashMap<String ,String> queryMap);
    //返回某个时间段内的新闻
    List<NewsEntity> findNewsByDate(Date starttime , Date endtime);
}
