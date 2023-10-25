package com.assignment.DoctorAppoitment.controller;

import com.assignment.DoctorAppoitment.model.DTO.*;
import com.assignment.DoctorAppoitment.model.Patient;
import com.assignment.DoctorAppoitment.service.AppointmentService;
import com.assignment.DoctorAppoitment.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class PatientController {
    @Autowired
    PatientService patientService;

    @Autowired
    AppointmentService appointmentService;

    // sign up
    @PostMapping("patient/signUp")
    public String patientSignUp(@Valid @RequestBody Patient newPatient){
        return  patientService.patientSignUp(newPatient);
    }

    // sign in
    @PostMapping("patient/signIn")
    public String patientSignIn(@Valid @RequestBody SignInInputDTO signInInputDTO){
        return patientService.patientSignIn(signInInputDTO);
    }

    // sign out
    @DeleteMapping("patient/signOut")
    public String patientSignOut(AuthenticationDTO authInfo){
        return patientService.patientSignOut(authInfo);
    }

    @PostMapping("patient/appointment/schedule")
    public String appointmentSchedule(@Valid @RequestBody AppointmentScheduleDTO appointmentDetails){
       // return patientService.appointmentSchedule(appointmentDetails);
        return appointmentService.appointmentSchedule(appointmentDetails);
    }

    @DeleteMapping("patient/appointment/{appointmentId}/cancel")
    public String appointmentCancel(@RequestBody AuthenticationDTO authInfo, @PathVariable Integer appointmentId){
        return appointmentService.appointmentCancel(authInfo,appointmentId);
    }

    @GetMapping("patient/{id}")
    public Patient getPatientById(@PathVariable Integer id, @RequestBody AuthenticationDTO authInfo){
        return patientService.getPatientById(id, authInfo);

    }

    @PutMapping("patient/change/password")
    public String changeYourPassword(@Valid @RequestBody ChangePasswordDTO changePassDetails){
        return patientService.changeYourPassword(changePassDetails);
    }

    @PutMapping("patient/forget/password")
    public String forgetYourPassword(@Valid @RequestBody ForgetPasswordDTO forgetPasswordDTO){
        return patientService.forgetYourPassword(forgetPasswordDTO);
    }
}
