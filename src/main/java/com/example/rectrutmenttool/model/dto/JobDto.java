package com.example.rectrutmenttool.model.dto;

import com.example.rectrutmenttool.model.entity.SkillEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;

public class JobDto {
    private Set<SkillEntity> skills;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @Positive
    private BigDecimal salary;

    public JobDto() {
    }

    public Set<SkillEntity> getSkills() {
        return skills;
    }

    public JobDto setSkills(Set<SkillEntity> skills) {
        this.skills = skills;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public JobDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public JobDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public JobDto setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }
}
