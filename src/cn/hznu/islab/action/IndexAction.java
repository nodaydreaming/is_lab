package cn.hznu.islab.action;

import cn.hznu.islab.service.*;
import com.opensymphony.xwork2.ActionSupport;

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
    private LinkService linkService;

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

    public void setLinkService(LinkService linkService) {
        this.linkService = linkService;
    }

    public String indexInfo() {

        return NONE;
    }
}
