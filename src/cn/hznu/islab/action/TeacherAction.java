package cn.hznu.islab.action;

import cn.hznu.islab.entity.TeacherEntity;
import cn.hznu.islab.service.TeacherService;
import cn.hznu.islab.util.MapToJSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName TeacherAction
 * @Description teacher类的action类
 * @Author GYJ
 * @Date 2018/10/16 19:37
 * @Version 1.0
 **/
public class TeacherAction extends ActionSupport implements ModelDriven<TeacherEntity> {
    private TeacherEntity teacherEntity = new TeacherEntity();
    private TeacherService teacherService;

    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Override
    public TeacherEntity getModel() {
        return teacherEntity;
    }

    /**
     * 添加指导老师，不可
     * @return
     * @throws IOException
     */
    public String addTeacher() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, Object> queryMap = new HashMap<>();
        queryMap.put("email", teacherEntity.getEmail());
        List<TeacherEntity> list = teacherService.findTeachersByProperties(queryMap);

        if(list == null){
            teacherService.addTeacher(teacherEntity);
            list = teacherService.findTeachersByProperties(queryMap);
            if(list != null && list.get(0) != null){
                TeacherEntity t = list.get(0);
                t.setPriority(t.getTeacherId());
                teacherService.updateTeacher(t);
            }
            else{
                map.put("message", "添加失败！");
            }
        }
        else{
            map.put("message", "此邮箱已存在，请重新输入！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String getAllInstructors() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();

        List<TeacherEntity> list = teacherService.findAllTeachers();
        map.put("instructors", list);
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String delTeacher() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> queryMap = new HashMap<>();

        teacherService.deleteTeacher(teacherEntity);
        queryMap.put("teacherId", teacherEntity.getTeacherId());
        List<TeacherEntity> list = teacherService.findTeachersByProperties(queryMap);
        if(list != null){
            map.put("message", "删除指导老师失败！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String updateTeacher() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> queryMap = new HashMap<>();

        queryMap.put("email", teacherEntity.getEmail());
        List<TeacherEntity> list = teacherService.findTeachersByProperties(queryMap);
        if(list != null){
            TeacherEntity entity = list.get(0);
            entity.setPhoto(teacherEntity.getPhoto());
            entity.setName(teacherEntity.getName());
            entity.setGender(teacherEntity.getGender());
            entity.setDegree(teacherEntity.getDegree());
            entity.setIntro(teacherEntity.getIntro());
            teacherService.updateTeacher(entity);
            list = teacherService.findTeachersByProperties(queryMap);
            if(list != null) {
                TeacherEntity teacherEntity1 = list.get(0);
                if (teacherEntity1.getName().equals(entity.getName()) &&
                        teacherEntity1.getDegree().equals(entity.getDegree()) &&
                        teacherEntity1.getIntro().equals(entity.getIntro()) &&
                        teacherEntity1.getPhoto().equals(entity.getPhoto()) &&
                        teacherEntity1.getGender().equals(entity.getGender())) {
                } else {
                    map.put("message", "更新失败1！");
                }
            }
        }
        else
        {
            map.put("message", "更新失败2！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }
}