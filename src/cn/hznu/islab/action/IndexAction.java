package cn.hznu.islab.action;

import cn.hznu.islab.entity.UserEntity;
import cn.hznu.islab.service.UserService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName IndexAction
 * @Description
 * @Author GYJ
 * @Date 2018/10/31 18:17
 * @Version 1.0
 **/
public class IndexAction extends ActionSupport {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute() throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        Gson gson = new Gson();
        String result;

        HashMap<String,List> map = new HashMap<>();
//        System.out.println("查询所有用户。。。");
        List<UserEntity> userEntityList = userService.findAllUsers();
        List<String> stringList = new ArrayList<>();
        stringList.add("aaa");
        stringList.add("bbb");
        stringList.add("ccc");
        map.put("user",userEntityList);
        map.put("abc",stringList);

        result = gson.toJson(map);

        writer.print(result);
        writer.flush();
        writer.close();

        return NONE;
    }
}
