package cn.hznu.islab.dao.impl;

import cn.hznu.islab.dao.UserDao;
import cn.hznu.islab.entity.UserEntity;
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
 * @ClassName UserDaoImpl
 * @Description user类的dao层方法实现
 * @Author GYJ
 * @Date 2018/10/11 19:05
 * @Version 1.0
 **/
@Transactional
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    //添加用户
    @Override
    public void addUser(UserEntity userEntity) {
        this.getHibernateTemplate().save(userEntity);
    }
    //删除用户
    @Override
    public void deleteUser(UserEntity userEntity) {
        this.getHibernateTemplate().delete(userEntity);
    }
    //更新用户
    @Override
    public void updateUser(UserEntity userEntity) {
        this.getHibernateTemplate().update(userEntity);
    }
    //通过id查找用户
    @Override
    public UserEntity findUserById(int id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(UserEntity.class);
        criteria.add(Restrictions.eq("userId",id));
        List<UserEntity> list = (List<UserEntity>)this.getHibernateTemplate().findByCriteria(criteria);

        //判断返回结果
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
    //返回所有用户
    @Override
    public  List<UserEntity> findAllUsers(){
        Session session = this.currentSession();
        Query query = session.createQuery("from UserEntity where status = 1 order by userId desc ");

        List<UserEntity> list = query.list();
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
    //根据一个或多个字段查找某些用户
    @Override
    public List<UserEntity> findUsersByProperties(HashMap<String, Object> queryMap) {
        DetachedCriteria criteria = DetachedCriteria.forClass(UserEntity.class);
        //设置查找条件字段
        for(String key : queryMap.keySet()){
            String property = key;
            Object value = queryMap.get(key);
            criteria.add(Restrictions.eq(property,value));
        }
        criteria.addOrder(Order.asc("userId"));
        List<UserEntity> list = (List<UserEntity>) this.getHibernateTemplate().findByCriteria(criteria);
        //查询结果判断
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }

}
