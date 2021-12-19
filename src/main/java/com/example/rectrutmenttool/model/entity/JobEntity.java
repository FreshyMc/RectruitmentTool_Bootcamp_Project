package com.example.rectrutmenttool.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "jobs")
public class JobEntity extends BaseEntity{
    @ManyToMany
    private Set<SkillEntity> skills;
    private String title;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private BigDecimal salary;

    public JobEntity() {
    }

    public Set<SkillEntity> getSkills() {
        return skills;
    }

    public JobEntity setSkills(Set<SkillEntity> skills) {
        this.skills = skills;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public JobEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public JobEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public JobEntity setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }
}
