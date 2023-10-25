package com.assignment.DoctorAppoitment.repo;

import com.assignment.DoctorAppoitment.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctor extends JpaRepository<Doctor, Integer> {
}
