package cn.hznu.islab.dao.impl;

import cn.hznu.islab.dao.StudentDao;
import cn.hznu.islab.entity.StudentEntity;
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
 * @ClassName StudentServiceImpl
 * @Description student类的dao层方法实现
 * @Author GYJ
 * @Date 2018/10/15 23:36
 * @Version 1.0
 **/
@Transactional
public class StudentDaoImpl extends HibernateDaoSupport implements StudentDao {
    //添加学生
    @Override
    public void addStudent(StudentEntity studentEntity) {
        this.getHibernateTemplate().save(studentEntity);
    }
    //删除学生
    @Override
    public void deleteStudent(StudentEntity studentEntity) {
        this.getHibernateTemplate().delete(studentEntity);
    }
    //更新学生
    @Override
    public void updateStudent(StudentEntity studentEntity){
        this.getHibernateTemplate().update(studentEntity);
    }
    //根据id查找学生
    @Override
    public StudentEntity findStudentById(int id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(StudentEntity.class);
        criteria.add(Restrictions.eq("studentId",id));
        List<StudentEntity> list = (List<StudentEntity>) this.getHibernateTemplate().findByCriteria(criteria);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
    //返回所有学生
    @Override
    public List<StudentEntity> findAllStudents() {
        Session session = this.currentSession();
        Query query = session.createQuery("from StudentEntity where type != -1 order by studentId");
        List<StudentEntity> list = query.list();
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
    //根据一个或多个字段查找某些学生
    @Override
    public List<StudentEntity> findStudentsByProperties(HashMap<String, Object> queryMap) {
        DetachedCriteria criteria = DetachedCriteria.forClass(StudentEntity.class);
        //设置查找条件字段
        for (String key : queryMap.keySet()) {
            String property = key;
            Object value = queryMap.get(key);
            criteria.add(Restrictions.eq(property, value));
        }
        criteria.addOrder(Order.asc("studentId"));
        List<StudentEntity> list = (List<StudentEntity>) this.getHibernateTemplate().findByCriteria(criteria);
        //查找结果判断
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }
}
