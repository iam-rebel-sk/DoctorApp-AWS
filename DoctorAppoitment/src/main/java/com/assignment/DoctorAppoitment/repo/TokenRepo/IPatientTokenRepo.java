package com.assignment.DoctorAppoitment.repo.TokenRepo;

import com.assignment.DoctorAppoitment.model.token.PatientAuthenticationToken;
import org.antlr.v4.runtime.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientTokenRepo extends JpaRepository<PatientAuthenticationToken, Integer> {
    PatientAuthenticationToken findFirstByTokenValue(String tokenValue);
}
