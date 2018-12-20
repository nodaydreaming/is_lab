package cn.hznu.islab.dao.impl;

import cn.hznu.islab.dao.ProjectDao;
import cn.hznu.islab.entity.ProjectEntity;
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
public class ProjectDaoImpl extends HibernateDaoSupport implements ProjectDao {
    @Override
    public void addProject(ProjectEntity projectEntity) {
        this.getHibernateTemplate().save(projectEntity);
    }

    @Override
    public void deleteProject(ProjectEntity projectEntity) {
        this.getHibernateTemplate().delete(projectEntity);
    }

    @Override
    public void updateProject(ProjectEntity projectEntity) {
        this.getHibernateTemplate().update(projectEntity);
    }

    @Override
    public ProjectEntity findProjectById(int id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(ProjectEntity.class);
        criteria.add(Restrictions.eq("projectId", id));

        List<ProjectEntity> list = (List<ProjectEntity>) this.getHibernateTemplate().findByCriteria(criteria);

        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<ProjectEntity> findAllProjects() {
        Session session = this.currentSession();
        Query query = session.createQuery("from ProjectEntity order by projectId desc ");

        List<ProjectEntity> list = query.list();
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }

    @Override
    public List<ProjectEntity> findProjectsByProperties(HashMap<String, Object> queryMap) {
        DetachedCriteria criteria = DetachedCriteria.forClass(ProjectEntity.class);
        for(String key : queryMap.keySet()){
            String property = key;
            Object value = queryMap.get(key);
            criteria.add(Restrictions.eq(property, value));
        }
        criteria.addOrder(Order.asc("projectId"));
        List<ProjectEntity> list = (List<ProjectEntity>) this.getHibernateTemplate().findByCriteria(criteria);

        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
}
