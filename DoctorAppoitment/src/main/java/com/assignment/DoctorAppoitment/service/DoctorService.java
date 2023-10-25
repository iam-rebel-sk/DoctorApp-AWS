package com.assignment.DoctorAppoitment.service;

import com.assignment.DoctorAppoitment.model.Appointment;
import com.assignment.DoctorAppoitment.model.DTO.AuthenticationDTO;
import com.assignment.DoctorAppoitment.model.DTO.DoctorAddingDTO;
import com.assignment.DoctorAppoitment.model.Doctor;
import com.assignment.DoctorAppoitment.repo.IDoctor;
import com.assignment.DoctorAppoitment.service.TokenService.AdminTokenService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    IDoctor doctorRepo;

    @Autowired
    AdminTokenService adminTokenService;

    @Autowired
    AppointmentService appointmentService;

    // adding new doctor
    public String addDoctor(@NotNull DoctorAddingDTO addInfo) {
        AuthenticationDTO authInfo = addInfo.getAuthInfo();

        // authentication
        if(adminTokenService.authentication(authInfo)){

            Doctor newDoctor = addInfo.getDoctor();
            Integer docId = newDoctor.getDocId();

            if(docId!=null && doctorRepo.existsById(docId))
            {
                return "doctor already exists!!!";
            }

            //newDoctor.setAppointments(null);

            doctorRepo.save(newDoctor);

            return "doctor added!!!";

        }
        else {
            return "Unauthenticated access";
        }

    }

    public String removeDoctorById(AuthenticationDTO authInfo, Integer id) {

        if (adminTokenService.authentication(authInfo)){
            if (doctorRepo.existsById(id)){
                doctorRepo.deleteById(id);
                return "Doctor with id " + id + " removed";
            }
            else {
                return "Doctor with id " + id + " not exists";
            }

        }
        else {
            return "Unauthenticated access";
        }
    }

    public Doctor getDoctorById(Integer id) {
        if (doctorRepo.existsById(id)){
            return doctorRepo.findById(id).orElseThrow();
        }
        else{
            System.out.println("Unauthorised access");
            return null;
        }
    }

}
