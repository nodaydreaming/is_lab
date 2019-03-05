package cn.hznu.islab.dao.impl;

import cn.hznu.islab.dao.SoftwareDao;
import cn.hznu.islab.entity.SoftwareEntity;
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
public class SoftwareDaoImpl extends HibernateDaoSupport implements SoftwareDao {
    @Override
    public void addSoftware(SoftwareEntity softwareEntity) {
        this.getHibernateTemplate().save(softwareEntity);
    }

    @Override
    public void deleteSoftware(SoftwareEntity softwareEntity) {
        this.getHibernateTemplate().delete(softwareEntity);
    }

    @Override
    public void updateSoftware(SoftwareEntity softwareEntity) {
        this.getHibernateTemplate().update(softwareEntity);
    }

    @Override
    public SoftwareEntity findSoftwareById(int id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SoftwareEntity.class);
        criteria.add(Restrictions.eq("softId", id));
        List<SoftwareEntity> list = (List<SoftwareEntity>) this.getHibernateTemplate().findByCriteria(criteria);

        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<SoftwareEntity> findAllSoftwares() {
        Session session = this.currentSession();
        Query query =session.createQuery("from SoftwareEntity order by date desc ");
        List<SoftwareEntity> list = query.list();
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }

    @Override
    public List<SoftwareEntity> findSoftwaresByProperties(HashMap<String, Object> queryMap) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SoftwareEntity.class);
        for(String property : queryMap.keySet()){
            Object value = queryMap.get(property);
            criteria.add(Restrictions.eq(property, value));
        }
        criteria.addOrder(Order.asc("softId"));
        List<SoftwareEntity> list = (List<SoftwareEntity>) this.getHibernateTemplate().findByCriteria(criteria);
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
}
