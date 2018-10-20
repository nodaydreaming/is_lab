package cn.hznu.islab.entity;

import java.util.Date;
import java.util.Objects;

/**
 * @ClassName NewsEntity
 * @Description
 * @Author GYJ
 * @Date 2018/10/20 10:23
 * @Version 1.0
 **/
public class NewsEntity {
    private int id;
    private String title;
    private String photo;
    private Date date;
    private String author;
    private String content;
    private Integer status;
    private String url;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsEntity that = (NewsEntity) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(photo, that.photo) &&
                Objects.equals(date, that.date) &&
                Objects.equals(author, that.author) &&
                Objects.equals(content, that.content) &&
                Objects.equals(status, that.status) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, photo, date, author, content, status, url);
    }
}
