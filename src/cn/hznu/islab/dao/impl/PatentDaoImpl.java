package cn.hznu.islab.dao.impl;

import cn.hznu.islab.dao.PatentDao;
import cn.hznu.islab.entity.PatentEntity;
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
public class PatentDaoImpl extends HibernateDaoSupport implements PatentDao {
    @Override
    public void addPatent(PatentEntity patentEntity) {
        this.getHibernateTemplate().save(patentEntity);
    }

    @Override
    public void deletePatent(PatentEntity patentEntity) {
        this.getHibernateTemplate().delete(patentEntity);
    }

    @Override
    public void updatePatent(PatentEntity patentEntity) {
        this.getHibernateTemplate().update(patentEntity);
    }

    @Override
    public PatentEntity findPatentById(int id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(PatentEntity.class);
        criteria.add(Restrictions.eq("patentId", id));

        List<PatentEntity> list = (List<PatentEntity>) this.getHibernateTemplate().findByCriteria(criteria);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<PatentEntity> findAllPatents() {
        Session session = this.currentSession();
        Query query = session.createQuery("from PatentEntity order by date desc");

        List<PatentEntity> list = query.list();
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }

    @Override
    public List<PatentEntity> findPatentsByProperties(HashMap<String, Object> queryMap) {
        DetachedCriteria criteria = DetachedCriteria.forClass(PatentEntity.class);
        for(String key : queryMap.keySet()){
            String property = key;
            Object value = queryMap.get(key);
            criteria.add(Restrictions.eq(property, value));
        }
        criteria.addOrder(Order.asc("patentId"));
        List<PatentEntity> list = (List<PatentEntity>) this.getHibernateTemplate().findByCriteria(criteria);
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
}
