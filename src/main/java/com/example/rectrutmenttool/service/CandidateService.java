package com.example.rectrutmenttool.service;

import com.example.rectrutmenttool.model.dto.CandidateDto;
import com.example.rectrutmenttool.model.service.CandidateServiceModel;

import java.util.List;

public interface CandidateService {
    void createCandidate(CandidateServiceModel mapped);

    List<CandidateDto> listCandidates();

    CandidateDto listCandidate(Long id);

    void removeCandidate(Long id);

    void updateCandidate(Long id, CandidateServiceModel mapped);
}
