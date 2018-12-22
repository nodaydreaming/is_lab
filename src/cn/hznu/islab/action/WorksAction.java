package cn.hznu.islab.action;

import cn.hznu.islab.entity.WorksEntity;
import cn.hznu.islab.service.WorksService;
import cn.hznu.islab.util.MapToJSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class WorksAction extends ActionSupport implements ModelDriven<WorksEntity> {
    private WorksEntity worksEntity = new WorksEntity();
    private WorksService worksService;

    public void setWorksService(WorksService worksService) {
        this.worksService = worksService;
    }

    @Override
    public WorksEntity getModel() {
        return worksEntity;
    }

    public String addWorks() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();

        queryMap.put("name", worksEntity.getName());
        List<WorksEntity> list = worksService.findWorksByProperties(queryMap);
        if(list == null){
            worksService.addWorks(worksEntity);
            list = worksService.findWorksByProperties(queryMap);
            if(list == null){
                map.put("message", "添加失败！");
            }
        }else{
            map.put("message", "该作品名称已存在！");
        }

        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String getAllWorks() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();

        List<WorksEntity> list = worksService.findAllWorks();

        map.put("works", list);

        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String updateWorks() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();

        queryMap.put("name", worksEntity.getName());
        List<WorksEntity> list = worksService.findWorksByProperties(queryMap);
        if(list != null){
            WorksEntity w = list.get(0);
            w.setIntro(worksEntity.getIntro());
            w.setPhoto(worksEntity.getPhoto());
            worksService.updateWorks(w);
            list = worksService.findWorksByProperties(queryMap);
            if(list != null){
                WorksEntity works = list.get(0);
                if (works.getIntro().equals(w.getIntro()) && works.getPhoto().equals(w.getPhoto())){
                }
                else{
                    map.put("message", "更新失败！");
                }
            }
            else{
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

    public String delWorks() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();

        worksService.deleteWorks(worksEntity);
        queryMap.put("worksId", worksEntity.getWorksId());
        List<WorksEntity> list = worksService.findWorksByProperties(queryMap);
        if(list != null){
            map.put("message", "删除失败！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

}
