package cn.hznu.islab.dao.impl;

import cn.hznu.islab.dao.LinkDao;
import cn.hznu.islab.entity.LinkEntity;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName LinkDaoImpl
 * @Description
 * @Author GYJ
 * @Date 2018/11/6 18:25
 * @Version 1.0
 **/
@Transactional
public class LinkDaoImpl extends HibernateDaoSupport implements LinkDao {

    @Override
    public void addLink(LinkEntity linkEntity) {
        this.getHibernateTemplate().save(linkEntity);
    }

    @Override
    public void deleteLink(LinkEntity linkEntity) {
        this.getHibernateTemplate().delete(linkEntity);
    }

    @Override
    public void updateLink(LinkEntity linkEntity) {
        this.getHibernateTemplate().update(linkEntity);
    }

    @Override
    public LinkEntity findLinkById(int id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(LinkEntity.class);
        criteria.add(Restrictions.eq("linkId",id));
        List<LinkEntity> list = (List<LinkEntity>)this.getHibernateTemplate().findByCriteria(criteria);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<LinkEntity> findAllLinks() {
        Session session = this.currentSession();
        Query query = session.createQuery("from LinkEntity ");
        List<LinkEntity> list = query.list();
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
}
