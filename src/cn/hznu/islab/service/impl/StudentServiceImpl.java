package cn.hznu.islab.service.impl;

import cn.hznu.islab.dao.StudentDao;
import cn.hznu.islab.entity.StudentEntity;
import cn.hznu.islab.service.StudentService;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName StudentServiceImpl
 * @Description
 * @Author GYJ
 * @Date 2018/10/16 19:18
 * @Version 1.0
 **/
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void addStudent(StudentEntity studentEntity) {
        studentDao.addStudent(studentEntity);
    }

    @Override
    public void updateStudent(StudentEntity studentEntity) {
        studentDao.updateStudent(studentEntity);
    }

    @Override
    public void deleteStudent(StudentEntity studentEntity) {
        studentDao.deleteStudent(studentEntity);
    }

    @Override
    public List<StudentEntity> findStudentsByProperties(HashMap<String, Object> queryMap) {
        return studentDao.findStudentsByProperties(queryMap);
    }

    @Override
    public List<StudentEntity> findAllStudents() {
        return studentDao.findAllStudents();
    }
}
