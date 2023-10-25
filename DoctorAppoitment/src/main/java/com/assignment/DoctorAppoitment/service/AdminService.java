package com.assignment.DoctorAppoitment.service;

import com.assignment.DoctorAppoitment.model.Admin;
import com.assignment.DoctorAppoitment.model.DTO.*;
import com.assignment.DoctorAppoitment.model.token.AdminAuthenticationToken;
import com.assignment.DoctorAppoitment.repo.IAdmin;
import com.assignment.DoctorAppoitment.repo.IDoctor;
import com.assignment.DoctorAppoitment.service.TokenService.AdminTokenService;
import com.assignment.DoctorAppoitment.service.email.EmailService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

@Service
public class AdminService {
    @Autowired
    IAdmin adminRepo;

    @Autowired
    AdminTokenService adminTokenService;

    @Autowired
    IDoctor doctorRepo;


    // sign up
    public String adminSignUp(@NotNull Admin newAdmin) {

        // check the email is already present or not in DB
        String email = newAdmin.getAdminEmail();
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(email);

        // if exists -> sign in
        if(existingAdmin != null){
            return "Admin is already registered. Please sign in";
        }

        // if not exist -> let them sign up
        String password = newAdmin.getAdminPassword();
        try {

            // password encryption
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            // set encrypted password
            newAdmin.setAdminPassword(encryptedPassword);

            // save admin in DB
            adminRepo.save(newAdmin);
            return "Sign up successful";

        } catch (NoSuchAlgorithmException e) {
            return "Internal server issue. Please try again later";
        }
    }


    // sign in
    public String adminSignIn(@NotNull SignInInputDTO signInInputDTO) {
        String email = signInInputDTO.getEmail();
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(email);

        if(existingAdmin == null){
            return "Email is not registered. Please sign up first";
        }

        String password = signInInputDTO.getPassword();
        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if(existingAdmin.getAdminPassword().equals(encryptedPassword)){

                AdminAuthenticationToken token = new AdminAuthenticationToken(existingAdmin);

                if (EmailService.sendEmail(email, "SIGN IN TOKEN", token.getTokenValue())){
                    adminTokenService.createToken(token);
                    return "Sign in successful";
                }
                else {
                    return "Error while creating token. Please do it later";
                }
            }
            else {
                return "Unauthorised access!";
            }

        } catch (NoSuchAlgorithmException e) {
            return "Internal server error. Please try again later";
        }


    }

    // sign out
    public String adminSignOut(AuthenticationDTO authInfo) {
        if (adminTokenService.authentication(authInfo)){

            String tokenValue = authInfo.getTokenValue();
            adminTokenService.deleteToken(tokenValue);
            return "Sign out successful";

        }
        else {
           return "Unauthenticated access!";
        }
    }


    public String changeYourPassword(ChangePasswordDTO changePasswordDetails) {
        AuthenticationDTO authInfo = changePasswordDetails.getAuthenticationDTO();
        String newPassword = changePasswordDetails.getPassword();

        if (adminTokenService.authentication(authInfo)){
            String email = authInfo.getEmail();
            Admin existingAdmin = adminRepo.findFirstByAdminEmail(email);

            try {
                String encryptedPassword = PasswordEncryptor.encrypt(newPassword);

                if (!encryptedPassword.equals(existingAdmin.getAdminPassword())) {
                    existingAdmin.setAdminPassword(encryptedPassword);
                    adminRepo.save(existingAdmin);


                    String tokenValue = authInfo.getTokenValue();
                    adminTokenService.deleteToken(tokenValue);
                    return "Password changed successfully. Please log in again";
                }
                else {
                    return "Password should not be same as previous";
                }

            } catch (NoSuchAlgorithmException e) {
                return "Internal server issue. Try again later";
            }
        }
        else {
            return "Unauthenticated access";
        }
    }


    public String forgetYourPassword(@NotNull ForgetPasswordDTO forgetPasswordDTO) {
        EmailVerifierDTO userInfo = forgetPasswordDTO.getUserInfo();
        String newPassword = forgetPasswordDTO.getPassword();

        Admin existingAdmin = adminRepo.findFirstByAdminEmail(userInfo.getEmail());
        if (existingAdmin != null){
            LocalDate dob = LocalDate.parse(userInfo.getDateOBirth());
            if(existingAdmin.getAdminDateOFBirth().equals(dob)){

                try {
                    String encryptedPassword = PasswordEncryptor.encrypt(newPassword);
                    existingAdmin.setAdminPassword(encryptedPassword);
                    adminRepo.save(existingAdmin);

                    return "Password change successfully";

                } catch (NoSuchAlgorithmException e) {
                    return "Internal server issue. Please try again later";
                }
            }
        }
        return "Please provide correct details";
    }

}
