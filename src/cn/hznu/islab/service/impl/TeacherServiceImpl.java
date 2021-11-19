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
    public void addTeacher(TeacherEntity teacherEntity) {
        teacherDao.addTeacher(teacherEntity);
    }

    @Override
    public void updateTeacher(TeacherEntity teacherEntity) {
        teacherDao.updateTeacher(teacherEntity);
    }

    @Override
    public void deleteTeacher(TeacherEntity teacherEntity) {
        teacherDao.deleteTeacher(teacherEntity);
    }

    @Override
    public List<TeacherEntity> findTeachersByProperties(HashMap<String, Object> queryMap) {
        return teacherDao.findTeachersByProperties(queryMap);
    }

    @Override
    public List<TeacherEntity> findAllTeachers() {
        return teacherDao.findAllTeachers();
    }
}
