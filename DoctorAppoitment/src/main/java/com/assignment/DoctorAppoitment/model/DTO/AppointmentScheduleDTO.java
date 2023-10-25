package com.assignment.DoctorAppoitment.model.DTO;

import com.assignment.DoctorAppoitment.model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentScheduleDTO {

    private AuthenticationDTO authInfo;
    private Appointment appointment;
}
