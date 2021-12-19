package com.example.rectrutmenttool.service.impl;

import com.example.rectrutmenttool.model.dto.RecruiterDto;
import com.example.rectrutmenttool.model.entity.RecruiterEntity;
import com.example.rectrutmenttool.repository.RecruiterRepository;
import com.example.rectrutmenttool.service.RecruiterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecruiterServiceImpl implements RecruiterService {
    private final RecruiterRepository recruiterRepository;
    private final ModelMapper modelMapper;

    public RecruiterServiceImpl(RecruiterRepository recruiterRepository, ModelMapper modelMapper) {
        this.recruiterRepository = recruiterRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RecruiterDto> listRecruiters() {
        Optional<List<RecruiterEntity>> recruiters = recruiterRepository.findActiveRecruiters();

        if(recruiters.isPresent()){
            List<RecruiterDto> recruitersCollection = recruiters.get().stream().map(r -> {
                RecruiterDto mapped = modelMapper.map(r, RecruiterDto.class);

                return mapped;
            }).collect(Collectors.toList());

            return recruitersCollection;
        }

        return null;
    }

    @Override
    public List<RecruiterDto> listLeveledRecruiters(Integer level) {
        Optional<List<RecruiterEntity>> recruiters = recruiterRepository.findRecruitersByLevel(level);

        if(recruiters.isPresent()){
            List<RecruiterDto> recruitersCollection = recruiters.get().stream().map(r -> {
                RecruiterDto mapped = modelMapper.map(r, RecruiterDto.class);

                return mapped;
            }).collect(Collectors.toList());

            return recruitersCollection;
        }

        return null;
    }
}
