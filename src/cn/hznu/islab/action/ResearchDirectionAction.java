package cn.hznu.islab.action;

import cn.hznu.islab.entity.ResearchEntity;
import cn.hznu.islab.service.ResearchService;
import cn.hznu.islab.util.MapToJSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
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

        Map<String, String> queryMap = new HashMap<>();



        return NONE;
    }
}
