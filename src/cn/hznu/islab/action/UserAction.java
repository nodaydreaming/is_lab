package cn.hznu.islab.action;

import cn.hznu.islab.entity.UserEntity;
import cn.hznu.islab.service.UserService;
import cn.hznu.islab.util.PwdEnCoder;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName UserAction
 * @Description
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
     * 根据账号(用户名或者手机号)和密码进行登陆
     * @return
     */
    public String login() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        String hashPwd;
        String salt;

        user.setTelephone(user.getUsername());

//        System.out.println("用户输入账号: " + user.getUsername());
//        System.out.println("用户输入密码: " + user.getPassword());
        //假设账号是用户名
        HashMap<String,String> queryMap = new HashMap<>();
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

                Gson gson = new Gson();
                HashMap<String,String> map = new HashMap<>();
                map.put("newpage","admin/main.html");
                String result = gson.toJson(map);

                writer.print(result);
                writer.flush();
                writer.close();

                return NONE;
            }else{
                String error = "密码错误，请重新输入！";
//                System.out.println(error);
                Gson gson = new Gson();
                HashMap<String,String> map = new HashMap<>();
                map.put("error",error);
                String result = gson.toJson(map);

                writer.print(result);
                writer.flush();
                writer.close();

                return NONE;
            }
        }
        //账号不存在
        else{
            String error = "账号不存在！";
//            System.out.println(error);
            Gson gson = new Gson();
            HashMap<String,String> map = new HashMap<>();
            map.put("error",error);
            String result = gson.toJson(map);

            writer.print(result);
            writer.flush();
            writer.close();

            return NONE;
        }
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
        Gson gson = new Gson();
        HashMap<String, String> queryMap = new HashMap<>();
        HashMap<String, String> map = new HashMap<>();

        queryMap.put("telephone", user.getTelephone());

        List<UserEntity> list = userService.findUsersByProperties(queryMap);
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
        String result = gson.toJson(map);

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
        Gson gson = new Gson();
        HashMap<String, String> queryMap = new HashMap<>();
        HashMap<String, String> map = new HashMap<>();

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
        Gson gson = new Gson();
        HashMap<String, String> queryMap = new HashMap<>();
        HashMap<String, String> map = new HashMap<>();
        //从session中得到要修改密码的管理员对象
        UserEntity loginUser = (UserEntity) ActionContext.getContext().getSession().get("loginUser");
        String newPassword = request.getParameter("newPassword");
        String telephone = loginUser.getTelephone();

        String oldHashPassword = PwdEnCoder.enCoder(user.getPassword(), telephone.substring(0,8));
        //验证密码是否输入正确
        if(oldHashPassword.equals(loginUser.getPassword())){
            String newHashPassword = PwdEnCoder.enCoder(newPassword, telephone.substring(0,8));

            loginUser.setPassword(newHashPassword);
            //更改密码
            userService.updateUser(loginUser);

            ActionContext.getContext().getSession().put("loginUser", loginUser);

            map.put("message", "密码修改成功！");
        }else{
            map.put("message", "原密码输入错误！");
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

        return NONE;
    }
}
