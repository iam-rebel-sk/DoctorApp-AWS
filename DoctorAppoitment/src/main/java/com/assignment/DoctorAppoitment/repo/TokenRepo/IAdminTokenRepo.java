package com.assignment.DoctorAppoitment.repo.TokenRepo;

import com.assignment.DoctorAppoitment.model.token.AdminAuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminTokenRepo extends JpaRepository<AdminAuthenticationToken, Integer> {
    AdminAuthenticationToken findFirstByTokenValue(String tokenValue);
}
