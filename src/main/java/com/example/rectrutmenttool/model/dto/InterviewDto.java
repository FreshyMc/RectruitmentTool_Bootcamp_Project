package com.example.rectrutmenttool.model.dto;

public class InterviewDto {
    private String recruiterName;
    private String candidateName;

    public InterviewDto() {
    }

    public String getRecruiterName() {
        return recruiterName;
    }

    public InterviewDto setRecruiterName(String recruiterName) {
        this.recruiterName = recruiterName;
        return this;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public InterviewDto setCandidateName(String candidateName) {
        this.candidateName = candidateName;
        return this;
    }
}
