package com.assignment.DoctorAppoitment.model.DTO;

import com.assignment.DoctorAppoitment.model.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAddingDTO {
    AuthenticationDTO authInfo;
    Doctor doctor;
}
