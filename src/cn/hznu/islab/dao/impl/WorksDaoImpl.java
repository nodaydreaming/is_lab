package cn.hznu.islab.dao.impl;

import cn.hznu.islab.dao.WorksDao;
import cn.hznu.islab.entity.WorksEntity;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Transactional
public class WorksDaoImpl extends HibernateDaoSupport implements WorksDao {
    @Override
    public void addWorks(WorksEntity worksEntity) {
        this.getHibernateTemplate().save(worksEntity);
    }

    @Override
    public void deleteWorks(WorksEntity worksEntity) {
        this.getHibernateTemplate().delete(worksEntity);
    }

    @Override
    public void updateWorks(WorksEntity worksEntity) {
        this.getHibernateTemplate().update(worksEntity);
    }

    @Override
    public WorksEntity findWorksById(int id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(WorksEntity.class);
        criteria.add(Restrictions.eq("worksId", id));

        List<WorksEntity> list = (List<WorksEntity>) this.getHibernateTemplate().findByCriteria(criteria);

        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<WorksEntity> findAllWorks() {
        Session session = this.currentSession();
        Query query = session.createQuery("from WorksEntity order by worksId desc ");

        List<WorksEntity> list = query.list();

        if(!list.isEmpty()){
            return list;
        }
        return null;
    }

    @Override
    public List<WorksEntity> findWorksByProperties(HashMap<String, Object> queryMap) {
        DetachedCriteria criteria = DetachedCriteria.forClass(WorksEntity.class);
        for(String key : queryMap.keySet()){
            String property = key;
            Object value = queryMap.get(key);
            criteria.add(Restrictions.eq(property, value));
        }
        criteria.addOrder(Order.asc("worksId"));
        List<WorksEntity> list = (List<WorksEntity>) this.getHibernateTemplate().findByCriteria(criteria);

        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
}
