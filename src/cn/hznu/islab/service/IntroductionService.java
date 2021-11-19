package cn.hznu.islab.service;

import cn.hznu.islab.entity.IntroductionEntity;

import java.util.HashMap;
import java.util.List;

public interface IntroductionService {
    //添加介绍
    void addIntroduction(IntroductionEntity introduction);
    //更新介绍
    void updateIntroduction(IntroductionEntity introductionEntity);
    //删除介绍
    void deleteIntroduction(IntroductionEntity introductionEntity);
    //通过某些字段查找一个或多个介绍
    List<IntroductionEntity> findIntroductionsByProperties(HashMap<String ,Object> queryMap);
    //所有介绍
    List<IntroductionEntity> findAllIntroductions();
}
