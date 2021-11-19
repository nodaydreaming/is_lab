package cn.hznu.islab.action;

import cn.hznu.islab.entity.PatentEntity;
import cn.hznu.islab.service.PatentService;
import cn.hznu.islab.util.MapToJSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PatentAction extends ActionSupport implements ModelDriven<PatentEntity> {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private PatentEntity patentEntity = new PatentEntity();
    private PatentService patentService;

    public void setPatentService(PatentService patentService) {
        this.patentService = patentService;
    }

    @Override
    public PatentEntity getModel() {
        return patentEntity;
    }

    public String addPatent() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        HashMap<String, Object> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();

        try {
            patentEntity.setDate(sdf.parse(request.getParameter("date")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        queryMap.put("number", patentEntity.getNumber());
        List<PatentEntity> list = patentService.findPatentsByProperties(queryMap);
        if(list == null){
            patentEntity.setType(3);
            patentEntity.setDate(new Date(patentEntity.getDate().getTime() + 2000));
            patentService.addPatent(patentEntity);
            list = patentService.findPatentsByProperties(queryMap);
            if(list == null){
                map.put("message", "添加失败！");
            }
        }
        else
        {
            map.put("message", "此专利号已存在！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String getPatents() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();

        List<PatentEntity> list = patentService.findAllPatents();
        map.put("patents", list);
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String updatePatent() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();

        queryMap.put("number", patentEntity.getNumber());
        List<PatentEntity> list = patentService.findPatentsByProperties(queryMap);
        if(list != null){
            PatentEntity p = list.get(0);
            p.setName(patentEntity.getName());
            p.setPrincipal(patentEntity.getPrincipal());
            p.setDate(new Date(patentEntity.getDate().getTime() + 2000));
            patentService.updatePatent(p);
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String delPatent() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();

        patentService.deletePatent(patentEntity);
        queryMap.put("patentId", patentEntity.getPatentId());
        List<PatentEntity> list = patentService.findPatentsByProperties(queryMap);
        if(list != null){
            map.put("message", "删除失败！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }
}
