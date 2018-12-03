package cn.hznu.islab.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "patent", schema = "is_lab")
public class PatentEntity {
    private int patentId;
    private String name;
    private String number;
    private String principal;
    private Date date;

    @Id
    @Column(name = "patentId")
    public int getPatentId() {
        return patentId;
    }

    public void setPatentId(int patentId) {
        this.patentId = patentId;
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
    @Column(name = "principal")
    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatentEntity that = (PatentEntity) o;
        return patentId == that.patentId &&
                Objects.equals(name, that.name) &&
                Objects.equals(number, that.number) &&
                Objects.equals(principal, that.principal) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patentId, name, number, principal, date);
    }
}
