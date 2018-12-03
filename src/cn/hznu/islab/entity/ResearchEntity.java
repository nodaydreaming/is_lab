package cn.hznu.islab.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "research", schema = "is_lab")
public class ResearchEntity {
    private int researchId;
    private String researchDirection;
    private String introduction;
    //排序标志，值越小优先级越高，默认和 researchId相等
    private int priority;

    @Id
    @Column(name = "researchId")
    public int getResearchId() {
        return researchId;
    }

    public void setResearchId(int researchId) {
        this.researchId = researchId;
    }

    @Basic
    @Column(name = "research_direction")
    public String getResearchDirection() {
        return researchDirection;
    }

    public void setResearchDirection(String researchDirection) {
        this.researchDirection = researchDirection;
    }

    @Basic
    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
        ResearchEntity that = (ResearchEntity) o;
        return researchId == that.researchId &&
                priority == that.priority &&
                Objects.equals(researchDirection, that.researchDirection) &&
                Objects.equals(introduction, that.introduction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(researchId, researchDirection, introduction, priority);
    }
}
