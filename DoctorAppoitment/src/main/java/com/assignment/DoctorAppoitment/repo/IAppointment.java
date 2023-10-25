package com.assignment.DoctorAppoitment.repo;

import com.assignment.DoctorAppoitment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointment extends JpaRepository<Appointment, Integer> {
}
