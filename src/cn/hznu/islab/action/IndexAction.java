package cn.hznu.islab.action;

import cn.hznu.islab.entity.ResearchEntity;
import cn.hznu.islab.service.*;
import cn.hznu.islab.util.MapToJSON;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private WorksService worksService;
    private PatentService patentService;
    private CompetitionService competitionService;
    private CultureService cultureService;
    private ResearchService researchService;

    public void setWorksService(WorksService worksService) {
        this.worksService = worksService;
    }

    public void setPatentService(PatentService patentService) {
        this.patentService = patentService;
    }

    public void setCompetitionService(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    public void setCultureService(CultureService cultureService) {
        this.cultureService = cultureService;
    }

    public void setResearchService(ResearchService researchService) {
        this.researchService = researchService;
    }

    public String indexInfo() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();

        List<ResearchEntity> researchs = researchService.findAllResearchs();
        map.put("researchs", researchs);

        MapToJSON.mapToJson(response, map);
        return NONE;
    }
}
