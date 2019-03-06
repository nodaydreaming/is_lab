package cn.hznu.islab.action;

import cn.hznu.islab.entity.CultureEntity;
import cn.hznu.islab.service.CultureService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class CultureAction extends ActionSupport implements ModelDriven<CultureEntity> {
    private CultureService cultureService;
    private CultureEntity cultureEntity = new CultureEntity();

    @Override
    public CultureEntity getModel() {
        return cultureEntity;
    }

    public void setCultureService(CultureService cultureService) {
        this.cultureService = cultureService;
    }

    public String addCulture() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        //存储返回前端的数据
        HashMap<String, Object> map = new HashMap<>();
        //查询数据库使用的map
        HashMap<String, Object> queryMap = new HashMap<>();


        return NONE;
    }
}
