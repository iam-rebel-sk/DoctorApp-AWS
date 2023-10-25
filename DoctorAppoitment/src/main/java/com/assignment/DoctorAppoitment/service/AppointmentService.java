package com.assignment.DoctorAppoitment.service;

import com.assignment.DoctorAppoitment.model.Appointment;
import com.assignment.DoctorAppoitment.model.DTO.AppointmentScheduleDTO;
import com.assignment.DoctorAppoitment.model.DTO.AuthenticationDTO;
import com.assignment.DoctorAppoitment.model.Doctor;
import com.assignment.DoctorAppoitment.model.Patient;
import com.assignment.DoctorAppoitment.repo.IAppointment;
import com.assignment.DoctorAppoitment.repo.IDoctor;
import com.assignment.DoctorAppoitment.repo.IPatient;
import com.assignment.DoctorAppoitment.service.TokenService.PatientTokenService;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    IAppointment appointmentRepo;

    @Autowired
    IPatient patientRepo;

    @Autowired
    IDoctor doctorRepo;

    @Autowired
    PatientTokenService patientTokenService;


    // schedule appointment
    public String appointmentSchedule(AppointmentScheduleDTO appointmentDetails) {

        AuthenticationDTO authInfo = appointmentDetails.getAuthInfo();
        Appointment appointment = appointmentDetails.getAppointment();

        // authentication
        if (patientTokenService.authentic(authInfo)){

            //find the patient
            String email = authInfo.getEmail();
            Patient existingPatient = patientRepo.findFirstByPatientEmail(email);
            appointment.setPatient(existingPatient);

            // find the doctor
            Doctor givenDoctor = appointment.getDoctor();
            Doctor existingDoctor = doctorRepo.findById(givenDoctor.getDocId()).orElseThrow();

            if(existingDoctor != null){
                appointment.setDoctor(existingDoctor);
                appointment.setCreationTime(LocalDateTime.now());
                appointmentRepo.save(appointment);
                return "Appointment with Dr. " + existingDoctor.getDocName() + " has been scheduled on "
                        + appointment.getScheduleTime();

            }
            else {
                return "Doctor is not exists, choose another doctor.";
            }

        }
        else {
            return "Unauthenticated access";
        }

    }

    // cancel appointment
    public String appointmentCancel(AuthenticationDTO authInfo, Integer appointmentId) {

        // authentication
        if (patientTokenService.authentic(authInfo)){

            // finding the existing patient corresponding given email
            String email = authInfo.getEmail();
            Patient existingPatient = patientRepo.findFirstByPatientEmail(email);

            // finding existing appointment corresponding given id
            Appointment existingAppointment = appointmentRepo.findById(appointmentId).orElseThrow();

            // checking is the existing appointment linked with existing patient
            // if yes -> let them delete
            if (existingAppointment.getPatient().equals(existingPatient)){
                appointmentRepo.deleteById(appointmentId);
                return "Appointment with Dr. " + existingAppointment.getDoctor().getDocName()
                        + " on : " + existingAppointment.getScheduleTime()  + " has been canceled";
            }
            else {
                return "Unauthorised access";
            }
        }

        else {
            return "Unauthenticated access";
        }
    }



}
