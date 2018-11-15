package cn.hznu.islab.action;

import cn.hznu.islab.entity.UserEntity;
import cn.hznu.islab.service.UserService;
import cn.hznu.islab.util.PwdEnCoder;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName LoginAction
 * @Description
 * @Author GYJ
 * @Date 2018/11/8 15:34
 * @Version 1.0
 **/
public class LoginAction extends ActionSupport implements ModelDriven<UserEntity> {
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
        UserEntity userEntity;
        List<UserEntity> list;
        String hashPwd;
        String salt;

        user.setTelephone(user.getUsername());

        System.out.println("用户输入账号: " + user.getUsername());
        System.out.println("用户输入密码: " + user.getPassword());
        //假设账号是用户名
        HashMap<String,String> queryMap = new HashMap<>();
        queryMap.put("username",user.getUsername());

        list = userService.findUsersByProperties(queryMap);
        if(list == null){
            queryMap.clear();
            //假设账号是手机号
            queryMap.put("telephone",user.getTelephone());
            list = userService.findUsersByProperties(queryMap);
        }

        //账号存在，判断密码是否正确
        if(list != null){
            userEntity = list.get(0);
            //输出用户
            System.out.println("用户输入账号对应的用户信息" + userEntity);
            /**
             * 把用户手机号的前8为作为“盐”对口令进行加密
             * 默认“盐”为“12345678”
             */
            salt = "12345678";
            if(userEntity.getTelephone() != null) {
                salt = userEntity.getTelephone().substring(0,8);
            }
            hashPwd = new PwdEnCoder().enCoder(user.getPassword(), salt);

            System.out.println("用户输入密码的hash值：" + hashPwd);
            System.out.println("账号对应密码的hash值：" + userEntity.getPassword());

            if(hashPwd.equals(userEntity.getPassword())){
                //密码正确，将用户储存在session中
                ActionContext.getContext().getSession().put("user",userEntity);

                Gson gson = new Gson();
                HashMap<String,String> map = new HashMap<>();
                map.put("newpage","main.html");
                String result = gson.toJson(map);

                writer.print(result);
                writer.flush();
                writer.close();

                return NONE;
            }else{
                String error = "密码错误，请重新输入！";
                System.out.println(error);
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
            System.out.println(error);
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
}
