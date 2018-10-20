package cn.hznu.islab.service.impl;

import cn.hznu.islab.dao.TeacherDao;
import cn.hznu.islab.entity.TeacherEntity;
import cn.hznu.islab.service.TeacherService;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName TeacherServiceImpl
 * @Description
 * @Author GYJ
 * @Date 2018/10/16 19:29
 * @Version 1.0
 **/
public class TeacherServiceImpl implements TeacherService {
    private TeacherDao teacherDao;

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public void addUser(TeacherEntity teacherEntity) {
        teacherDao.addTeacher(teacherEntity);
    }

    @Override
    public void updateUser(TeacherEntity teacherEntity) {
        teacherDao.updateTeacher(teacherEntity);
    }

    @Override
    public void deleteUser(TeacherEntity teacherEntity) {
        teacherDao.deleteTeacher(teacherEntity);
    }

    @Override
    public TeacherEntity findUserByProperties(HashMap<String, String> queryMap) {
        return teacherDao.findTeacherByProperties(queryMap);
    }

    @Override
    public List<TeacherEntity> findAllUsers() {
        return teacherDao.findAllTeachers();
    }
}
