package com.assignment.DoctorAppoitment.model.token;

import com.assignment.DoctorAppoitment.model.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PatientAuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationTime;

    public PatientAuthenticationToken(Patient existingPatient) {
        this.patient = existingPatient;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationTime = LocalDateTime.now();
    }

    @OneToOne
    @JoinColumn(name = "fk_patient_id")
    Patient patient;
}
