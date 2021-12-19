package com.example.rectrutmenttool.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "interviews")
public class InterviewEntity extends BaseEntity{
    @ManyToOne
    private CandidateEntity candidate;
    @ManyToOne
    private RecruiterEntity recruiter;
    @ManyToOne
    private JobEntity job;

    public InterviewEntity() {
    }

    public CandidateEntity getCandidate() {
        return candidate;
    }

    public InterviewEntity setCandidate(CandidateEntity candidate) {
        this.candidate = candidate;
        return this;
    }

    public RecruiterEntity getRecruiter() {
        return recruiter;
    }

    public InterviewEntity setRecruiter(RecruiterEntity recruiter) {
        this.recruiter = recruiter;
        return this;
    }

    public JobEntity getJob() {
        return job;
    }

    public InterviewEntity setJob(JobEntity job) {
        this.job = job;
        return this;
    }
}
