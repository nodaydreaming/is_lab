package cn.hznu.islab.action;

import cn.hznu.islab.entity.UserEntity;
import cn.hznu.islab.service.UserService;
import cn.hznu.islab.util.MapToJSON;
import cn.hznu.islab.util.PwdEnCoder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static java.sql.Types.NULL;

/**
 * @ClassName UserAction
 * @Description 普通管理员 ：登陆、修改个人信息、修改密码
 *               超级管理员 ：登陆、修改个人信息、修改密码、添加普通管理员、删除普通管理员
 * @Author GYJ
 * @Date 2018/11/8 15:34
 * @Version 1.0
 **/

public class UserAction extends ActionSupport implements ModelDriven<UserEntity> {
    private UserService userService;
    private UserEntity user = new UserEntity();

    @Override
    public UserEntity getModel() {
        return user;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    /**
     * 获得目前登陆的用户对象
     */
    public String getLoginUser() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        //存储返回前端的数据
        HashMap<String,Object> map = new HashMap<>();

        if(ActionContext.getContext().getSession().get("loginUser") != null){
            UserEntity getLoginUser = (UserEntity) ActionContext.getContext().getSession().get("loginUser");

            UserEntity loginUser = new UserEntity();
            loginUser.setPhoto(getLoginUser.getPhoto());
            loginUser.setNickname(getLoginUser.getNickname());

            map.put("loginUser", loginUser);
        }
        else {
            map.put("login", ServletActionContext.getRequest().getContextPath() + "/login.html");
        }

        MapToJSON.mapToJson(response, map);
        return NONE;
    }
    /**
     * 根据账号(用户名或者手机号)和密码进行登陆
     * @return
     */
    public String login() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        //查询时，用于存储“属性”和“属性值”的map
        HashMap<String,String> queryMap = new HashMap<>();
        //存储返回前端的数据
        HashMap<String,Object> map = new HashMap<>();
        //用户输入的密码所生产的密钥
        String hashPwd;
        //加密所用的“盐”值，有默认值
        String salt = "123456";
        user.setTelephone(user.getUsername());
        //假设账号是用户名
        queryMap.put("username",user.getUsername());
        List<UserEntity> list = userService.findUsersByProperties(queryMap);
        //查询结果判断
        if(list == null){
            queryMap.clear();
            //假设账号是手机号
            queryMap.put("telephone",user.getTelephone());
            list = userService.findUsersByProperties(queryMap);
        }
        //账号存在，判断密码是否正确
        if(list != null){
            UserEntity userEntity = list.get(0);
            //检验用户输入的密码
            if(userEntity.getTelephone() != null) {
                salt = userEntity.getTelephone().substring(0,8);
            }
            //用户输入的密码加“盐”进行哈希
            hashPwd = PwdEnCoder.enCoder(user.getPassword(), salt);
            if(hashPwd.equals(userEntity.getPassword())){
                //密码正确，将用户储存在session中
                ActionContext.getContext().getSession().put("loginUser",userEntity);
                //跳转到后台首页
                map.put("newPage", ServletActionContext.getRequest().getContextPath() + "/admin/main.html");
            }
            else{
                //密码错误
                String error = "用户名或密码错误！";
                map.put("message",error);
            }
        }
        //账号不存在
        else{
            String error = "用户名或密码错误！";
            map.put("message",error);
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }
    /**
     * 修改管理员信息（不包括登陆密码的修改）
     * @return
     * @throws IOException
     */
    public String updateUser() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        //查询时，用于存储“属性”和“属性值”的map
        HashMap<String,String> queryMap = new HashMap<>();
        //存储返回前端的数据
        HashMap<String,Object> map = new HashMap<>();

        if(ActionContext.getContext().getSession().get("loginUser") != null) {
            UserEntity loginUser = (UserEntity) ActionContext.getContext().getSession().get("loginUser");
            //权限判断
            if(loginUser.getStatus() == 2) {
                queryMap.put("telephone", user.getTelephone());
                //通过不可修改属性“手机号”查找到要修改的管理员对象
                List<UserEntity> list = userService.findUsersByProperties(queryMap);
                if (list != null) {
                    UserEntity userEntity = list.get(0);
                    //将修改后的信息赋值给该管理员对象
                    userEntity.setPhoto(user.getPhoto());
                    userEntity.setUsername(user.getUsername());
                    userEntity.setGender(user.getGender());
                    userEntity.setNickname(user.getNickname());
                    userEntity.setEmail(user.getEmail());

                    userService.updateUser(userEntity);
                    //检查更新信息是否成功
                    queryMap.clear();
                    queryMap.put("telephone", user.getTelephone());
                    list.clear();
                    list = userService.findUsersByProperties(queryMap);
                    if (list != null) {
                        UserEntity newUserEntity = list.get(0);
                        //判断可修改属性是否相等
                        if (user.getUsername().equals(newUserEntity.getUsername()) &&
                                user.getEmail().equals(newUserEntity.getEmail()) &&
                                user.getGender().equals(newUserEntity.getGender()) &&
                                user.getNickname().equals(newUserEntity.getNickname()) &&
                                user.getPhoto().equals(newUserEntity.getPhoto())) {
                            map.put("message", "更新管理员信息成功！");
                        }
                        else {
                            map.put("message", "更新管理员信息失败！");
                        }
                    }
                }
                else{
                    map.put("message", "该管理员不存在！");
                }
            }
            else{
                map.put("message", "您没有权限修改其他管理员的信息！");
            }
        }
        else{
            //跳转到登陆页面
            map.put("login", ServletActionContext.getRequest().getContextPath() + "/login.html");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }
    /**
     * 管理员修改密码
     * 需要提供原密码和新密码
     */
    public String updatePassword() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        //存储返回前端的数据
        HashMap<String,Object> map = new HashMap<>();

