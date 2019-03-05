package cn.hznu.islab.dao.impl;

import cn.hznu.islab.dao.PaperDao;
import cn.hznu.islab.entity.PaperEntity;
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
public class PaperDaoImpl extends HibernateDaoSupport implements PaperDao {
    @Override
    public void addPaper(PaperEntity paperEntity) {
        this.getHibernateTemplate().save(paperEntity);
    }

    @Override
    public void deletePaper(PaperEntity paperEntity) {
        this.getHibernateTemplate().delete(paperEntity);
    }

    @Override
    public void updatePaper(PaperEntity paperEntity) {
        this.getHibernateTemplate().update(paperEntity);
    }

    @Override
    public PaperEntity findPaperById(int id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(PaperEntity.class);
        criteria.add(Restrictions.eq("paperId", id));
        List<PaperEntity> list = (List<PaperEntity>)this.getHibernateTemplate().findByCriteria(criteria);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<PaperEntity> findAllPapers() {
        Session session = this.currentSession();
        Query query = session.createQuery("from PaperEntity order by date desc ");

        List<PaperEntity> list = query.list();
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }

    @Override
    public List<PaperEntity> findPapersByProperties(HashMap<String, Object> queryMap) {
        DetachedCriteria criteria = DetachedCriteria.forClass(PaperEntity.class);
        for(String property : queryMap.keySet()){
            Object value = queryMap.get(property);
            criteria.add(Restrictions.eq(property, value));
        }
        criteria.addOrder(Order.asc("paperId"));
        List<PaperEntity> list = (List<PaperEntity>)this.getHibernateTemplate().findByCriteria(criteria);
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
}
