package cn.hznu.islab.dao.impl;

import cn.hznu.islab.dao.CultureDao;
import cn.hznu.islab.entity.CultureEntity;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName CultureDaoImpl
 * @Description
 * @Author GYJ
 * @Date 2018/10/20 13:57
 * @Version 1.0
 **/
@Transactional
public class CultureDaoImpl extends HibernateDaoSupport implements CultureDao {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //添加实验室文化
    @Override
    public void addCulture(CultureEntity cultureEntity) {
        this.getHibernateTemplate().save(cultureEntity);
    }
    //删除实验室文化
    @Override
    public void deleteCulture(CultureEntity cultureEntity) {
        this.getHibernateTemplate().delete(cultureEntity);
    }
    //更新实验室文化
    @Override
    public void updateCulture(CultureEntity cultureEntity) {
        this.getHibernateTemplate().update(cultureEntity);
    }
    //通过id查找实验室文化
    @Override
    public CultureEntity findCultureById(int id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(CultureEntity.class);
        criteria.add(Restrictions.eq("cultureId",id));
        List<CultureEntity> list = (List<CultureEntity>) this.getHibernateTemplate().findByCriteria(criteria);

        //判断返回结果
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
    //返回所有实验室文化
    @Override
    public  List<CultureEntity> findAllCultures(){
        Session session = this.currentSession();
        Query query = session.createQuery("from CultureEntity order by date desc");

        List<CultureEntity> list = query.list();
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
    //返回根据一个或多个字段查找一个或多个实验室文化
    @Override
    public List<CultureEntity> findCulturesByProperties(HashMap<String, Object> queryMap) {
        DetachedCriteria criteria = DetachedCriteria.forClass(CultureEntity.class);
        //设置查找条件字段
        for(String key : queryMap.keySet()){
            String property = key;
            Object value = queryMap.get(key);
            criteria.add(Restrictions.eq(property,value));
        }
        criteria.addOrder(Order.desc("date"));
        List<CultureEntity> list = (List<CultureEntity>)this.getHibernateTemplate().findByCriteria(criteria);
        //查找返回结果判断
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
    //返回某个时间段内的实验室文化
    @Override
    public List<CultureEntity> findCulturesByDate(Date starttime , Date endtime){
        Session session = this.currentSession();
        String hql = "from CultureEntity where date >= '" + sdf.format(starttime)
                + "' and date <= '" + sdf.format(endtime) + "' order by date desc ";
        Query query = session.createQuery(hql);
        List<CultureEntity> list = query.list();
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
    //返回该类型的实验室文化
    @Override
    public List<CultureEntity> findCulturesByTypes(int type) {
        DetachedCriteria criteria = DetachedCriteria.forClass(CultureEntity.class);
        criteria.add(Restrictions.eq("type",type));

        List<CultureEntity> list = (List<CultureEntity>) this.getHibernateTemplate().findByCriteria(criteria);

        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
}
