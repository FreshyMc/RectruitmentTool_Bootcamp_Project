package com.example.rectrutmenttool.service.impl;

import com.example.rectrutmenttool.model.dto.SkillDto;
import com.example.rectrutmenttool.model.entity.SkillEntity;
import com.example.rectrutmenttool.repository.SkillRepository;
import com.example.rectrutmenttool.service.SkillService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;
    private final ModelMapper modelMapper;

    public SkillServiceImpl(SkillRepository skillRepository, ModelMapper modelMapper) {
        this.skillRepository = skillRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SkillDto showSkillById(Long id) {
        Optional<SkillEntity> skill = skillRepository.findById(id);

        if(skill.isPresent()){
            return modelMapper.map(skill.get(), SkillDto.class);
        }

        return null;
    }

    @Override
    public Set<SkillDto> getActiveSkills() {
        Optional<Set<SkillEntity>> activeSkills = skillRepository.findActiveSkills();

        if(activeSkills.isPresent()){
            Set<SkillDto> skills = activeSkills.get().stream().map(s -> {
                return modelMapper.map(s, SkillDto.class);
            }).collect(Collectors.toSet());

            return skills;
        }

        return null;
    }
}
