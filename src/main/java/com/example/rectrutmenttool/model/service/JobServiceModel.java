package com.example.rectrutmenttool.model.service;

import com.example.rectrutmenttool.model.dto.SkillDto;
import com.example.rectrutmenttool.model.entity.SkillEntity;

import java.math.BigDecimal;
import java.util.Set;

public class JobServiceModel {
    private Set<SkillDto> skills;
    private String title;
    private String description;
    private BigDecimal salary;

    public JobServiceModel() {
    }

    public Set<SkillDto> getSkills() {
        return skills;
    }

    public JobServiceModel setSkills(Set<SkillDto> skills) {
        this.skills = skills;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public JobServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public JobServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public JobServiceModel setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }
}
