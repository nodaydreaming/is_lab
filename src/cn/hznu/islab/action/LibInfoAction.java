package cn.hznu.islab.action;

import cn.hznu.islab.entity.IntroductionEntity;
import cn.hznu.islab.service.IntroductionService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName LibInfoAction
 * @Description
 * @Author GYJ
 * @Date 2018/11/19 18:44
 * @Version 1.0
 **/
public class LibInfoAction extends ActionSupport implements ModelDriven<IntroductionEntity> {
    private IntroductionService introductionService;

    private IntroductionEntity introductionEntity = new IntroductionEntity();

    public void setIntroductionService(IntroductionService introductionService) {
        this.introductionService = introductionService;
    }

    @Override
    public IntroductionEntity getModel() {
        return introductionEntity;
    }

    public String updateInfo(){
        System.out.println(introductionEntity.getName());
        System.out.println(introductionEntity.getContent());

        IntroductionEntity introduction;
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("name",introductionEntity.getName());

        List<IntroductionEntity> list = introductionService.findIntroductionsByProperties(queryMap);

        if(list != null){
            introduction = list.get(0);
            introduction.setContent(introductionEntity.getContent());
            introductionService.updateIntroduction(introduction);
        }

        list = introductionService.findIntroductionsByProperties(queryMap);

        if(list.get(0).getContent().equals(introductionEntity.getContent())){
            System.out.println("更新成功！");
        }

        return NONE;
    }
}
