package cn.hznu.islab.entity;

import java.util.Objects;

/**
 * @ClassName StudentEntity
 * @Description
 * @Author GYJ
 * @Date 2018/10/20 10:23
 * @Version 1.0
 **/
public class StudentEntity {
    private int id;
    private String name;
    private String photo;
    private String gender;
    private Integer grade;
    private String telephone;
    private String qq;
    private String email;
    private String homepage;
    private String company;
    private String post;
    private String salary;
    private String intro;
    private int status;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return id == that.id &&
                status == that.status &&
                Objects.equals(name, that.name) &&
                Objects.equals(photo, that.photo) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(grade, that.grade) &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(qq, that.qq) &&
                Objects.equals(email, that.email) &&
                Objects.equals(homepage, that.homepage) &&
                Objects.equals(company, that.company) &&
                Objects.equals(post, that.post) &&
                Objects.equals(salary, that.salary) &&
                Objects.equals(intro, that.intro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, photo, gender, grade, telephone, qq, email, homepage, company, post, salary, intro, status);
    }
}
