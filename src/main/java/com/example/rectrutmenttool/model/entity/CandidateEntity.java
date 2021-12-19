package com.example.rectrutmenttool.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "candidates")
public class CandidateEntity extends BaseEntity{
    @ManyToMany
    private Set<SkillEntity> skills;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(columnDefinition = "LONGTEXT")
    private String bio;
    @Column
    private LocalDate birthDate;
    @ManyToOne
    private RecruiterEntity recruiter;

    public CandidateEntity() {
    }

    public Set<SkillEntity> getSkills() {
        return skills;
    }

    public CandidateEntity setSkills(Set<SkillEntity> skills) {
        this.skills = skills;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CandidateEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CandidateEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CandidateEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public CandidateEntity setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public CandidateEntity setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public RecruiterEntity getRecruiter() {
        return recruiter;
    }

    public CandidateEntity setRecruiter(RecruiterEntity recruiter) {
        this.recruiter = recruiter;
        return this;
    }
}
