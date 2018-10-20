package cn.hznu.islab.action;

import cn.hznu.islab.entity.NoticeEntity;
import cn.hznu.islab.service.NoticeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName NoticeAction
 * @Description
 * @Author GYJ
 * @Date 2018/10/16 19:45
 * @Version 1.0
 **/
public class NoticeAction extends ActionSupport implements ModelDriven<NoticeEntity> {

    private NoticeEntity noticeEntity = new NoticeEntity();
    private NoticeService noticeService;
    private HttpServletRequest request;

    @Override
    public NoticeEntity getModel() {
        return noticeEntity;
    }

    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    public String addNotice(){
        request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        String introduction = new String(request.getParameter("editorValue"));

        System.out.println(introduction);
        return SUCCESS;
    }

}
