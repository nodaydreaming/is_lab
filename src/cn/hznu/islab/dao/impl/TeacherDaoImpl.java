package cn.hznu.islab.dao.impl;

import cn.hznu.islab.dao.TeacherDao;
import cn.hznu.islab.entity.TeacherEntity;
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
 * @ClassName TeacherDaoImpl
 * @Description teacher类的dao层方法实现
 * @Author GYJ
 * @Date 2018/10/15 21:04
 * @Version 1.0
 **/
@Transactional
public class TeacherDaoImpl extends HibernateDaoSupport implements TeacherDao {
    //添加指导老师
    @Override
    public void addTeacher(TeacherEntity teacherEntity) {
        this.getHibernateTemplate().save(teacherEntity);
    }
    //删除指导老师
    @Override
    public void deleteTeacher(TeacherEntity teacherEntity) {
        this.getHibernateTemplate().delete(teacherEntity);
    }
    //更新指导老师
    @Override
    public void updateTeacher(TeacherEntity teacherEntity) {
        this.getHibernateTemplate().update(teacherEntity);
    }
    //根据id查找指导老师
    @Override
    public TeacherEntity findTeacherById(int id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TeacherEntity.class);
        criteria.add(Restrictions.eq("teacherId",id));
        List<TeacherEntity> list = (List<TeacherEntity>) this.getHibernateTemplate().findByCriteria(criteria);
        //判断返回结果
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
    //返回所有指导老师
    @Override
    public List<TeacherEntity> findAllTeachers() {
        Session session = this.currentSession();
        Query query = session.createQuery("from TeacherEntity order by teacherId");
        List<TeacherEntity> list = query.list();
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
    //根据一个或多个字段查找某些指导老师
    @Override
    public List<TeacherEntity> findTeachersByProperties(HashMap<String, Object> queryMap) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TeacherEntity.class);
        //设置查找条件
        for(String key : queryMap.keySet()){
            String property = key;
            Object value = queryMap.get(key);
            criteria.add(Restrictions.eq(property,value));
        }
        criteria.addOrder(Order.asc("teacherId"));
        List<TeacherEntity> list = (List<TeacherEntity>)this.getHibernateTemplate().findByCriteria(criteria);
        //查询结果判断
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }

}
