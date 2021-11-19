package cn.hznu.islab.action;

import cn.hznu.islab.entity.CompetitionEntity;
import cn.hznu.islab.service.CompetitionService;
import cn.hznu.islab.util.MapToJSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class CompetitionAction extends ActionSupport implements ModelDriven<CompetitionEntity> {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private CompetitionEntity competitionEntity = new CompetitionEntity();
    private CompetitionService competitionService;

    public void setCompetitionService(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @Override
    public CompetitionEntity getModel() {
        return competitionEntity;
    }

    public String addCompetition() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();

        queryMap.put("name", competitionEntity.getName());
        queryMap.put("worksName", competitionEntity.getWorksName());
        List<CompetitionEntity> list = competitionService.findCompetitionsByProperties(queryMap);

        if(list == null){
            competitionEntity.setType(1);
            competitionEntity.setDate(new Date(competitionEntity.getDate().getTime() + 3000));
            competitionService.addCompetition(competitionEntity);
            list = competitionService.findCompetitionsByProperties(queryMap);
            if(list == null){
                map.put("message", "添加失败！");
            }
        }
        else
        {
            map.put("message", "此条记录已存在！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String getCompetitions() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();

        List<CompetitionEntity> list = competitionService.findAllCompetitions();
        map.put("competitions", list);
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String updateCompetition() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();

        queryMap.put("name", competitionEntity.getName());
        queryMap.put("worksName", competitionEntity.getWorksName());
        List<CompetitionEntity> list = competitionService.findCompetitionsByProperties(queryMap);

        if(list != null){
            CompetitionEntity c = list.get(0);

            c.setTeamMember(competitionEntity.getTeamMember());
            c.setInstructor(competitionEntity.getInstructor());
            c.setAwardLevel(competitionEntity.getAwardLevel());
            c.setDate(new Date(competitionEntity.getDate().getTime() + 3000));

            competitionService.updateCompetition(c);
            list.clear();
            list = competitionService.findCompetitionsByProperties(queryMap);
            if(list != null){
                CompetitionEntity c1 = list.get(0);
                System.out.println(c.toString());
                System.out.println(c1.toString());
                if(c1.getInstructor().equals(c.getInstructor()) && c1.getTeamMember().equals(c.getTeamMember()) &&
                        sdf.format(c1.getDate()).equals(sdf.format(c.getDate())) && c1.getAwardLevel().equals(c.getAwardLevel())){
                }
                else{
                    map.put("message", "更新失败！");
                }
            }else{
                map.put("message", "更新失败！");
            }
        }else{
            map.put("message", "更新失败！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String delCompetition() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();

        competitionService.deleteCompetition(competitionEntity);
        queryMap.put("competitionId", competitionEntity.getCompetitionId());
        if(competitionService.findCompetitionsByProperties(queryMap) != null){
            map.put("message", "删除失败！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }
}
