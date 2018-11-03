package cn.hznu.islab.action;

import cn.hznu.islab.entity.StudentEntity;
import cn.hznu.islab.service.StudentService;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName IndexAction
 * @Description
 * @Author GYJ
 * @Date 2018/10/31 18:17
 * @Version 1.0
 **/
public class IndexAction extends ActionSupport {
    private String result;
    private StudentEntity studentEntity;
    private StudentService studentService;


    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String execute() throws Exception {
        List<String> list = new ArrayList<>();

        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        System.out.println(list);

        List<StudentEntity> studentEntities = studentService.findAllStudents();
        System.out.println(studentEntities);
        try {
            JSONArray jsonArray = JSONArray.fromObject(list);
            result = jsonArray.toString();
//            response.getWriter().print(result);
            System.out.println("result:"+result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }
}
