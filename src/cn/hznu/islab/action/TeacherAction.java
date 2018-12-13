package cn.hznu.islab.action;

import cn.hznu.islab.entity.TeacherEntity;
import cn.hznu.islab.service.TeacherService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

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
        HashMap<String, String> queryMap = new HashMap<>();

        queryMap.put("name", teacherEntity.getName());

        return NONE;
    }

}
