package com.assignment.DoctorAppoitment.service.TokenService;

import com.assignment.DoctorAppoitment.model.DTO.AuthenticationDTO;
import com.assignment.DoctorAppoitment.model.Patient;
import com.assignment.DoctorAppoitment.model.token.PatientAuthenticationToken;
import com.assignment.DoctorAppoitment.repo.TokenRepo.IPatientTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientTokenService {

    @Autowired
    IPatientTokenRepo patientTokenRepo;

    public void createToken(PatientAuthenticationToken token) {
        patientTokenRepo.save(token);
    }

    public boolean authentic(AuthenticationDTO authInfo) {
        String email = authInfo.getEmail();
        String tokenValue = authInfo.getTokenValue();

        PatientAuthenticationToken token = patientTokenRepo.findFirstByTokenValue(tokenValue);

        if (token != null){
            return token.getPatient().getPatientEmail().equals(email);
        }
        else {
            return false;
        }
    }

    public void deleteToken(String tokenValue) {
        PatientAuthenticationToken token = patientTokenRepo.findFirstByTokenValue(tokenValue);
        patientTokenRepo.delete(token);
    }
}
