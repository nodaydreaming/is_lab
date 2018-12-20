package cn.hznu.islab.action;

import cn.hznu.islab.entity.IntroductionEntity;
import cn.hznu.islab.service.IntroductionService;
import cn.hznu.islab.util.MapToJSON;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void setIntroductionService(IntroductionService introductionService) {
        this.introductionService = introductionService;
    }

    @Override
    public IntroductionEntity getModel() {
        return introductionEntity;
    }

    public String updateInfo() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        Map<String, Object> map = new HashMap<>();
        HashMap<String, Object> queryMap = new HashMap<>();

        queryMap.put("name",introductionEntity.getName());
        List<IntroductionEntity> list = introductionService.findIntroductionsByProperties(queryMap);
        if(list != null){
            IntroductionEntity introduction = list.get(0);
            introductionEntity.setIntroductionId(introduction.getIntroductionId());
            introductionService.updateIntroduction(introductionEntity);
        }
        list.clear();
        list = introductionService.findIntroductionsByProperties(queryMap);
//        System.out.println(list.get(0).getContent());
//        System.out.println(introductionEntity.getContent());
        if(list.get(0).getContent().equals(introductionEntity.getContent())){
            map.put("updateResult", "更新成功！");
        }else{
            map.put("updateResult", "更新失败！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String getInfoContent() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> queryMap = new HashMap<>();

        queryMap.put("name",introductionEntity.getName());
        List<IntroductionEntity> list = introductionService.findIntroductionsByProperties(queryMap);
        if(list != null){
            String content;
            if(list.get(0).getContent() == null){
                content = "";
            }else {
                content = list.get(0).getContent();
            }

            map.put("content", content);
        }else{
            map.put("message", "获得content失败！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }
}
