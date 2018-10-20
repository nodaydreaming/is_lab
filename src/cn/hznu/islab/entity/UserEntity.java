package cn.hznu.islab.entity;

import java.util.Objects;

/**
 * @ClassName UserEntity
 * @Description
 * @Author GYJ
 * @Date 2018/10/20 10:23
 * @Version 1.0
 **/
public class UserEntity {
    private int id;
    private String photo;
    private String username;
    private String password;
    private String gender;
    private String nickname;
    private String email;
    private String telephone;
    private Integer status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                Objects.equals(photo, that.photo) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photo, username, password, gender, nickname, email, telephone, status);
    }
}
