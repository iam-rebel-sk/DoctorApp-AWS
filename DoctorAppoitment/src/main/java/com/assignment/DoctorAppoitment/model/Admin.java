package com.assignment.DoctorAppoitment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;

    private String adminName;

    private LocalDate adminDateOFBirth;

    @Pattern(regexp = ".*@hospital\\.admin\\.in$", message = "Please provide admin specify email")
    private String adminEmail;

    @Pattern(regexp = "^.{8,}$",
            message = "Your password should be 8 length")
    private String adminPassword;

}
