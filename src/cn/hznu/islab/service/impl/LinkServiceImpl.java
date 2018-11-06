package cn.hznu.islab.service.impl;

import cn.hznu.islab.dao.LinkDao;
import cn.hznu.islab.entity.LinkEntity;
import cn.hznu.islab.service.LinkService;

import java.util.List;

/**
 * @ClassName LinkServiceImpl
 * @Description
 * @Author GYJ
 * @Date 2018/11/6 18:33
 * @Version 1.0
 **/
public class LinkServiceImpl implements LinkService {
    private LinkDao linkDao;

    public void setLinkDao(LinkDao linkDao) {
        this.linkDao = linkDao;
    }

    @Override
    public void addLink(LinkEntity linkEntity) {
        linkDao.addLink(linkEntity);
    }

    @Override
    public void updateLink(LinkEntity linkEntity) {
        linkDao.updateLink(linkEntity);
    }

    @Override
    public void deleteLink(LinkEntity linkEntity) {
        linkDao.deleteLink(linkEntity);
    }

    @Override
    public List<LinkEntity> findAllLinks() {
        return linkDao.findAllLinks();
    }
}
