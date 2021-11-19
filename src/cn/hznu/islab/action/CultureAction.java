package cn.hznu.islab.action;

import cn.hznu.islab.entity.CultureEntity;
import cn.hznu.islab.entity.UserEntity;
import cn.hznu.islab.service.CultureService;
import cn.hznu.islab.util.MapToJSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CultureAction extends ActionSupport implements ModelDriven<CultureEntity> {
    private CultureService cultureService;
    private CultureEntity cultureEntity = new CultureEntity();

    @Override
    public CultureEntity getModel() {
        return cultureEntity;
    }

    public void setCultureService(CultureService cultureService) {
        this.cultureService = cultureService;
    }

    public String addCulture() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        //存储返回前端的数据
        HashMap<String, Object> map = new HashMap<>();
        //查询数据库使用的map
        HashMap<String, Object> queryMap = new HashMap<>();
        cultureEntity.setAuthor(((UserEntity) ActionContext.getContext().getSession().get("loginUser")).getUserId() + "");
        cultureEntity.setDate(new Date());

        queryMap.put("title", cultureEntity.getTitle());
        List<CultureEntity> list = cultureService.findCulturesByProperties(queryMap);
        if(list == null || list.size() == 0){
            cultureService.addCulture(cultureEntity);
        }
        else{
            map.put("message", "此标题已存在！");
        }

        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String getCultureByType() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        //存储返回前端的数据
        HashMap<String, Object> map = new HashMap<>();
        //查询数据库使用的map
        HashMap<String, Object> queryMap = new HashMap<>();

        queryMap.put("type", cultureEntity.getType());
        List<CultureEntity> list = cultureService.findCulturesByProperties(queryMap);

        map.put("cultureList", list);
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String delCulture() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        //存储返回前端的数据
        HashMap<String, Object> map = new HashMap<>();
        //查询数据库使用的map
        HashMap<String, Object> queryMap = new HashMap<>();

        queryMap.put("cultureId", cultureEntity.getCultureId());
        List<CultureEntity> list = cultureService.findCulturesByProperties(queryMap);
        if(list != null){
            cultureService.deleteCulture(list.get(0));
        }
        else{
            map.put("message", "此文章不存在！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String updateCulture() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        //存储返回前端的数据
        HashMap<String, Object> map = new HashMap<>();
        //查询数据库使用的map
        HashMap<String, Object> queryMap = new HashMap<>();

        queryMap.put("cultureId", cultureEntity.getCultureId());
        List<CultureEntity> list = cultureService.findCulturesByProperties(queryMap);
        if(list != null){
            CultureEntity culture = list.get(0);
            culture.setDate(new Date());
            culture.setTitle(cultureEntity.getTitle());
            culture.setType(cultureEntity.getType());
            culture.setContent(cultureEntity.getContent());

            cultureService.updateCulture(culture);
        }
        else{
            map.put("message", "此文章不存在！");
        }
        return NONE;
    }
}
