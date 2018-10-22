package cn.hznu.islab.service;

import cn.hznu.islab.entity.NoticeEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface NoticeService {
    //添加通知
    void addNotice(NoticeEntity noticeEntiry);
    //更新通知
    void updateNotice(NoticeEntity noticeEntity);
    //删除通知
    void deleteNotice(NoticeEntity noticeEntity);
    //通过某些字段查找一个或多个通知
    List<NoticeEntity> findNoticesByProperties(HashMap<String ,String> queryMap);
    //所有通知
    List<NoticeEntity> findAllNotices();
    //查询某个时间段内的通知
    List<NoticeEntity> findNoticesByDate(Date starttime ,Date endtime);
}
