package cn.hznu.islab.dao.impl;

import cn.hznu.islab.dao.NewsDao;
import cn.hznu.islab.entity.NewsEntity;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName NewsDaoImpl
 * @Description
 * @Author GYJ
 * @Date 2018/10/20 14:17
 * @Version 1.0
 **/
public class NewsDaoImpl extends HibernateDaoSupport implements NewsDao {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //添加新闻
    @Override
    public void addNews(NewsEntity newsEntity) {
        this.getHibernateTemplate().save(newsEntity);
    }
    //删除新闻
    @Override
    public void deleteNews(NewsEntity newsEntity) {
        this.getHibernateTemplate().delete(newsEntity);
    }
    //更新新闻
    @Override
    public void updateNews(NewsEntity newsEntity) {
        this.getHibernateTemplate().update(newsEntity);
    }
    //通过id查找新闻
    @Override
    public NewsEntity findNewsById(int id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(NewsEntity.class);
        criteria.add(Restrictions.eq("id",id));
        List<NewsEntity> list = (List<NewsEntity>)this.getHibernateTemplate().findByCriteria(criteria);
        //判断返回结果
        if(!list.isEmpty()){
            System.out.println(list.get(0));
            return list.get(0);
        }
        return null;
    }
    //返回所有新闻
    @Override
    public  List<NewsEntity> findAllNews(){
        Session session = this.currentSession();
        Query query = session.createQuery("from NewsEntity order by date desc ");

        List<NewsEntity> list = query.list();
        if(list.size() > 0){
            return list;
        }
        return null;
    }
    //返回根据一个或多个字段查找一个或多个新闻
    @Override
    public List<NewsEntity> findNewsByProperties(HashMap<String, String> queryMap) {
        DetachedCriteria criteria = DetachedCriteria.forClass(NewsEntity.class);
        //设置查找条件字段
        for(String key : queryMap.keySet()){
            String property = key;
            String value = queryMap.get(key);
            criteria.add(Restrictions.eq(property,value));
        }
        List<NewsEntity> list = (List<NewsEntity>)this.getHibernateTemplate().findByCriteria(criteria);
        //查找返回结果判断
        if(list.size() > 0){
            return list;
        }
        return null;
    }
    //返回某个时间段内的新闻
    @Override
    public List<NewsEntity> findNewsByDate(Date starttime , Date endtime){
        Session session = this.currentSession();
        String hql = "from NewsEntity where date >= '" + sdf.format(starttime)
                + "' and date <= '" + sdf.format(endtime) + "'";
        Query query = session.createQuery(hql);
        List<NewsEntity> list = query.list();
        if(list.size() > 0){
            return list;
        }
        return null;
    }
}
