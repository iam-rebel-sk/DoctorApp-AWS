package com.assignment.DoctorAppoitment.model;

import com.assignment.DoctorAppoitment.model.DoctorEnum.Qualification;
import com.assignment.DoctorAppoitment.model.DoctorEnum.Specialization;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Doctor.class, property = "docId")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer docId;
    private String  docName;
    private double  docFee;

    @Enumerated(value = EnumType.STRING)
    private Specialization docSpecialization;

    @Enumerated(value = EnumType.STRING)
    private Qualification docQualification;

    private String docContact;

    @OneToMany(mappedBy = "doctor")
    List<Appointment> appointments;

}
