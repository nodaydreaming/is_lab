package cn.hznu.islab.entity;

import java.util.Objects;

/**
 * @ClassName ResearchEntity
 * @Description
 * @Author GYJ
 * @Date 2018/10/20 10:23
 * @Version 1.0
 **/
public class ResearchEntity {
    private int id;
    private String researchDirection;
    private String introduction;
    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResearchDirection() {
        return researchDirection;
    }

    public void setResearchDirection(String researchDirection) {
        this.researchDirection = researchDirection;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
        ResearchEntity that = (ResearchEntity) o;
        return id == that.id &&
                level == that.level &&
                Objects.equals(researchDirection, that.researchDirection) &&
                Objects.equals(introduction, that.introduction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, researchDirection, introduction, level);
    }
}
