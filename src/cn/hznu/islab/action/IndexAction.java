package cn.hznu.islab.action;

import cn.hznu.islab.entity.UserEntity;
import cn.hznu.islab.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;

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
    private String result;

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String execute() throws Exception {
        HashMap<String,List> map = new HashMap<>();

        List<UserEntity> userEntityList = userService.findAllUsers();
        List<String> stringList = new ArrayList<>();
        stringList.add("aaa");
        stringList.add("bbb");
        stringList.add("ccc");
        map.put("user",userEntityList);
        map.put("abc",stringList);

        JSONArray jsonArray = JSONArray.fromObject(map);
        result = jsonArray.toString();

        return SUCCESS;
    }
}
