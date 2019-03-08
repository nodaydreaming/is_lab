package cn.hznu.islab.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "competition", schema = "is_lab")
public class CompetitionEntity {
    private int competitionId;
    private String name;
    private String worksName;
    private String awardLevel;
    private String instructor;
    private String teamMember;
    private Date date;
    private Integer type;

    @Id
    @Column(name = "competitionId")
    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
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
    @Column(name = "worksName")
    public String getWorksName() {
        return worksName;
    }

    public void setWorksName(String worksName) {
        this.worksName = worksName;
    }

    @Basic
    @Column(name = "awardLevel")
    public String getAwardLevel() {
        return awardLevel;
    }

    public void setAwardLevel(String awardLevel) {
        this.awardLevel = awardLevel;
    }

    @Basic
    @Column(name = "instructor")
    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Basic
    @Column(name = "teamMember")
    public String getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(String teamMember) {
        this.teamMember = teamMember;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompetitionEntity that = (CompetitionEntity) o;
        return competitionId == that.competitionId &&
                Objects.equals(name, that.name) &&
                Objects.equals(worksName, that.worksName) &&
                Objects.equals(awardLevel, that.awardLevel) &&
                Objects.equals(instructor, that.instructor) &&
                Objects.equals(teamMember, that.teamMember) &&
                Objects.equals(date, that.date) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(competitionId, name, worksName, awardLevel, instructor, teamMember, date, type);
    }
}
