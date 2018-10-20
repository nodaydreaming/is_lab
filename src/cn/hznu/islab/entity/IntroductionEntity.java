package cn.hznu.islab.entity;

import java.util.Objects;

/**
 * @ClassName IntroductionEntity
 * @Description
 * @Author GYJ
 * @Date 2018/10/20 10:23
 * @Version 1.0
 **/
public class IntroductionEntity {
    private int id;
    private String name;
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntroductionEntity that = (IntroductionEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content);
    }
}
