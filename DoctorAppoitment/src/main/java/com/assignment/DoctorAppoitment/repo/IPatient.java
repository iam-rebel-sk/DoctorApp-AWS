package com.assignment.DoctorAppoitment.repo;

import com.assignment.DoctorAppoitment.model.Patient;
import com.assignment.DoctorAppoitment.model.PatientEnum.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPatient extends JpaRepository<Patient, Integer> {
    Patient findFirstByPatientEmail(String email);

    List<Patient> findByBloodGroup(BloodGroup bloodGroup);
}
