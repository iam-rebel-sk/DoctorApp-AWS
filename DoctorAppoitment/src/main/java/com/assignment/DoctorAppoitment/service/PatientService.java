package com.assignment.DoctorAppoitment.service;

import com.assignment.DoctorAppoitment.model.DTO.*;
import com.assignment.DoctorAppoitment.model.Patient;
import com.assignment.DoctorAppoitment.model.PatientEnum.BloodGroup;
import com.assignment.DoctorAppoitment.model.token.PatientAuthenticationToken;
import com.assignment.DoctorAppoitment.repo.IAppointment;
import com.assignment.DoctorAppoitment.repo.IDoctor;
import com.assignment.DoctorAppoitment.repo.IPatient;
import com.assignment.DoctorAppoitment.service.TokenService.AdminTokenService;
import com.assignment.DoctorAppoitment.service.TokenService.PatientTokenService;
import com.assignment.DoctorAppoitment.service.email.EmailService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    IPatient patientRepo;

    @Autowired
    PatientTokenService patientTokenService;

    @Autowired
    AdminTokenService adminTokenService;

    @Autowired
    IDoctor doctorRepo;

    @Autowired
    IAppointment appointmentRepo;

    //  sign up
    public String patientSignUp(@NotNull Patient newPatient) {

        // check the email is already exist or not
        String email = newPatient.getPatientEmail();
        Patient existingPatient = patientRepo.findFirstByPatientEmail(email);


        //if exists then do sign in -> avoiding repeated entry
        if (existingPatient != null){
            return "Patient is already registered. Please sign in";
        }

        // if the email is not exist -> let them do sign up
            String password = newPatient.getPassword();

        try {

            // password encryption
            String encryptedPassword = PasswordEncryptor.encrypt(password);
            newPatient.setPassword(encryptedPassword);

            // save in the patient table
            newPatient.setAppointments(null);
            patientRepo.save(newPatient);
            return "Sign up successful.";

        } catch (NoSuchAlgorithmException e) {
            return "Internal server issue. Please try again later";
        }


    }

    // sign in
    public String patientSignIn(@NotNull SignInInputDTO signInInputDTO) {

        // check the email is present in DB or not
        String email = signInInputDTO.getEmail();
        Patient existingPatient = patientRepo.findFirstByPatientEmail(email);

        // if the email is not present sign up first
        if(existingPatient == null ){
            return "Email is not registered. Please sign up first";
        }

        // if the email is present in DB -> let them sign in

        // password checking corresponding their email -> password should be same
        String password = signInInputDTO.getPassword();
        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if(existingPatient.getPassword().equals(encryptedPassword)){

                // create a token for signed in patient
                PatientAuthenticationToken token = new PatientAuthenticationToken(existingPatient);

                // if the email delivered successfully then only the token will save to DB
                if(EmailService.sendEmail(email, "Signing Token", token.getTokenValue())){
                    patientTokenService.createToken(token);
                    return "Sign in successful";
                }
                else {
                    return "Error while creating token";
                }

            }
            else {
                return "Unauthorised access";
            }
        } catch (NoSuchAlgorithmException e) {
            return "Internal server error. Please try again later";
        }
    }

    // sign out
    public String patientSignOut(AuthenticationDTO authInfo) {

        if (patientTokenService.authentic(authInfo)){

            String tokenValue = authInfo.getTokenValue();
            patientTokenService.deleteToken(tokenValue);
            return "Sign out successful";
        }
        return "Unauthenticated access!";
    }


    public List<Patient> getAllPatientOfBloodGroup(AuthenticationDTO authInfo, BloodGroup bloodGroup) {

        if (adminTokenService.authentication(authInfo)){

           return patientRepo.findByBloodGroup(bloodGroup);
        }
        else {
            System.out.println("Unauthenticated access");
            return null;
        }
    }

    public String changeYourPassword(@NotNull ChangePasswordDTO changePassDetails) {

        AuthenticationDTO authInfo = changePassDetails.getAuthenticationDTO();
        String newPassword = changePassDetails.getPassword();
        if (patientTokenService.authentic(authInfo)){

            String email = authInfo.getEmail();
            Patient existingPatient = patientRepo.findFirstByPatientEmail(email);



            try {
                String encryptedPassword = PasswordEncryptor.encrypt(newPassword);

                if (!encryptedPassword.equals(existingPatient.getPassword())) {
                    existingPatient.setPassword(encryptedPassword);
                    patientRepo.save(existingPatient);


                    String tokenValue = authInfo.getTokenValue();
                    patientTokenService.deleteToken(tokenValue);
                    return "Password changed successfully. Please log in again";
                }
                else {
                    return "Password should not be same as previous";
                }

            } catch (NoSuchAlgorithmException e) {
                return "Internal server error. Please try again later";
            }

        }
        else {
            return "Unauthenticated access";
        }
    }

    public Patient getPatientById(Integer id, AuthenticationDTO authInfo) {
        if (patientTokenService.authentic(authInfo)){
            return patientRepo.findById(id).orElseThrow();
        }
        else {
            System.out.println("Unauthenticated access");
            return null;
        }
    }

    public String forgetYourPassword(@NotNull ForgetPasswordDTO forgetPasswordDTO) {
        EmailVerifierDTO userInfo = forgetPasswordDTO.getUserInfo();
        String newPassword = forgetPasswordDTO.getPassword();

        Patient existingPatient = patientRepo.findFirstByPatientEmail(userInfo.getEmail());
        if (existingPatient != null){
            LocalDate dob = LocalDate.parse(userInfo.getDateOBirth());
            if(existingPatient.getPatientDateOFBirth().equals(dob)){

                try {
                    String encryptedPassword = PasswordEncryptor.encrypt(newPassword);
                    existingPatient.setPassword(encryptedPassword);
                    patientRepo.save(existingPatient);

                    return "Password change successfully";

                } catch (NoSuchAlgorithmException e) {
                    return "Internal server issue. Please try again later";
                }
            }
        }
        return "Please provide correct details";
    }
}
