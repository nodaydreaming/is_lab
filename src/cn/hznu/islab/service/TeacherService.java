package cn.hznu.islab.service;

import cn.hznu.islab.entity.TeacherEntity;

import java.util.HashMap;
import java.util.List;

public interface TeacherService {
    //添加指导老师
    void addTeacher(TeacherEntity teacherEntity);
    //更新指导老师
    void updateTeacher(TeacherEntity teacherEntity);
    //删除指导老师
    void deleteTeacher(TeacherEntity teacherEntity);
    //通过某些字段查找特定一个或多个老师
    List<TeacherEntity> findTeachersByProperties(HashMap<String ,Object> queryMap);
    //所有指导老师
    List<TeacherEntity> findAllTeachers();
}
