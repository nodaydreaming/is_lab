package cn.hznu.islab.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "link", schema = "is_lab")
public class LinkEntity {
    private int linkId;
    private String name;
    private String url;
    //排序标志，值越小优先级越高，默认与 linkId 相等
    private int priority;

    @Id
    @Column(name = "linkId")
    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "priority")
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkEntity that = (LinkEntity) o;
        return linkId == that.linkId &&
                priority == that.priority &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkId, name, url, priority);
    }
}
