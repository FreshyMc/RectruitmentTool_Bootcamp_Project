package com.example.rectrutmenttool.service;

import com.example.rectrutmenttool.model.dto.RecruiterDto;

import java.util.List;

public interface RecruiterService {
    List<RecruiterDto> listRecruiters();

    List<RecruiterDto> listLeveledRecruiters(Integer level);
}
