package com.example.rectrutmenttool.model.dto;

import com.example.rectrutmenttool.model.entity.SkillEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

public class CandidateDto {
    private Set<SkillDto> skills;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String bio;
    @NotNull
    private LocalDate birthDate;
    private RecruiterDto recruiter;

    public CandidateDto() {
    }

    public Set<SkillDto> getSkills() {
        return skills;
    }

    public CandidateDto setSkills(Set<SkillDto> skills) {
        this.skills = skills;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CandidateDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CandidateDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CandidateDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public CandidateDto setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public CandidateDto setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public RecruiterDto getRecruiter() {
        return recruiter;
    }

    public CandidateDto setRecruiter(RecruiterDto recruiter) {
        this.recruiter = recruiter;
        return this;
    }
}
