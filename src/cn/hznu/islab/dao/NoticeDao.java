package cn.hznu.islab.dao;

import cn.hznu.islab.entity.NoticeEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface NoticeDao {
    //添加通知
    void addNotice(NoticeEntity noticeEntity);
    //删除通知
    void deleteNotice(NoticeEntity noticeEntity);
    //更新通知
    void updateNotice(NoticeEntity noticeEntity);
    //根据id查找通知
    NoticeEntity findNoticeById(int id);
    //返回所有通知
    List<NoticeEntity> findAllNotices();
    //返回根据一个或多个字段查找一个或多个通知
    List<NoticeEntity> findNoticesByProperties(HashMap<String ,String> queryMap);
    //返回某个时间段内的通知
    List<NoticeEntity> findNoticesByDate(Date starttime ,Date endtime);
}
