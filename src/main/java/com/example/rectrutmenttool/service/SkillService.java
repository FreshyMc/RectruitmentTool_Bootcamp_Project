package com.example.rectrutmenttool.service;

import com.example.rectrutmenttool.model.dto.SkillDto;

import java.util.Set;

public interface SkillService {
    SkillDto showSkillById(Long id);

    Set<SkillDto> getActiveSkills();
}
