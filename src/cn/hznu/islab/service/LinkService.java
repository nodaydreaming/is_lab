package cn.hznu.islab.service;

import cn.hznu.islab.entity.LinkEntity;

import java.util.List;

public interface LinkService {
    //添加链接
    void addLink(LinkEntity link);
    //更新链接
    void updateLink(LinkEntity linkEntity);
    //删除链接
    void deleteLink(LinkEntity linkEntity);
    //所有链接
    List<LinkEntity> findAllLinks();
}
