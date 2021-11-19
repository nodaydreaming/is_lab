package cn.hznu.islab.action;

import cn.hznu.islab.entity.PaperEntity;
import cn.hznu.islab.service.PaperService;
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

public class PaperAction extends ActionSupport implements ModelDriven<PaperEntity> {
    private PaperEntity paperEntity = new PaperEntity();
    private PaperService paperService;

    public void setPaperService(PaperService paperService) {
        this.paperService = paperService;
    }

    @Override
    public PaperEntity getModel() {
        return paperEntity;
    }

    public String addPaper() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();

        queryMap.put("name", paperEntity.getName());
        List<PaperEntity> list = paperService.findPapersByProperties(queryMap);
        if (list == null) {
            paperEntity.setType(2);
            paperEntity.setDate(new Date(paperEntity.getDate().getTime() + 1000));
            paperService.addPaper(paperEntity);
            list = paperService.findPapersByProperties(queryMap);
            if(list == null){
                map.put("message", "添加失败！");
            }
        } else {
            map.put("message", "此论文标题已存在！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String getAllPapers() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();

        List<PaperEntity> list = paperService.findAllPapers();
        map.put("papers", list);
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String updatePaper() throws IOException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();

        queryMap.put("name", paperEntity.getName());
        List<PaperEntity> list = paperService.findPapersByProperties(queryMap);
        if(list != null){
            PaperEntity paper = list.get(0);
            paper.setAddress(paperEntity.getAddress());
            paper.setDate(new Date(paperEntity.getDate().getTime() + 1000));
            paperService.updatePaper(paper);

            list = paperService.findPapersByProperties(queryMap);
            if(list != null){
                PaperEntity p = list.get(0);
                if(p.getAddress().equals(paper.getAddress()) && sdf.format(p.getDate()).equals(sdf.format(paper.getDate()))){}
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

    public String deletePaper() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> queryMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();

        queryMap.put("paperId", paperEntity.getPaperId());
        List<PaperEntity> list = paperService.findPapersByProperties(queryMap);
        if(list != null){
            paperService.deletePaper(list.get(0));
            list = paperService.findPapersByProperties(queryMap);
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
