package com.example.rectrutmenttool.repository;

import com.example.rectrutmenttool.model.entity.InterviewEntity;
import com.example.rectrutmenttool.model.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<InterviewEntity, Long> {
    List<InterviewEntity> findAllByJob(JobEntity job);
}
