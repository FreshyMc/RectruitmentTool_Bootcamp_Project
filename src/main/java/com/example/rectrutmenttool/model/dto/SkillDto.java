package com.example.rectrutmenttool.model.dto;

import javax.validation.constraints.NotBlank;

public class SkillDto {
    @NotBlank
    private String name;

    public SkillDto() {
    }

    public String getName() {
        return name;
    }

    public SkillDto setName(String name) {
        this.name = name;
        return this;
    }
}
