package cn.hznu.islab.action;

import cn.hznu.islab.entity.StudentEntity;
import cn.hznu.islab.service.StudentService;
import cn.hznu.islab.util.MapToJSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName StudentAction
 * @Description student类的action类
 * @Author GYJ
 * @Date 2018/10/16 19:43
 * @Version 1.0
 **/
public class StudentAction extends ActionSupport implements ModelDriven<StudentEntity> {
    private StudentEntity studentEntity = new StudentEntity();
    private StudentService studentService;

    @Override
    public StudentEntity getModel() {
        return studentEntity;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public String getStudentsByType() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> queryMap = new HashMap<>();
        int type = studentEntity.getType();

        queryMap.put("type", type);
        List<StudentEntity> list = studentService.findStudentsByProperties(queryMap);

        map.put("students", list);

        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String addStudent() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> queryMap = new HashMap<>();

        queryMap.put("telephone", studentEntity.getTelephone());
        List<StudentEntity> list = studentService.findStudentsByProperties(queryMap);
        if(list == null){
            studentService.addStudent(studentEntity);
            list = studentService.findStudentsByProperties(queryMap);
            if(list == null){
                map.put("message", "添加失败！");
            }
        }
        else{
            map.put("message", "该手机号已存在！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String updateStudent() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> queryMap = new HashMap<>();

        queryMap.put("telephone", studentEntity.getTelephone());
        List<StudentEntity> list = studentService.findStudentsByProperties(queryMap);
        if(list != null){
            StudentEntity s = list.get(0);
            studentEntity.setStudentId(s.getStudentId());
            studentService.updateStudent(studentEntity);

            list = studentService.findStudentsByProperties(queryMap);
            if(list != null) {
                StudentEntity ss = list.get(0);
                if (ss.equals(studentEntity)) {
                } else {
                    map.put("message", "更新失败！");
                }
            }
        }
        else{
            map.put("message", "更新失败！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String delStudent() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> queryMap = new HashMap<>();

        studentService.deleteStudent(studentEntity);
        queryMap.put("studentId", studentEntity.getStudentId());
        List<StudentEntity> list = studentService.findStudentsByProperties(queryMap);
        if(list != null){
            map.put("message", "删除失败！");
        }
        MapToJSON.mapToJson(response, map);
        return NONE;
    }
}
