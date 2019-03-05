package cn.hznu.islab.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "software", schema = "is_lab", catalog = "")
public class SoftwareEntity {
    private int softId;
    private String name;
    private String address;
    private Date date;

    @Id
    @Column(name = "softId")
    public int getSoftId() {
        return softId;
    }

    public void setSoftId(int softId) {
        this.softId = softId;
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
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        SoftwareEntity that = (SoftwareEntity) o;
        return softId == that.softId &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(softId, name, address, date);
    }
}
