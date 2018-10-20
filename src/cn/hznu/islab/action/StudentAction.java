package cn.hznu.islab.action;

import cn.hznu.islab.entity.StudentEntity;
import cn.hznu.islab.service.StudentService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

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
}
