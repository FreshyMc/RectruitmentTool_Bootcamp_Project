package com.example.rectrutmenttool.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RecruiterDto {
    @NotBlank
    private String lastName;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String country;

    public RecruiterDto() {
    }

    public String getLastName() {
        return lastName;
    }

    public RecruiterDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RecruiterDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public RecruiterDto setCountry(String country) {
        this.country = country;
        return this;
    }
}
