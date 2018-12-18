package cn.hznu.islab.dao;

import cn.hznu.islab.entity.ProjectEntity;

import java.util.HashMap;
import java.util.List;

public interface ProjectDao {
    //添加项目
    void addProject(ProjectEntity projectEntity);
    //删除项目
    void deleteProject(ProjectEntity projectEntity);
    //更新项目
    void updateProject(ProjectEntity projectEntity);
    //根据id查找项目
    ProjectEntity findProjectById(int id);
    //返回所有项目
    List<ProjectEntity> findAllProjects();
    //返回根据一个或多个字段查找的某些项目
    List<ProjectEntity> findProjectsByProperties(HashMap<String ,Object> queryMap);
}
