package com.example.rectrutmenttool.service.impl;

import com.example.rectrutmenttool.model.dto.InterviewDto;
import com.example.rectrutmenttool.repository.InterviewRepository;
import com.example.rectrutmenttool.service.InterviewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewServiceImpl implements InterviewService {
    private final InterviewRepository interviewRepository;
    private final ModelMapper modelMapper;

    public InterviewServiceImpl(InterviewRepository interviewRepository, ModelMapper modelMapper) {
        this.interviewRepository = interviewRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<InterviewDto> showInterviews() {
        List<InterviewDto> interviews = interviewRepository.findAll().stream().map(i -> {
            InterviewDto interview = new InterviewDto();

            interview.setCandidateName(i.getCandidate().getFirstName() + " " + i.getCandidate().getLastName()).
                    setRecruiterName(i.getRecruiter().getLastName());

            return interview;
        }).collect(Collectors.toList());

        return interviews;
    }
}
