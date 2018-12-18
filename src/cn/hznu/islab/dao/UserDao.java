package cn.hznu.islab.dao;

import cn.hznu.islab.entity.UserEntity;

import java.util.HashMap;
import java.util.List;

public interface UserDao {
    //添加用户
    void addUser(UserEntity userEntity);
    //删除用户
    void deleteUser(UserEntity userEntity);
    //更新用户
    void updateUser(UserEntity userEntity);
    //根据id查找用户
    UserEntity findUserById(int id);
    //返回所有用户
    List<UserEntity> findAllUsers();
    //返回根据一个或多个字段查找的某些用户
    List<UserEntity> findUsersByProperties(HashMap<String ,Object> queryMap);
}
