package com.assignment.DoctorAppoitment.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDTO {
    private String email;
    private String tokenValue;
}
