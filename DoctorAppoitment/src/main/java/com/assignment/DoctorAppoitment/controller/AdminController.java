package com.assignment.DoctorAppoitment.controller;

import com.assignment.DoctorAppoitment.model.Admin;
import com.assignment.DoctorAppoitment.model.DTO.*;
import com.assignment.DoctorAppoitment.model.Doctor;
import com.assignment.DoctorAppoitment.model.Patient;
import com.assignment.DoctorAppoitment.model.PatientEnum.BloodGroup;
import com.assignment.DoctorAppoitment.service.AdminService;
import com.assignment.DoctorAppoitment.service.DoctorService;
import com.assignment.DoctorAppoitment.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;



    // sign up
    @PostMapping("admin/signUp")
    public String adminSignUp(@Valid @RequestBody Admin newAdmin){
        return adminService.adminSignUp(newAdmin);
    }

    // sign in
    @PostMapping("admin/signIn")
    public String adminSignIn(@Valid @RequestBody SignInInputDTO signInInputDTO){
        return adminService.adminSignIn(signInInputDTO);
    }

    // sign out
    @DeleteMapping("admin/signOut")
    public String adminSignOut(@Valid @RequestBody AuthenticationDTO authInfo){
        return adminService.adminSignOut(authInfo);
    }

    // posting doctor
    @PostMapping("doctor")
    public String addDoctor(@Valid @RequestBody DoctorAddingDTO addInfo){
        return doctorService.addDoctor(addInfo);
    }

    @DeleteMapping("doctor/{id}")
    public String removeDoctorById(@RequestBody AuthenticationDTO authInfo, @PathVariable Integer id){
        return doctorService.removeDoctorById(authInfo,id);
    }

    @GetMapping("patients/{bloodGroup}/bloodGroup")
    public List<Patient> getAllPatientOfBloodGroup(@RequestBody AuthenticationDTO authInfo, @PathVariable BloodGroup bloodGroup){
        return patientService.getAllPatientOfBloodGroup(authInfo,bloodGroup);
    }

    @PutMapping("admin/change/password")
    public String changeYourPassword(@Valid @RequestBody ChangePasswordDTO changePasswordDetails){
        return adminService.changeYourPassword(changePasswordDetails);
    }

    @PutMapping("admin/forget/password")
    public String forgetYourPassword(@Valid @RequestBody ForgetPasswordDTO forgetPasswordDTO){
        return adminService.forgetYourPassword(forgetPasswordDTO);
    }
}
