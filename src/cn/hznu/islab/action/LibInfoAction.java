package cn.hznu.islab.action;

import cn.hznu.islab.entity.IntroductionEntity;
import cn.hznu.islab.service.IntroductionService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName LibInfoAction
 * @Description
 * @Author GYJ
 * @Date 2018/11/19 18:44
 * @Version 1.0
 **/
public class LibInfoAction extends ActionSupport implements ModelDriven<IntroductionEntity> {
    private IntroductionService introductionService;
    private IntroductionEntity introductionEntity = new IntroductionEntity();

    private HttpServletResponse response;

    public void setIntroductionService(IntroductionService introductionService) {
        this.introductionService = introductionService;
    }

    @Override
    public IntroductionEntity getModel() {
        return introductionEntity;
    }

    public String updateInfo() throws IOException {
        response = ServletActionContext.getResponse();
        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        Gson gson = new Gson();
        HashMap<String, Object> map = new HashMap<>();

        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("name",introductionEntity.getName());

        List<IntroductionEntity> list = introductionService.findIntroductionsByProperties(queryMap);

        if(list != null){
            IntroductionEntity introduction = list.get(0);
            introductionEntity.setId(introduction.getId());
            introductionService.updateIntroduction(introductionEntity);
        }
        list.clear();
        list = introductionService.findIntroductionsByProperties(queryMap);

//        System.out.println(list.get(0).getContent());
//        System.out.println(introductionEntity.getContent());
        if(list.get(0).getContent().equals(introductionEntity.getContent())){
            map.put("updateResult", "success");

            writer.print(gson.toJson(map));
            writer.flush();
            writer.close();
        }else{
            map.put("updateResult", "error");

            writer.print(gson.toJson(map));
            writer.flush();
            writer.close();
        }

        return NONE;
    }

    public String getInfoContent() throws IOException {
        response = ServletActionContext.getResponse();
        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        Gson gson = new Gson();
        HashMap<String, Object> map = new HashMap<>();

        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("name",introductionEntity.getName());
        List<IntroductionEntity> list = introductionService.findIntroductionsByProperties(queryMap);

        if(list != null){
            String content = list.get(0).getContent().toString();

            map.put("content", content);

            writer.print(gson.toJson(map));
            writer.flush();
            writer.close();

        }else{
            map.put("content", "获得content失败！");

            writer.print(gson.toJson(map));
            writer.flush();
            writer.close();
        }
        return NONE;
    }
}
