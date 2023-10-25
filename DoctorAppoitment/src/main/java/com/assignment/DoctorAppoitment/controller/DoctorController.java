package com.assignment.DoctorAppoitment.controller;

import com.assignment.DoctorAppoitment.model.Appointment;
import com.assignment.DoctorAppoitment.model.Doctor;
import com.assignment.DoctorAppoitment.service.AppointmentService;
import com.assignment.DoctorAppoitment.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @Autowired
    AppointmentService appointmentService;

    @GetMapping("doctor/{id}")
    public Doctor getDoctorById(@PathVariable Integer id){
        return doctorService.getDoctorById(id);
    }




}
