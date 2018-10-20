package cn.hznu.islab.dao;

import cn.hznu.islab.entity.StudentEntity;

import java.util.HashMap;
import java.util.List;

public interface StudentDao {
    //添加学生
    void addStudent(StudentEntity studentEntity);
    //删除学生
    void deleteStudent(StudentEntity studentEntity);
    //更新学生
    void updateStudent(StudentEntity studentEntity);
    //根据id查找学生
    StudentEntity findStudentById(int id);
    //根据一个或多个字段查找特定学生
    StudentEntity findStudentByProperties(HashMap<String ,String> queryMap);
    //返回所有学生
    List<StudentEntity> findAllStudents();
    //返回根据一个或多个字段查找的某些学生
    List<StudentEntity> findStudentsByProperties(HashMap<String ,String> queryMap);
}
