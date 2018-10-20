package cn.hznu.islab.dao.impl;

import cn.hznu.islab.dao.IntroductionDao;
import cn.hznu.islab.entity.IntroductionEntity;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName IntroductionDaoImpl
 * @Description
 * @Author GYJ
 * @Date 2018/10/20 14:13
 * @Version 1.0
 **/
public class IntroductionDaoImpl extends HibernateDaoSupport implements IntroductionDao {
    //添加介绍
    @Override
    public void addIntroduction(IntroductionEntity introductionEntity) {
        this.getHibernateTemplate().save(introductionEntity);
    }
    //删除介绍
    @Override
    public void deleteIntroduction(IntroductionEntity introductionEntity) {
        this.getHibernateTemplate().delete(introductionEntity);
    }
    //更新介绍
    @Override
    public void updateIntroduction(IntroductionEntity introductionEntity) {
        this.getHibernateTemplate().update(introductionEntity);
    }
    //通过id查找介绍
    @Override
    public IntroductionEntity findIntroductionById(int id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(IntroductionEntity.class);
        criteria.add(Restrictions.eq("id",id));
        List<IntroductionEntity> list = (List<IntroductionEntity>)this.getHibernateTemplate().findByCriteria(criteria);

        //判断返回结果
        if(!list.isEmpty()){
            System.out.println(list.get(0));
            return list.get(0);
        }
        return null;
    }
    //根据一个或多个字段查找特定介绍
    @Override
    public IntroductionEntity findIntroductionByProperties(HashMap<String ,String> queryMap) {

        DetachedCriteria criteria = DetachedCriteria.forClass(IntroductionEntity.class);
        //查询的条件
        for(String key : queryMap.keySet()) {
            String property = key;
            String value = queryMap.get(key);
            criteria.add(Restrictions.eq(property, value));
        }
        List<IntroductionEntity> list = (List<IntroductionEntity>)this.getHibernateTemplate().findByCriteria(criteria);

        //判断返回结果
        if(!list.isEmpty()){
            System.out.println(list.get(0));
            return list.get(0);
        }
        return null;
    }
    //返回所有介绍
    @Override
    public  List<IntroductionEntity> findAllIntroductions(){
        Session session = this.currentSession();
        Query query = session.createQuery("from IntroductionEntity");

        List<IntroductionEntity> list = query.list();
        if(list.size() > 0){
            return list;
        }
        return null;
    }
}
