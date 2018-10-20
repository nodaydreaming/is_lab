package cn.hznu.islab.service;

import cn.hznu.islab.entity.TeacherEntity;

import java.util.HashMap;
import java.util.List;

public interface TeacherService {
    //添加指导老师
    void addUser(TeacherEntity teacherEntity);
    //更新指导老师
    void updateUser(TeacherEntity teacherEntity);
    //删除指导老师
    void deleteUser(TeacherEntity teacherEntity);
    //通过某些字段查找特定指导老师
    TeacherEntity findUserByProperties(HashMap<String ,String> queryMap);
    //所有指导老师
    List<TeacherEntity> findAllUsers();
}
