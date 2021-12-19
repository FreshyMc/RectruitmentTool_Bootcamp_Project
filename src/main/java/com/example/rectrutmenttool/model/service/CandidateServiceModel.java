package com.example.rectrutmenttool.model.service;

import com.example.rectrutmenttool.model.dto.RecruiterDto;
import com.example.rectrutmenttool.model.dto.SkillDto;

import java.time.LocalDate;
import java.util.Set;

public class CandidateServiceModel {
    private Set<SkillDto> skills;
    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    private LocalDate birthDate;
    private RecruiterDto recruiter;

    public CandidateServiceModel() {
    }

    public Set<SkillDto> getSkills() {
        return skills;
    }

    public CandidateServiceModel setSkills(Set<SkillDto> skills) {
        this.skills = skills;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CandidateServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CandidateServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CandidateServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public CandidateServiceModel setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public CandidateServiceModel setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public RecruiterDto getRecruiter() {
        return recruiter;
    }

    public CandidateServiceModel setRecruiter(RecruiterDto recruiter) {
        this.recruiter = recruiter;
        return this;
    }
}
