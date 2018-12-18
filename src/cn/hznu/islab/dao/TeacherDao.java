package cn.hznu.islab.dao;

import cn.hznu.islab.entity.TeacherEntity;

import java.util.HashMap;
import java.util.List;

public interface TeacherDao {
    //添加指导教师
    void addTeacher(TeacherEntity teacherEntity);
    //删除指导老师
    void deleteTeacher(TeacherEntity teacherEntity);
    //更新指导老师
    void updateTeacher(TeacherEntity teacherEntity);
    //根据id查找指导老师
    TeacherEntity findTeacherById(int id);
    //返回所有指导老师
    List<TeacherEntity> findAllTeachers();
    //返回根据一个或多个字段查找的一个或多个指导老师
    List<TeacherEntity> findTeachersByProperties(HashMap<String ,Object> queryMap);
}
