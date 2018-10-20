package cn.hznu.islab.action;

import cn.hznu.islab.entity.TeacherEntity;
import cn.hznu.islab.service.TeacherService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

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


}
