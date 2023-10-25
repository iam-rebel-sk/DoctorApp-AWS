package com.assignment.DoctorAppoitment.service.TokenService;

import com.assignment.DoctorAppoitment.model.DTO.AuthenticationDTO;
import com.assignment.DoctorAppoitment.model.token.AdminAuthenticationToken;
import com.assignment.DoctorAppoitment.repo.TokenRepo.IAdminTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminTokenService {
    @Autowired
    IAdminTokenRepo adminTokenRepo;

    public void createToken(AdminAuthenticationToken token) {
        adminTokenRepo.save(token);
    }

    public boolean authentication(AuthenticationDTO authInfo) {
        String email = authInfo.getEmail();

        String tokenValue = authInfo.getTokenValue();
        AdminAuthenticationToken token = adminTokenRepo.findFirstByTokenValue(tokenValue);
        if (token != null){
            return token.getAdmin().getAdminEmail().equals(email);
        }
        else {
            return false;
        }
    }

    public void deleteToken(String tokenValue) {
        AdminAuthenticationToken token = adminTokenRepo.findFirstByTokenValue(tokenValue);
        adminTokenRepo.delete(token);
    }
}
