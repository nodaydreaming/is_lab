package cn.hznu.islab.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "introduction", schema = "is_lab")
public class IntroductionEntity {
    private int introductionId;
    private String name;
    private String content;

    @Id
    @Column(name = "introductionId")
    public int getIntroductionId() {
        return introductionId;
    }

    public void setIntroductionId(int introductionId) {
        this.introductionId = introductionId;
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
    @Column(name = "content")
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
        return introductionId == that.introductionId &&
                Objects.equals(name, that.name) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(introductionId, name, content);
    }
}
