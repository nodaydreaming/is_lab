package cn.hznu.islab.action;

import cn.hznu.islab.entity.SoftwareEntity;
import cn.hznu.islab.service.SoftwareService;
import cn.hznu.islab.util.MapToJSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

public class SoftwareAction extends ActionSupport implements ModelDriven<SoftwareEntity> {
    private SoftwareEntity softwareEntity = new SoftwareEntity();
    private SoftwareService softwareService;

    public void setSoftwareService(SoftwareService softwareService) {
        this.softwareService = softwareService;
    }

    @Override
    public SoftwareEntity getModel() {
        return softwareEntity;
    }

    public String addSoftware() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();

        queryMap.put("name", softwareEntity.getName());
        List<SoftwareEntity> list = softwareService.findSoftwaresByProperties(queryMap);
        if (list == null) {
            softwareEntity.setType(4);
            softwareService.addSoftware(softwareEntity);
            list = softwareService.findSoftwaresByProperties(queryMap);
            if(list == null){
                map.put("message", "添加失败！");
            }
        } else {
            map.put("message", "此软著标题已存在！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String getAllSoftwares() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();

        List<SoftwareEntity> list = softwareService.findAllSoftwares();
        map.put("softwares", list);
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String updateSoftware() throws IOException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();

        queryMap.put("name", softwareEntity.getName());
        List<SoftwareEntity> list = softwareService.findSoftwaresByProperties(queryMap);
        if(list != null){
            SoftwareEntity software = list.get(0);
            software.setAddress(softwareEntity.getAddress());
            software.setDate(softwareEntity.getDate());
            softwareService.updateSoftware(software);

            list = softwareService.findSoftwaresByProperties(queryMap);
            if(list != null){
                SoftwareEntity p = list.get(0);
                if(p.getAddress().equals(software.getAddress()) && sdf.format(p.getDate()).equals(sdf.format(software.getDate()))){}
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

    public String deleteSoftware() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();

        queryMap.put("softId", softwareEntity.getSoftId());
        List<SoftwareEntity> list = softwareService.findSoftwaresByProperties(queryMap);
        if(list != null){
            softwareService.deleteSoftware(list.get(0));
            list = softwareService.findSoftwaresByProperties(queryMap);
            if(list != null){
                map.put("message", "删除失败！");
            }
        }else{
            map.put("message", "删除失败！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }
}
