package cn.hznu.islab.action;

import cn.hznu.islab.entity.ResearchEntity;
import cn.hznu.islab.service.ResearchService;
import cn.hznu.islab.util.MapToJSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ResearchDirectionAction extends ActionSupport implements ModelDriven<ResearchEntity> {

    private ResearchEntity researchEntity = new ResearchEntity();
    private ResearchService researchService;

    public void setResearchService(ResearchService researchService) {
        this.researchService = researchService;
    }

    @Override
    public ResearchEntity getModel() {
        return researchEntity;
    }

    public String getAllResearch() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        Map<String, Object> map = new HashMap<>();

        List<ResearchEntity> list = researchService.findAllResearchs();
        map.put("researchs", list);
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String addResearch() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        Map<String, Object> map = new HashMap<>();
        HashMap<String, Object> queryMap = new HashMap<>();

        if(ActionContext.getContext().getSession().get("loginUser") != null) {
            queryMap.put("researchDirection", researchEntity.getResearchDirection());
            List<ResearchEntity> list = researchService.findResearchsByProperties(queryMap);
            if(list == null){
                researchService.addResearch(researchEntity);
                list = researchService.findResearchsByProperties(queryMap);
                if(list == null){
                    map.put("message", "添加研究方向失败");
                }else{
                    list.get(0).setPriority(list.get(0).getResearchId());
                    researchService.updateResearch(list.get(0));
                }
            }
            else{
                map.put("message", "该研究方向已存在！");
            }
        }

        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String updateResearch() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> queryMap = new HashMap<>();

        researchService.updateResearch(researchEntity);
        queryMap.put("researchId", researchEntity.getResearchId());
        List<ResearchEntity> list = researchService.findResearchsByProperties(queryMap);
        if(list != null){
            ResearchEntity researchEntity1 = list.get(0);
            if(researchEntity1.getResearchDirection().equals(researchEntity.getResearchDirection()) &&
                researchEntity1.getIntroduction().equals(researchEntity.getIntroduction())){
            }
            else
            {
                map.put("message", "更新失败！");
            }
        }
        else
        {
            map.put("message", "更新失败！");
        }

        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String delResearch() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> queryMap = new HashMap<>();

        researchService.deleteResearch(researchEntity);
        queryMap.put("researchDirection", researchEntity.getResearchDirection());
        List<ResearchEntity> list = researchService.findResearchsByProperties(queryMap);
        if(list != null){
            map.put("message", "删除失败！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }
}
