package com.example.rectrutmenttool.service.impl;

import com.example.rectrutmenttool.model.dto.JobDto;
import com.example.rectrutmenttool.model.dto.SkillDto;
import com.example.rectrutmenttool.model.entity.*;
import com.example.rectrutmenttool.model.service.JobServiceModel;
import com.example.rectrutmenttool.repository.*;
import com.example.rectrutmenttool.service.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;
    private final CandidateRepository candidateRepository;
    private final RecruiterRepository recruiterRepository;
    private final InterviewRepository interviewRepository;
    private final ModelMapper modelMapper;

    public JobServiceImpl(JobRepository jobRepository, SkillRepository skillRepository, CandidateRepository candidateRepository, RecruiterRepository recruiterRepository, InterviewRepository interviewRepository, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.skillRepository = skillRepository;
        this.candidateRepository = candidateRepository;
        this.recruiterRepository = recruiterRepository;
        this.interviewRepository = interviewRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void createJob(JobServiceModel mapped) {
        JobEntity job = new JobEntity();

        Set<SkillEntity> skills = matchSkills(mapped.getSkills());

        job.setDescription(mapped.getDescription()).
                setSalary(mapped.getSalary()).
                setTitle(mapped.getTitle()).
                setSkills(skills);

        JobEntity savedJob = jobRepository.save(job);

        Optional<List<CandidateEntity>> candidatesForJob = candidateRepository.findAllBySkillsIn(skills);

        if(candidatesForJob.isPresent()){
            List<CandidateEntity> candidates = candidatesForJob.get();

            candidates.forEach(c -> {
                RecruiterEntity recruiter = c.getRecruiter();

                int availableSlots = recruiter.getInterviewSlots().size();

                if(availableSlots < 5){
                    Set<CandidateEntity> slots = recruiter.getInterviewSlots();

                    slots.add(c);

                    recruiter.setInterviewSlots(slots);

                    recruiter.setExperience(recruiter.getExperience() + 1);

                    recruiterRepository.save(recruiter);

                    InterviewEntity interview = new InterviewEntity();

                    interview.setRecruiter(recruiter).
                            setCandidate(c).
                            setJob(savedJob);

                    interviewRepository.save(interview);
                }
            });
        }
    }

    @Override
    @Transactional
    public List<JobDto> searchBySkill(String skill) {
        Optional<SkillEntity> skillEntity = skillRepository.findSkillEntityByName(skill);

        if(!skillEntity.isPresent()){
            return null;
        }

        Optional<List<JobEntity>> jobEntities = jobRepository.findAllBySkillsIn(Set.of(skillEntity.get()));

        if(jobEntities.isPresent()){
            List<JobEntity> jobs = jobEntities.get();

            List<JobDto> mappedJobs = jobs.stream().map(s -> {
                return modelMapper.map(s, JobDto.class);
            }).collect(Collectors.toList());

            return mappedJobs;
        }

        return null;
    }

    @Override
    @Transactional
    public void removeJob(Long id) {
        Optional<JobEntity> jobOpt = jobRepository.findById(id);

        if(jobOpt.isPresent()){
            JobEntity job = jobOpt.get();

            interviewRepository.findAllByJob(job).stream().map(i -> {
                i.getRecruiter().getInterviewSlots().remove(i.getCandidate());

                return i.getId();
            }).forEach(interviewRepository::deleteById);
        }

        jobRepository.deleteById(id);
    }

    private Set<SkillEntity> matchSkills(Set<SkillDto> skills){
        Set<SkillEntity> skillsCollection = skills.stream().map(s -> {
            Optional<SkillEntity> skill = skillRepository.findSkillEntityByName(s.getName());

            if (skill.isPresent()) {
                return skill.get();
            } else {
                SkillEntity skillToSave = new SkillEntity();

                skillToSave.setName(s.getName());

                return skillRepository.save(skillToSave);
            }
        }).collect(Collectors.toSet());

        return skillsCollection;
    }
}
