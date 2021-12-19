package com.example.rectrutmenttool.service;

import com.example.rectrutmenttool.model.dto.JobDto;
import com.example.rectrutmenttool.model.service.JobServiceModel;

import java.util.List;

public interface JobService {
    void createJob(JobServiceModel mapped);

    List<JobDto> searchBySkill(String skill);

    void removeJob(Long id);
}
