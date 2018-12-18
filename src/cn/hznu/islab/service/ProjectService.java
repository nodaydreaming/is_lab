package cn.hznu.islab.service;

import cn.hznu.islab.entity.ProjectEntity;

import java.util.HashMap;
import java.util.List;

public interface ProjectService {
    //添加项目
    void addProject(ProjectEntity project);
    //更新项目
    void updateProject(ProjectEntity projectEntity);
    //删除项目
    void deleteProject(ProjectEntity projectEntity);
    //通过某些字段查找一个或多个项目
    List<ProjectEntity> findProjectsByProperties(HashMap<String ,Object> queryMap);
    //所有项目
    List<ProjectEntity> findAllProjects();
}
