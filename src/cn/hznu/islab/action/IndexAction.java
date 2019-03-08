package cn.hznu.islab.action;

import cn.hznu.islab.entity.*;
import cn.hznu.islab.service.*;
import cn.hznu.islab.util.GetCultureImagesUtil;
import cn.hznu.islab.util.MapToJSON;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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
    private PaperService paperService;
    private SoftwareService softwareService;
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

    public void setPaperService(PaperService paperService) {
        this.paperService = paperService;
    }

    public void setSoftwareService(SoftwareService softwareService) {
        this.softwareService = softwareService;
    }

    public String indexInfo() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();

        List<WorksEntity> works = worksService.findAllWorks();
        List<ResearchEntity> researchs = researchService.findAllResearchs();
        List<CultureEntity> cultures = cultureService.findAllCultures();
        List<String> cultureImages = new GetCultureImagesUtil().getCultureImages();

        map.put("works", works);
        map.put("cultures", cultures);
        map.put("researchs", researchs);
        map.put("cultureImages", cultureImages);

        List<PaperEntity> papers = paperService.findAllPapers();
        List<PatentEntity> patents = patentService.findAllPatents();
        List<SoftwareEntity> softwares = softwareService.findAllSoftwares();
        List<CompetitionEntity> competitions = competitionService.findAllCompetitions();

        Map<Date, Object> sortObject = new HashMap<>();
        List<Object> results = new LinkedList<>();

        for(int i = 0; i < papers.size(); ++i){
            sortObject.put(papers.get(i).getDate(), papers.get(i));
        }
        for(int i = 0; i < patents.size(); ++i){
            sortObject.put(patents.get(i).getDate(), patents.get(i));
        }
        for(int i = 0; i < softwares.size(); ++i){
            sortObject.put(softwares.get(i).getDate(), softwares.get(i));
        }
        for(int i = 0; i < competitions.size(); ++i){
            sortObject.put(competitions.get(i).getDate(), competitions.get(i));
        }
        Map<Date, Object> resultMap = sortMapByKey(sortObject);

        for(Date d : resultMap.keySet()){
            results.add(resultMap.get(d));
        }

        map.put("results", results);
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String getResults() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();

        List<PaperEntity> papers = paperService.findAllPapers();
        List<PatentEntity> patents = patentService.findAllPatents();
        List<SoftwareEntity> softwares = softwareService.findAllSoftwares();
        List<CompetitionEntity> competitions = competitionService.findAllCompetitions();

        map.put("papers", papers);
        map.put("patents", patents);
        map.put("softwares", softwares);
        map.put("competitions", competitions);

        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    class MapKeyComparator implements Comparator<Date>{
        @Override
        public int compare(Date d1, Date d2) {
            return d2.compareTo(d1);
        }
    }
    public Map<Date, Object> sortMapByKey(Map<Date, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<Date, Object> sortMap = new TreeMap<Date, Object>(new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }

}
