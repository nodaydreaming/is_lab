package cn.hznu.islab.dao;

import cn.hznu.islab.entity.LinkEntity;

import java.util.List;

public interface LinkDao {
    //添加链接
    void addLink(LinkEntity linkEntity);
    //删除链接
    void deleteLink(LinkEntity linkEntity);
    //更新链接
    void updateLink(LinkEntity linkEntity);
    //根据id查找链接
    LinkEntity findLinkById(int id);
    //返回所有链接
    List<LinkEntity> findAllLinks();
}
