package com.example.rectrutmenttool.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "recruiters")
public class RecruiterEntity extends BaseEntity{
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private String country;
    @OneToMany
    private Set<CandidateEntity> interviewSlots;
    private Integer level = 1;
    private Integer experience = 1;

    public RecruiterEntity() {
    }

    public Set<CandidateEntity> getInterviewSlots() {
        return interviewSlots;
    }

    public RecruiterEntity setInterviewSlots(Set<CandidateEntity> interviewSlots) {
        this.interviewSlots = interviewSlots;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public RecruiterEntity setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public RecruiterEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RecruiterEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public RecruiterEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public Integer getExperience() {
        return experience;
    }

    public RecruiterEntity setExperience(Integer experience) {
        this.experience = experience;
        return this;
    }
}
