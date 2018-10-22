package cn.hznu.islab.service.impl;

import cn.hznu.islab.dao.NoticeDao;
import cn.hznu.islab.entity.NoticeEntity;
import cn.hznu.islab.service.NoticeService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName NoticeServiceImpl
 * @Description
 * @Author GYJ
 * @Date 2018/10/16 19:14
 * @Version 1.0
 **/
public class NoticeServiceImpl implements NoticeService {
    private NoticeDao noticeDao;

    public void setNoticeDao(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }

    @Override
    public void addNotice(NoticeEntity noticeEntiry) {
        noticeDao.addNotice(noticeEntiry);
    }

    @Override
    public void updateNotice(NoticeEntity noticeEntity) {
        noticeDao.updateNotice(noticeEntity);
    }

    @Override
    public void deleteNotice(NoticeEntity noticeEntity) {
        noticeDao.deleteNotice(noticeEntity);
    }

    @Override
    public List<NoticeEntity> findNoticesByProperties(HashMap<String, String> queryMap) {
        return noticeDao.findNoticesByProperties(queryMap);
    }

    @Override
    public List<NoticeEntity> findAllNotices() {
        return noticeDao.findAllNotices();
    }

    @Override
    public List<NoticeEntity> findNoticesByDate(Date starttime, Date endtime) {
        return noticeDao.findNoticesByDate(starttime,endtime);
    }
}
