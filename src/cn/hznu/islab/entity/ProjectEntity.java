package cn.hznu.islab.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "project", schema = "is_lab")
public class ProjectEntity {
    private int projectId;
    private String name;
    private String number;
    private Date stratdate;
    private Date enddate;
    private String principal;
    private String type;

    @Id
    @Column(name = "projectId")
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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
    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "stratdate")
    public Date getStratdate() {
        return stratdate;
    }

    public void setStratdate(Date stratdate) {
        this.stratdate = stratdate;
    }

    @Basic
    @Column(name = "enddate")
    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date endate) {
        this.enddate = enddate;
    }

    @Basic
    @Column(name = "principal")
    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectEntity that = (ProjectEntity) o;
        return projectId == that.projectId &&
                Objects.equals(name, that.name) &&
                Objects.equals(number, that.number) &&
                Objects.equals(stratdate, that.stratdate) &&
                Objects.equals(enddate, that.enddate) &&
                Objects.equals(principal, that.principal) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, name, number, stratdate, enddate, principal, type);
    }
}
