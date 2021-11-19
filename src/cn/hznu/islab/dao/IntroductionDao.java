package cn.hznu.islab.dao;

import cn.hznu.islab.entity.IntroductionEntity;

import java.util.HashMap;
import java.util.List;

public interface IntroductionDao {
    //添加介绍
    void addIntroduction(IntroductionEntity introductionEntity);
    //删除介绍
    void deleteIntroduction(IntroductionEntity introductionEntity);
    //更新介绍
    void updateIntroduction(IntroductionEntity introductionEntity);
    //根据id查找介绍
    IntroductionEntity findIntroductionById(int id);
    //根据某些字段查找一个或多个介绍
    List<IntroductionEntity> findIntroductionsByProperties(HashMap<String, Object> queryMap);
    //返回所有介绍
    List<IntroductionEntity> findAllIntroductions();

}
