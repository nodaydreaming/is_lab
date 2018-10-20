package cn.hznu.islab.entity;

import java.util.Objects;

/**
 * @ClassName TeacherEntity
 * @Description
 * @Author GYJ
 * @Date 2018/10/20 10:23
 * @Version 1.0
 **/
public class TeacherEntity {
    private int id;
    private String name;
    private String photo;
    private String enName;
    private String gender;
    private String telephone;
    private String email;
    private String researchArea;
    private String degree;
    private String intro;
    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResearchArea() {
        return researchArea;
    }

    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherEntity that = (TeacherEntity) o;
        return id == that.id &&
                level == that.level &&
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
        return Objects.hash(id, name, photo, enName, gender, telephone, email, researchArea, degree, intro, level);
    }
}
