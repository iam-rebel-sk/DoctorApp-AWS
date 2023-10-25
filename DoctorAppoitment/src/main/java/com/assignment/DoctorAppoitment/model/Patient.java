package com.assignment.DoctorAppoitment.model;

import com.assignment.DoctorAppoitment.model.PatientEnum.BloodGroup;
import com.assignment.DoctorAppoitment.model.PatientEnum.Gender;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Patient.class, property = "patientId")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;
    private String patientName;

    @Email
    private String patientEmail;

    @Pattern(regexp = "^.{8,}$",
            message = "Your password should be 8 length")
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Enumerated(value = EnumType.STRING)
    private BloodGroup bloodGroup;

    @Size(min = 10,max = 10)
    @Pattern(regexp = "\\d+", message = "Phone number should be number with 10 character")
    private String contact;

    private LocalDate patientDateOFBirth;


    @OneToMany(mappedBy = "patient")
    List<Appointment> appointments;

}
