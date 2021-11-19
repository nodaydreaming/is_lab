package cn.hznu.islab.dao.impl;

import cn.hznu.islab.dao.CompetitionDao;
import cn.hznu.islab.entity.CompetitionEntity;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Transactional
public class CompetitionDaoImpl extends HibernateDaoSupport implements CompetitionDao {
    @Override
    public void addCompetition(CompetitionEntity competitionEntity) {
        this.getHibernateTemplate().save(competitionEntity);
    }

    @Override
    public void deleteCompetition(CompetitionEntity competitionEntity) {
        this.getHibernateTemplate().delete(competitionEntity);
    }

    @Override
    public void updateCompetition(CompetitionEntity competitionEntity) {
        this.getHibernateTemplate().update(competitionEntity);
    }

    @Override
    public CompetitionEntity findCompetitionById(int id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(CompetitionEntity.class);
        criteria.add(Restrictions.eq("competitionId", id));

        List<CompetitionEntity> list = (List<CompetitionEntity>) this.getHibernateTemplate().findByCriteria(criteria);

        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<CompetitionEntity> findAllCompetitions() {
        Session session = this.currentSession();
        Query query = session.createQuery("from CompetitionEntity order by date desc");

        List<CompetitionEntity> list = query.list();
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }

    @Override
    public List<CompetitionEntity> findCompetitionsByProperties(HashMap<String, Object> queryMap) {
        DetachedCriteria criteria = DetachedCriteria.forClass(CompetitionEntity.class);

        for(String key : queryMap.keySet()){
            String property = key;
            Object value = queryMap.get(key);
            criteria.add(Restrictions.eq(property, value));
        }
        List<CompetitionEntity> list = (List<CompetitionEntity>) this.getHibernateTemplate().findByCriteria(criteria);

        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
}
