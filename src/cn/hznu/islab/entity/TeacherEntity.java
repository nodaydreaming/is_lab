package cn.hznu.islab.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teacher", schema = "is_lab")
public class TeacherEntity {
    private int teacherId;
    private String name;
    private String photo;
    private String enName;
    private String gender;
    private String telephone;
    private String email;
    private String researchArea;
    private String degree;
    private String intro;
    //排序标志，值越小优先级越高，默认与 teacherId 相等
    private int priority;

    @Id
    @Column(name = "teacherId")
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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
    @Column(name = "photo")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Basic
    @Column(name = "en_name")
    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "research_area")
    public String getResearchArea() {
        return researchArea;
    }

    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    @Basic
    @Column(name = "degree")
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Basic
    @Column(name = "intro")
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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
        TeacherEntity that = (TeacherEntity) o;
        return teacherId == that.teacherId &&
                priority == that.priority &&
                Objects.equals(name, that.name) &&
                Objects.equals(photo, that.photo) &&
                Objects.equals(enName, that.enName) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(researchArea, that.researchArea) &&
                Objects.equals(degree, that.degree) &&
                Objects.equals(intro, that.intro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, name, photo, enName, gender, telephone, email, researchArea, degree, intro, priority);
    }

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "teacherId=" + teacherId +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", enName='" + enName + '\'' +
                ", gender='" + gender + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", researchArea='" + researchArea + '\'' +
                ", degree='" + degree + '\'' +
                ", intro='" + intro + '\'' +
                ", priority=" + priority +
                '}';
    }
}
