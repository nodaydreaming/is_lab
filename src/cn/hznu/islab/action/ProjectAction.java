package cn.hznu.islab.action;

import cn.hznu.islab.entity.ProjectEntity;
import cn.hznu.islab.service.ProjectService;
import cn.hznu.islab.util.MapToJSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

public class ProjectAction extends ActionSupport implements ModelDriven<ProjectEntity> {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    ProjectEntity projectEntity = new ProjectEntity();
    ProjectService projectService;

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public ProjectEntity getModel() {
        return projectEntity;
    }

    public String addProject() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> queryMap = new HashMap<>();

        try {
            projectEntity.setStartdate(sdf.parse(request.getParameter("startdate")));
            projectEntity.setEnddate(sdf.parse(request.getParameter("enddate")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        queryMap.put("name", projectEntity.getName());
        List<ProjectEntity> list = projectService.findProjectsByProperties(queryMap);
        if(list == null) {
            projectService.addProject(projectEntity);
            queryMap.put("number", projectEntity.getNumber());
            queryMap.put("startdate", sdf.format(projectEntity.getStartdate()));
            queryMap.put("enddate", sdf.format(projectEntity.getEnddate()));
            queryMap.put("principal", projectEntity.getPrincipal());
            queryMap.put("type", projectEntity.getType());
            list = projectService.findProjectsByProperties(queryMap);
            if (list == null) {
                map.put("message", "添加项目失败！");
            }
        }
        else{
            map.put("message", "该项目已存在！");
        }

        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String getAllProjects() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();

        List<ProjectEntity> list = projectService.findAllProjects();
        map.put("projects", list);

        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String delProject() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> queryMap = new HashMap<>();

        projectService.deleteProject(projectEntity);

        queryMap.put("name", projectEntity.getName());
        queryMap.put("number", projectEntity.getNumber());
        queryMap.put("startdate", sdf.format(projectEntity.getStartdate()));
        queryMap.put("enddate", sdf.format(projectEntity.getEnddate()));
        queryMap.put("principal", projectEntity.getPrincipal());
        queryMap.put("type", projectEntity.getType());
        List<ProjectEntity> list = projectService.findProjectsByProperties(queryMap);
        if(list != null){
            map.put("message", "删除项目失败！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String updateProject() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> queryMap = new HashMap<>();

        projectService.updateProject(projectEntity);
        queryMap.put("projectId", projectEntity.getProjectId());
        List<ProjectEntity> list = projectService.findProjectsByProperties(queryMap);
        if(list != null){
            ProjectEntity projectEntity1 = list.get(0);
            if(projectEntity1.getName().equals(projectEntity.getName()) &&
                projectEntity1.getNumber().equals(projectEntity.getNumber()) &&
                projectEntity1.getStartdate().equals(projectEntity.getStartdate()) &&
                projectEntity1.getEnddate().equals(projectEntity.getEnddate()) &&
                projectEntity1.getPrincipal().equals(projectEntity.getPrincipal()) &&
                projectEntity1.getType().equals(projectEntity.getType())){

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
}
