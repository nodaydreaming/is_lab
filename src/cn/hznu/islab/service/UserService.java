package cn.hznu.islab.service;

import cn.hznu.islab.entity.UserEntity;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName UserService
 * @Description
 * @Author GYJ
 * @Date 2018/10/11 19:31
 * @Version 1.0
 **/
public interface UserService {
    //添加用户
    void addUser(UserEntity user);
    //更新用户
    void updateUser(UserEntity userEntity);
    //删除用户
    void deleteUser(UserEntity userEntity);
    //通过某些字段查找一个或多个用户
    List<UserEntity> findUsersByProperties(HashMap<String, Object> queryMap);
    //所有用户
    List<UserEntity> findAllUsers();
}
