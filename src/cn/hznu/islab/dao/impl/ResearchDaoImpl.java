package cn.hznu.islab.dao.impl;

import cn.hznu.islab.dao.ResearchDao;
import cn.hznu.islab.entity.ResearchEntity;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName ResearchDaoImpl
 * @Description
 * @Author GYJ
 * @Date 2018/10/20 14:36
 * @Version 1.0
 **/
@Transactional
public class ResearchDaoImpl extends HibernateDaoSupport implements ResearchDao {
    //添加研究方向
    @Override
    public void addResearch(ResearchEntity researchEntity) {
        this.getHibernateTemplate().save(researchEntity);
    }
    //删除研究方向
    @Override
    public void deleteResearch(ResearchEntity researchEntity) {
        this.getHibernateTemplate().delete(researchEntity);
    }
    //更新研究方向
    @Override
    public void updateResearch(ResearchEntity researchEntity) {
        this.getHibernateTemplate().update(researchEntity);
    }
    //通过id查找研究方向
    @Override
    public ResearchEntity findResearchById(int id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(ResearchEntity.class);
        criteria.add(Restrictions.eq("researchId",id));
        List<ResearchEntity> list = (List<ResearchEntity>)this.getHibernateTemplate().findByCriteria(criteria);
        //判断返回结果
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
    //返回根据一个或多个字段查找一个或多个研究方向
    @Override
    public List<ResearchEntity> findResearchsByProperties(HashMap<String, Object> queryMap) {
        DetachedCriteria criteria = DetachedCriteria.forClass(ResearchEntity.class);
        for(String key : queryMap.keySet()){
            String property = key;
            Object value = queryMap.get(key);
            criteria.add(Restrictions.eq(property,value));
        }
        criteria.addOrder(Order.asc("researchId"));
        List<ResearchEntity> list = (List<ResearchEntity>) this.getHibernateTemplate().findByCriteria(criteria);
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
    //返回所有研究方向
    @Override
    public  List<ResearchEntity> findAllResearchs(){
        Session session = this.currentSession();
        Query query = session.createQuery("from ResearchEntity order by researchId");

        List<ResearchEntity> list = query.list();
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }

}
