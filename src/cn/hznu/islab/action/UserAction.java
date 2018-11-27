package cn.hznu.islab.action;

import cn.hznu.islab.entity.UserEntity;
import cn.hznu.islab.service.UserService;
import cn.hznu.islab.util.PwdEnCoder;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.bytebuddy.dynamic.scaffold.TypeInitializer;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName UserAction
 * @Description 普通管理员 ： 登陆、修改个人信息、修改密码
 *               超级管理员 ： 登陆、修改个人信息、修改密码、添加普通管理员、删除普通管理员
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
     * 获得目前登陆的用户信息
     */
    public String getLoginUser() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        HashMap<String,Object> map = new HashMap<>();
        Gson gson = new Gson();
        String result = "";

        if(ActionContext.getContext().getSession().get("loginUser") != null){
            UserEntity loginUser = (UserEntity) ActionContext.getContext().getSession().get("loginUser");

            map.put("loginUser", loginUser);
            result = gson.toJson(map);
        }

        writer.print(result);
        writer.flush();
        writer.close();

        return NONE;
    }

    /**
     * 根据账号(用户名或者手机号)和密码进行登陆
     * @return
     */
    public String login() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        HashMap<String,String> queryMap = new HashMap<>();
        HashMap<String,Object> map = new HashMap<>();
        Gson gson = new Gson();

        String hashPwd;
        String salt;
        String result;

        user.setTelephone(user.getUsername());

//        System.out.println("用户输入账号: " + user.getUsername());
//        System.out.println("用户输入密码: " + user.getPassword());
        //假设账号是用户名
        queryMap.put("username",user.getUsername());

        List<UserEntity> list = userService.findUsersByProperties(queryMap);
        if(list == null){
            queryMap.clear();
            //假设账号是手机号
            queryMap.put("telephone",user.getTelephone());
            list = userService.findUsersByProperties(queryMap);
        }
        //账号存在，判断密码是否正确
        if(list != null){
            UserEntity userEntity = list.get(0);
            //输出用户
//            System.out.println("用户输入账号对应的用户信息" + userEntity);
            /**
             * 把用户手机号的前8为作为“盐”对口令进行加密
             * 默认“盐”为“12345678”
             */
            salt = "12345678";
            if(userEntity.getTelephone() != null) {
                salt = userEntity.getTelephone().substring(0,8);
            }
            hashPwd = new PwdEnCoder().enCoder(user.getPassword(), salt);
//            System.out.println("用户输入密码的hash值：" + hashPwd);
//            System.out.println("账号对应密码的hash值：" + userEntity.getPassword());
            if(hashPwd.equals(userEntity.getPassword())){
                //密码正确，将用户储存在session中
                ActionContext.getContext().getSession().put("loginUser",userEntity);
                map.put("nextPage","admin/main.html");
                result = gson.toJson(map);
            }else{
                String error = "密码错误，请重新输入！";
//                System.out.println(error);
                map.put("message",error);
                result = gson.toJson(map);
            }
        }
        //账号不存在
        else{
            String error = "账号不存在！";
//            System.out.println(error);
            map.put("message",error);
            result = gson.toJson(map);
        }
        writer.print(result);
        writer.flush();
        writer.close();
        return NONE;
    }

    /**
     * 修改管理员信息（不包括登陆密码的修改）
     * @return
     * @throws IOException
     */
    public String updateUser() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        HashMap<String, String> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();
        Gson gson = new Gson();

        queryMap.put("telephone", user.getTelephone());
        //通过不可修改属性“手机号”查找到要修改的管理员对象
        List<UserEntity> list = userService.findUsersByProperties(queryMap);

        if(list != null){
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
            if(list != null){
                UserEntity userEntity1 = list.get(0);
                //判断可修改属性是否相等
                if(user.getUsername().equals(userEntity1.getUsername()) &&
                   user.getEmail().equals(userEntity1.getEmail()) &&
                   user.getGender().equals(userEntity1.getGender()) &&
                   user.getNickname().equals(userEntity1.getNickname()) &&
                   user.getPhoto().equals(userEntity1.getPhoto())){

                    map.put("message", "更新管理员信息成功！");
                }else{
                    map.put("message", "更新管理员信息失败！");
                }
            }
        }
        if(!map.keySet().contains("message")){
            map.put("message", "更新管理员信息失败！");
        }

        String result = gson.toJson(map);

        writer.print(result);
        writer.flush();
        writer.close();

        return NONE;
    }

    /**
     * 管理员修改密码
     * 需要提供原密码和新密码
     */
    public String updatePassword() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        HashMap<String, String> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();
        Gson gson = new Gson();

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
            } else {
                map.put("message", "原密码输入错误！");
            }
        }else{
            map.put("message", "请先登陆！");
        }

        String result = gson.toJson(map);

        writer.print(result);
        writer.flush();
        writer.close();

        return NONE;
    }

    /**
     * 获得所有用户对象
     * @return
     * @throws IOException
     */
    public String getAllUsers() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        HashMap<String, Object> map = new HashMap<>();
        Gson gson = new Gson();


        List<UserEntity> list = userService.findAllUsers();
        if(list != null){
            map.put("users",list);
        }
        String result = gson.toJson(map);

        writer.print(result);
        writer.flush();
        writer.close();

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
        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        HashMap<String, String> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();
        Gson gson = new Gson();
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
                    } else {
                        map.put("message", "添加管理员失败！");
                    }
                }else{
                    map.put("message", "该手机号已存在！");
                }
            } else{
                map.put("message", "您没有权限删除管理员！");
            }
        } else{
            map.put("message", "请先登录！");
        }

        String result = gson.toJson(map);

        writer.print(result);
        writer.flush();
        writer.close();
        return NONE;
    }

    /**
     * 删除管理员
     * 通过不可修改信息：手机号 找到要删除的管理员对象
     */
    public String deleteUser() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        HashMap<String, String> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();
        Gson gson = new Gson();

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
                    }else{
                        map.put("message", "管理员删除失败！");
                    }
                }else {
                    map.put("message", "该管理员不存在！");
                }
            } else{
                map.put("message", "您没有权限删除管理员！");
            }
        } else{
                map.put("message", "请先登录！");
        }
        String result = gson.toJson(map);

        writer.print(result);
        writer.flush();
        writer.close();

        return NONE;
    }

}
