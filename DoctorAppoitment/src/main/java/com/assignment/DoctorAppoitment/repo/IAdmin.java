package com.assignment.DoctorAppoitment.repo;

import com.assignment.DoctorAppoitment.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdmin extends JpaRepository<Admin, Integer> {
    Admin findFirstByAdminEmail(String email);
}
