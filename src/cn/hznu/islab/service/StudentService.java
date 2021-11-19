package cn.hznu.islab.service;

import cn.hznu.islab.entity.StudentEntity;

import java.util.HashMap;
import java.util.List;

public interface StudentService {
    //添加学生
    void addStudent(StudentEntity studentEntity);
    //更新学生
    void updateStudent(StudentEntity studentEntity);
    //删除学生
    void deleteStudent(StudentEntity studentEntity);
    //通过某些字段查找一个或多个学生
    List<StudentEntity> findStudentsByProperties(HashMap<String ,Object> queryMap);
    //所有学生
    List<StudentEntity> findAllStudents();
}