        if(ActionContext.getContext().getSession().get("loginUser") != null) {
            //从session中得到要修改密码的管理员对象
            UserEntity loginUser = (UserEntity) ActionContext.getContext().getSession().get("loginUser");
            String newPassword = request.getParameter("newPassword");
            String telephone = loginUser.getTelephone();

            String oldHashPassword = PwdEnCoder.enCoder(user.getPassword(), telephone.substring(0, 8));
            //验证密码是否输入正确
            if (oldHashPassword.equals(loginUser.getPassword())) {
                String newHashPassword = PwdEnCoder.enCoder(newPassword, telephone.substring(0, 8));
                loginUser.setPassword(newHashPassword);
                //更改密码
                userService.updateUser(loginUser);
                ActionContext.getContext().getSession().put("loginUser", loginUser);
                map.put("message", "密码修改成功！");
            }
            else {
                map.put("message", "原密码输入错误！");
            }
        }
        else {
            map.put("login", ServletActionContext.getRequest().getContextPath() + "/login.html");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }
    /**
     * 获得所有用户对象
     * @return
     * @throws IOException
     */
    public String getAllUsers() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        //存储返回前端的数据
        HashMap<String,Object> map = new HashMap<>();

        if(ActionContext.getContext().getSession().get("loginUser") != null){
            UserEntity loginUser = (UserEntity) ActionContext.getContext().getSession().get("loginUser");
            //权限判断
            if(loginUser.getStatus() == 2) {
                List<UserEntity> list = userService.findAllUsers();
                if (list != null) {
                    map.put("users", list);
                }
            }
            else{
                map.put("message", "您没有权限查看所有管理员信息！");
            }
        }
        else{
            map.put("login", ServletActionContext.getRequest().getContextPath() + "/login.html");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }
    /**
     * 添加新的用户，只有超级管理员可以添加用户
     * 用户的 username，password，telephone为必需信息
     * @return
     * @throws IOException
     */
    public String addUser() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        //查询时，用于存储“属性”和“属性值”的map
        HashMap<String,String> queryMap = new HashMap<>();
        //存储返回前端的数据
        HashMap<String,Object> map = new HashMap<>();

        if(ActionContext.getContext().getSession().get("loginUser") != null) {
            UserEntity loginUser = (UserEntity) ActionContext.getContext().getSession().get("loginUser");
            //超级管理员判断
            if(loginUser.getStatus() == 2){
                //判断改手机号是否已经存在
                queryMap.put("telephone", user.getTelephone());
                List<UserEntity> list = userService.findUsersByProperties(queryMap);
                //手机号不存在
                if(list == null) {
                    userService.addUser(user);
                    //检验是否添加成功
                    queryMap.clear();
                    queryMap.put("telephone", user.getTelephone());
                    queryMap.put("username", user.getUsername());

                    list.clear();
                    list = userService.findUsersByProperties(queryMap);
                    if (list != null) {
                        map.put("message", "添加管理员成功！");
                    }
                    else {
                        map.put("message", "添加管理员失败！");
                    }
                }
                else{
                    map.put("message", "该手机号已存在！");
                }
            }
            else{
                map.put("message", "您没有权限添加管理员！");
            }
        }
        else{
            //登陆
            map.put("login", ServletActionContext.getRequest().getContextPath() + "/login.html");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }
    /**
     * 删除管理员
     * 通过不可修改信息：手机号 找到要删除的管理员对象
     */
    public String deleteUser() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        //查询时，用于存储“属性”和“属性值”的map
        HashMap<String,String> queryMap = new HashMap<>();
        //存储返回前端的数据
        HashMap<String,Object> map = new HashMap<>();

        if(ActionContext.getContext().getSession().get("loginUser") != null) {
            UserEntity loginUser = (UserEntity) ActionContext.getContext().getSession().get("loginUser");
            //超级管理员才能删除普通管理员
            if(loginUser.getStatus() == 2){
                queryMap.put("telephone", user.getTelephone());

                List<UserEntity> list = userService.findUsersByProperties(queryMap);
                if(list != null){
                    UserEntity deleteUser = list.get(0);
                    userService.deleteUser(deleteUser);
                    //删除检查
                    list.clear();
                    list = userService.findUsersByProperties(queryMap);
                    if(list == null){
                        map.put("message", "管理员删除成功！");
                    }
                    else{
                        map.put("message", "管理员删除失败！");
                    }
                }
                else {
                    map.put("message", "该管理员不存在！");
                }
            }
            else{
                map.put("message", "您没有权限删除管理员！");
            }
        }
        else{
            //跳转到登录页
            map.put("login", ServletActionContext.getRequest().getContextPath() + "/login.html");
        }
        MapToJSON.mapToJson(response, map);
        System.out.println(map);
        return NONE;
    }

}
