package cn.hznu.islab.entity;

import java.util.Date;
import java.util.Objects;

/**
 * @ClassName CultureEntity
 * @Description
 * @Author GYJ
 * @Date 2018/10/20 10:28
 * @Version 1.0
 **/
public class CultureEntity {
    private int id;
    private String title;
    private Date date;
    private String author;
    private String content;
    /**
     * 1 生活剪影（实验室环境及平时工作照片）
     * 2 团建活动
     * 3 毕业时分
     */
    private Integer type;
    private Integer status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CultureEntity that = (CultureEntity) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(date, that.date) &&
                Objects.equals(author, that.author) &&
                Objects.equals(content, that.content) &&
                Objects.equals(type, that.type) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date, author, content, type, status);
    }
}
