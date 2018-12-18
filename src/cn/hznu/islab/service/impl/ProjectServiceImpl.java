package cn.hznu.islab.service.impl;

import cn.hznu.islab.dao.ProjectDao;
import cn.hznu.islab.entity.ProjectEntity;
import cn.hznu.islab.service.ProjectService;

import java.util.HashMap;
import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    private ProjectDao projectDao;

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public void addProject(ProjectEntity project) {
        projectDao.addProject(project);
    }

    @Override
    public void updateProject(ProjectEntity projectEntity) {
        projectDao.updateProject(projectEntity);
    }

    @Override
    public void deleteProject(ProjectEntity projectEntity) {
        projectDao.deleteProject(projectEntity);
    }

    @Override
    public List<ProjectEntity> findProjectsByProperties(HashMap<String, Object> queryMap) {
        return projectDao.findProjectsByProperties(queryMap);
    }

    @Override
    public List<ProjectEntity> findAllProjects() {
        return projectDao.findAllProjects();
    }
}
