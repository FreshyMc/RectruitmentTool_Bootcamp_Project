package com.example.rectrutmenttool.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "skills")
public class SkillEntity extends BaseEntity {
    private String name;

    public SkillEntity() {
    }

    public String getName() {
        return name;
    }

    public SkillEntity setName(String name) {
        this.name = name;
        return this;
    }
}
