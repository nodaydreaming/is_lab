package cn.hznu.islab.service.impl;

import cn.hznu.islab.dao.UserDao;
import cn.hznu.islab.entity.UserEntity;
import cn.hznu.islab.service.UserService;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author GYJ
 * @Date 2018/10/11 19:33
 * @Version 1.0
 **/
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    //添加用户
    @Override
    public void addUser(UserEntity user) {
        System.out.println("service中的addUser方法执行了......");
        userDao.addUser(user);
    }
    //更新用户
    @Override
    public void updateUser(UserEntity userEntity) {
        userDao.updateUser(userEntity);
    }
    //删除用户
    @Override
    public void deleteUser(UserEntity userEntity) {
        userDao.deleteUser(userEntity);
    }
    //根据某些字段查找特定用户
    @Override
    public List<UserEntity> findUsersByProperties(HashMap<String ,Object> queryMap) {
        return userDao.findUsersByProperties(queryMap);
    }
    //所有用户
    @Override
    public List<UserEntity> findAllUsers() {
        return userDao.findAllUsers();
    }
}
