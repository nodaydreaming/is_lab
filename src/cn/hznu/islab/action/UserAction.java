package cn.hznu.islab.action;

import cn.hznu.islab.entity.UserEntity;
import cn.hznu.islab.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName UserAction
 * @Description
 * @Author GYJ
 * @Date 2018/10/11 19:44
 * @Version 1.0
 **/
public class UserAction extends ActionSupport implements ModelDriven<UserEntity> {
    //模型驱动对象
    UserEntity userEntity = new UserEntity();

    @Override
    public UserEntity getModel() {
        return userEntity;
    }
    //注入业务层的类
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //添加用户的方法
    public String addUser(){
        userEntity.setStatus(1);
        userService.addUser(userEntity);
        //检验是否添加成功
        HashMap<String ,String> queryMap = new HashMap<>();
        queryMap.put("telephone",userEntity.getTelephone());

        if(userService.findUsersByProperties(queryMap) != null) {
            System.out.println("用户添加成功！");
            System.out.println(userService.findUsersByProperties(queryMap));

            return SUCCESS;
        }else{
            addActionError("用户添加失败，请重试！");
            System.out.println("用户添加失败，请重试！");
            return INPUT;
        }
    }

    //更新用户
    public String updateUser(){
        boolean flag = false;
        userService.updateUser(userEntity);
        //检验是否更新成功
        HashMap<String ,String> queryMap = new HashMap<>();
        queryMap.put("telephone",userEntity.getTelephone());
        List<UserEntity> list = userService.findUsersByProperties(queryMap);
        for(UserEntity u : list) {
            if(!flag) {
                flag = u.equals(userEntity);
            }
        }
        if(flag) {
            System.out.println("用户更新成功！");

            return SUCCESS;
        }else{
            addActionError("用户更新失败，请重试！");
            System.out.println("用户更新失败，请重试！");
            return INPUT;
        }
    }


}
