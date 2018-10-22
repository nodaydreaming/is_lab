package cn.hznu.islab.dao.impl;

import cn.hznu.islab.dao.NoticeDao;
import cn.hznu.islab.entity.NoticeEntity;
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
 * @ClassName NoticeServiceImpl
 * @Description notice类dao层方法实现
 * @Author GYJ
 * @Date 2018/10/16 18:13
 * @Version 1.0
 **/
public class NoticeDaoImpl extends HibernateDaoSupport implements NoticeDao {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //添加通知
    @Override
    public void addNotice(NoticeEntity noticeEntity) {
        this.getHibernateTemplate().save(noticeEntity);
    }
    //删除通知
    @Override
    public void deleteNotice(NoticeEntity noticeEntity) {
        this.getHibernateTemplate().delete(noticeEntity);
    }
    //更新通知
    @Override
    public void updateNotice(NoticeEntity noticeEntity) {
        this.getHibernateTemplate().update(noticeEntity);
    }
    //通过id查找通知
    @Override
    public NoticeEntity findNoticeById(int id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(NoticeEntity.class);
        criteria.add(Restrictions.eq("id",id));
        List<NoticeEntity> list = (List<NoticeEntity>)this.getHibernateTemplate().findByCriteria(criteria);
        //判断返回结果
        if(!list.isEmpty()){
            System.out.println(list.get(0));
            return list.get(0);
        }
        return null;
    }
    //返回所有通知
    @Override
    public  List<NoticeEntity> findAllNotices(){
        Session session = this.currentSession();
        Query query = session.createQuery("from NoticeEntity order by date desc");

        List<NoticeEntity> list = query.list();
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
    //返回根据一个或多个字段查找一个或多个通知
    @Override
    public List<NoticeEntity> findNoticesByProperties(HashMap<String, String> queryMap) {
        DetachedCriteria criteria = DetachedCriteria.forClass(NoticeEntity.class);
        //设置查找条件字段
        for(String key : queryMap.keySet()){
            String property = key;
            String value = queryMap.get(key);
            criteria.add(Restrictions.eq(property,value));
        }
        List<NoticeEntity> list = (List<NoticeEntity>)this.getHibernateTemplate().findByCriteria(criteria);
        //查找返回结果判断
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
    //返回某个时间段内的通知
    @Override
    public List<NoticeEntity> findNoticesByDate(Date starttime , Date endtime){
        Session session = this.currentSession();
        String hql = "from NoticeEntity where date >= '" + sdf.format(starttime)
                + "' and date <= '" + sdf.format(endtime) + "' order by date desc ";
        Query query = session.createQuery(hql);
        List<NoticeEntity> list = query.list();
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
}
